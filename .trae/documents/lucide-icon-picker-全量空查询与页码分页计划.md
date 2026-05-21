# LucideIconPicker 全量空查询与页码分页计划

## Summary

- 目标：修正 `LucideIconPicker` 在空查询时只显示少量图标的问题，并为结果列表引入页码分页。
- 核心行为：
  - 空查询时不再走 `search()` 的默认 `limit = 100`，而是基于注册表取出全部图标元数据后排序展示。
  - 有查询词时继续走 `registry.search(query, locale, limit)`，并通过公开参数保留 `searchLimit`。
  - 结果列表按 `pageSize` 分页，使用“上一页 / 当前页 / 下一页”的页码切换交互。
- 范围：优先限制在 `lucide-compose` 模块，尽量复用现有 `lucide-core` 能力，不引入不必要的核心 API 变更。

## Current State Analysis

- `lucide-compose/src/commonMain/kotlin/com/shermant/compose/picker/LucideIconPicker.kt`
  - 当前使用 `registry.search(state.query, locale = locale)` 作为统一数据源。
  - 该调用未显式传入 `limit`，会命中 `IconRegistry.search()` 的默认 `limit = 100`，因此空查询时最多只能拿到 100 条结果。
  - 当前仅包含“搜索 + 分类过滤 + 网格渲染”，没有分页状态、分页切片和分页控件。
- `lucide-compose/src/commonMain/kotlin/com/shermant/compose/picker/LucideIconPickerState.kt`
  - 当前只保存 `query` 和 `selectedCategory`，缺少 `currentPage` 以及“筛选条件变化后重置页码”的状态方法。
- `lucide-compose/src/commonMain/kotlin/com/shermant/compose/picker/LucideIconGrid.kt`
  - 当前 `LazyVerticalGrid` 会完整消费传入的 `icons` 列表，本身不限制数量；问题源头在上游结果集被截断。
- `lucide-core/src/commonMain/kotlin/com/shermant/core/registry/IconRegistry.kt`
  - 当前公开了 `keys()` 和 `metadata(...)`，可在不修改核心接口的前提下拼出“全部元数据”集合。
  - `search(query, locale, limit)` 已经支持显式搜索上限，因此有查询词时无需改动核心搜索实现。
- `lucide-core/src/commonMain/kotlin/com/shermant/core/model/LucideIconMetadata.kt`
  - 已提供 `displayName(locale)`，可直接用于空查询的本地化排序。
- `lucide-compose/src/commonMain/kotlin/com/shermant/compose/picker/LucideIconPickerStyle.kt`
  - 当前样式仅覆盖容器、搜索框、分类栏、网格；若新增分页 UI，需要补充对应样式对象。
- `lucide-compose/build.gradle.kts`
  - 已包含 `compose.material3`，可以在分页控件中继续复用现有 Compose 依赖，无需新增库。
- `sample-compose/src/commonMain/kotlin/com/shermant/sample/screens/IconPickerDemoScreen.kt`
  - 当前示例固定高度为 `320.dp`，很适合在后续手动验证中观察分页行为。

## Assumptions & Decisions

- 已确认分页交互采用“页码切换”，不做“加载更多”或“无限滚动”。
- 已确认对外暴露 `pageSize` 与 `searchLimit` 两个参数。
- `searchLimit` 仅作用于“有查询词”的分支；空查询始终展示注册表中的全部图标。
- 保持 `IconRegistry.search()` 默认行为不变，避免影响库中其他调用方；行为修正只在 `LucideIconPicker` 内部完成。
- 分页切换与筛选重置逻辑优先落在 `LucideIconPickerState`，避免 UI 层到处分散写 `state.currentPage = 0`。
- 不新增 `lucide-core` 的“all metadata”公开接口，先复用现有 `keys()` + `metadata()`，以减少 API 面积变化。
- 默认参数建议：
  - `pageSize: Int = 48`，与当前默认 `columns = 6` 组合时一页 8 行，密度较均衡。
  - `searchLimit: Int = 100`，保持现有搜索上限语义不变，仅把它显式暴露到 Picker API。

## Proposed Changes

### 1. 扩展 Picker 状态以承载页码

- 修改 `lucide-compose/src/commonMain/kotlin/com/shermant/compose/picker/LucideIconPickerState.kt`
  - 新增 `currentPage` 状态，默认值为 `0`。
  - 新增明确的状态方法，统一维护筛选与分页关系，例如：
    - `updateQuery(query: String)`
    - `selectCategory(category: LucideIconCategory?)`
    - `goToPage(page: Int)`
    - 可选 `resetPage()`
  - 规则：
    - 查询词变化时重置到第一页。
    - 分类变化时重置到第一页。
    - 手动翻页只改 `currentPage`。
  - 同步更新 `rememberLucideIconPickerState(...)`，保持构造方式兼容，并支持新的初始页码默认值。

### 2. 在 Picker 内部拆分“空查询全量”与“有查询走 search”

- 修改 `lucide-compose/src/commonMain/kotlin/com/shermant/compose/picker/LucideIconPicker.kt`
  - 为主重载新增参数：
    - `pageSize: Int = 48`
    - `searchLimit: Int = 100`
  - 把结果获取逻辑拆为两条路径：
    - 空查询：
      - 使用 `registry.keys()`
      - 通过 `registry.metadata(key)` 取回全部 `LucideIconMetadata`
      - 过滤空值后按 `displayName(locale)` 排序
    - 有查询：
      - 使用 `registry.search(state.query, locale = locale, limit = searchLimit)`
  - 在统一结果集基础上继续执行现有分类过滤。
  - 再基于过滤后结果计算：
    - `totalResults`
    - `pageCount`
    - 合法的当前页索引
    - 当前页切片 `pagedResults`
  - 网格只消费 `pagedResults`，不再直接消费完整结果集。
  - 仅当 `pageCount > 1` 时显示分页控件。
  - 对 `pageSize` 和 `searchLimit` 增加基础保护：
    - 推荐在组件入口使用 `require(pageSize > 0)` 与 `require(searchLimit > 0)`。

### 3. 让状态变更通过方法而不是直接赋值

- 修改 `lucide-compose/src/commonMain/kotlin/com/shermant/compose/picker/LucideIconPicker.kt`
  - 搜索框回调从 `state.query = it` 改为 `state.updateQuery(it)`。
  - 分类选择回调从 `state.selectedCategory = it` 改为 `state.selectCategory(it)`。
  - 第二个简化重载里，外部 `query` 同步到内部 state 时，改为使用状态方法而非裸赋值，确保外部 query 变化时页码也正确重置。

### 4. 新增内部分页控件组件

- 新增文件 `lucide-compose/src/commonMain/kotlin/com/shermant/compose/picker/LucideIconPagination.kt`
  - 提供内部 `@Composable` 分页组件，职责只包括：
    - 上一页按钮
    - 当前页 / 总页数文本
    - 下一页按钮
    - 可选总结果数文本
  - 行为规则：
    - 首页禁用“上一页”。
    - 末页禁用“下一页”。
    - `pageCount <= 1` 时不渲染。
  - 实现方式：
    - 复用当前模块已有的 Compose/Foundation/Material3 依赖。
    - 保持组件内部化，避免无必要扩大公开 API 面。

### 5. 为分页 UI 补充样式配置

- 修改 `lucide-compose/src/commonMain/kotlin/com/shermant/compose/picker/LucideIconPickerStyle.kt`
  - 新增 `LucideIconPickerPaginationStyle` 数据类。
  - 在 `LucideIconPickerStyle` 中新增 `pagination` 字段。
  - 样式内容保持克制，只覆盖分页栏真正需要的最小集合，例如：
    - 水平间距
    - 文本样式
    - 文本颜色 / 禁用颜色
    - 按钮或容器的可选视觉参数
- 修改 `lucide-compose/src/commonMain/kotlin/com/shermant/compose/picker/LucideIconPickerDefaults.kt`
  - 在 `style(...)` 默认实现中补上 `pagination = ...`。
  - 默认视觉与现有搜索框、分类栏风格保持一致，避免分页控件显得突兀。

### 6. 让两个 Picker 重载都支持分页参数

- 修改 `lucide-compose/src/commonMain/kotlin/com/shermant/compose/picker/LucideIconPicker.kt`
  - 第二个简化重载也新增：
    - `pageSize: Int = 48`
    - `searchLimit: Int = 100`
  - 并把这两个参数透传给主重载。
  - 保持现有对外用法兼容：旧调用点不需要改动即可编译通过。

### 7. 提炼可测试的分页逻辑

- 修改 `lucide-compose/src/commonMain/kotlin/com/shermant/compose/picker/LucideIconPicker.kt`
  - 将分页计算提炼为内部纯函数或内部小型数据结构，便于 `commonTest` 做普通单元测试，而不是依赖 Compose UI 测试框架。
  - 推荐覆盖的纯逻辑：
    - 结果总数到页数的换算
    - 当前页越界时的回退策略
    - 当前页切片范围
  - 这样可以在不引入 UI 测试基建的情况下，稳定验证分页行为。

### 8. 更新测试

- 修改 `lucide-compose/src/commonTest/kotlin/com/shermant/compose/picker/LucideIconPickerStateTest.kt`
  - 新增状态相关测试：
    - 初始页码为第一页
    - 更新查询词会重置页码
    - 切换分类会重置页码
    - `goToPage()` 可正确保存目标页
  - 新增默认值测试：
    - `LucideIconPickerDefaults.style()` 仍能生成完整样式对象，包含新的 `pagination` 默认样式
- 新增测试文件，建议命名为 `lucide-compose/src/commonTest/kotlin/com/shermant/compose/picker/LucideIconPickerPaginationTest.kt`
  - 验证分页纯逻辑：
    - 0 条结果时页数和切片行为正确
    - 结果数小于 `pageSize` 时只有 1 页
    - 结果数大于 `pageSize` 时切片边界正确
    - 请求超出范围页码时会被校正到最后一页或第一页
    - 空查询全量结果路径与有查询路径在分页阶段使用相同规则

### 9. 更新文档与示例

- 修改 `README.md`
  - 在能力说明中补充：
    - 空查询显示全部已注册图标
    - `pageSize`
    - `searchLimit`
    - 页码分页行为
- 修改 `README.zh-CN.md`
  - 同步中文说明与示例代码。
- 修改 `LOCAL_USAGE.md`
  - 在本地验证示例中补充分页相关参数示例，便于消费方验证。
- 可选修改 `sample-compose/src/commonMain/kotlin/com/shermant/sample/screens/IconPickerDemoScreen.kt`
  - 显式传入较小的 `pageSize`，例如 `pageSize = 12`，便于样例中观察分页切换。
  - 若默认分页效果已足够明显，可不改示例代码，仅在手动验证时观察当前行为。

## Verification Steps

1. 运行 `lucide-compose` 的 `commonTest`，确认新增状态测试与分页逻辑测试通过。
2. 打开 `sample-compose` 中的 `IconPickerDemoScreen`，手动验证：
   - 空查询时可翻页浏览全部已注册图标，而不是只显示前 100 个。
   - 输入查询词后结果来自 `search()`，并受 `searchLimit` 控制。
   - 修改查询词后自动回到第一页。
   - 切换分类后自动回到第一页。
   - 首页和末页的分页按钮禁用状态正确。
3. 检查 `README.md`、`README.zh-CN.md`、`LOCAL_USAGE.md` 中的参数名与默认行为说明是否一致。
4. 对改动文件运行诊断检查，确保新增样式字段和分页组件没有引入编译或静态检查错误。
