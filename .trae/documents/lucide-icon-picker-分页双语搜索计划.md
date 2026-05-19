# LucideIconPicker 分页与双语搜索计划

## Summary

- 目标：让 `LucideIconPicker` 默认基于注册表展示全部可搜索图标，并通过页码分页控制单次渲染数量。
- 搜索能力：在保留现有英文名称、显示名、标签搜索的基础上，新增中文别名匹配。
- 交互决策：采用页码分页，保留现有分类筛选；当搜索词或分类变化时自动回到第一页。

## Current State Analysis

- `lucide-compose/src/commonMain/kotlin/io/github/lucideicons/kmp/compose/picker/LucideIconPicker.kt`
  - 当前直接调用 `registry.search(state.query)`，会命中 `IconRegistry.search()` 的默认 `limit = 100`，因此即使注册表中有更多图标，Picker 也最多只显示 100 个结果。
  - 当前只做“搜索 + 分类过滤”，没有分页状态、分页切片或分页控件。
- `lucide-compose/src/commonMain/kotlin/io/github/lucideicons/kmp/compose/picker/LucideIconGrid.kt`
  - 已使用 `LazyVerticalGrid`，具备按需渲染基础，但仍会一次性消费传入结果列表。
- `lucide-compose/src/commonMain/kotlin/io/github/lucideicons/kmp/compose/picker/LucideIconPickerState.kt`
  - 当前仅保存 `query` 和 `selectedCategory`，没有当前页状态，也没有在筛选条件变化时重置页码的机制。
- `lucide-core/src/commonMain/kotlin/io/github/lucideicons/kmp/core/search/DefaultIconSearchStrategy.kt`
  - 当前只匹配 `key.value`、`displayName`、`tags`，尚不支持中文别名。
- `lucide-core/src/commonMain/kotlin/io/github/lucideicons/kmp/core/model/LucideIconMetadata.kt`
  - 当前元数据没有可扩展的额外搜索词字段。
- `lucide-core/src/commonMain/kotlin/io/github/lucideicons/kmp/core/generated/LucideGeneratedMetadata.kt`
  - 当前内置图标元数据仅包含英文显示名与英文标签，没有中文别名数据。
- `lucide-compose/src/commonMain/kotlin/io/github/lucideicons/kmp/compose/picker/LucideIconSearchBar.kt`
  - 当前搜索框文案固定为英文 `"Search icons"`。

## Proposed Changes

### 1. 扩展图标元数据的搜索词能力

- 修改 `lucide-core/src/commonMain/kotlin/io/github/lucideicons/kmp/core/model/LucideIconMetadata.kt`
  - 新增 `searchAliases: Set<String> = emptySet()` 字段。
  - 目的：为中文别名提供正式数据入口，同时保持现有调用兼容；已有构造调用无需修改。

### 2. 在核心搜索策略中纳入中文别名

- 修改 `lucide-core/src/commonMain/kotlin/io/github/lucideicons/kmp/core/search/DefaultIconSearchStrategy.kt`
  - 在现有 `key/displayName/tags` 之外，将 `searchAliases` 一并纳入归一化匹配。
  - 继续沿用现有排序规则：
    - 完全匹配优先
    - 前缀匹配次之
    - 子串匹配再次之
    - 标签/别名命中排在其后
  - 目的：保证英文搜索行为不回退，同时让中文别名能自然进入同一套结果排序。

### 3. 为内置图标补充中文别名

- 修改 `lucide-core/src/commonMain/kotlin/io/github/lucideicons/kmp/core/generated/LucideGeneratedMetadata.kt`
  - 为当前内置图标补充 `searchAliases`：
    - `activity` -> 例如 `"活动"`, `"脉冲"`, `"监测"`
    - `airplay` -> 例如 `"投屏"`, `"播放到设备"`, `"屏幕投射"`
    - `arrow-right` -> 例如 `"右箭头"`, `"向右"`, `"下一步"`
  - 目的：让当前仓库内置样例立即具备中文搜索能力，并为后续扩展更多图标提供一致模式。
  - 说明：当前仓库内置元数据规模较小，先直接在现有元数据文件中补齐；若后续引入完整图标集，再考虑将别名数据外置或接入生成流程。

### 4. 为 Picker 引入分页状态

- 修改 `lucide-compose/src/commonMain/kotlin/io/github/lucideicons/kmp/compose/picker/LucideIconPickerState.kt`
  - 新增 `currentPage`，默认值为 `0`。
  - 新增明确的状态操作方法，至少包括：
    - 更新查询词并重置到第一页
    - 更新分类并重置到第一页
    - 切换到指定页
  - 目的：把“筛选条件变化后页码回归第一页”的行为固定到状态层，避免 UI 层分散处理。

### 5. 让 Picker 获取全量结果并按页切片

- 修改 `lucide-compose/src/commonMain/kotlin/io/github/lucideicons/kmp/compose/picker/LucideIconPicker.kt`
  - 新增参数 `pageSize: Int = 48`。
  - 查询时改为显式传入足够大的上限，例如 `registry.search(state.query, limit = Int.MAX_VALUE)`，确保 Picker 拿到完整可匹配结果集，而不是默认 100 条。
  - 保留现有分类过滤逻辑，并在过滤后计算：
    - `totalResults`
    - `pageCount`
    - 当前页合法范围
    - 当前页 `pagedResults`
  - 搜索词或分类变化时通过状态方法重置 `currentPage = 0`。
  - 将 `LucideIconGrid` 的数据源改为 `pagedResults`。
  - 目的：组件默认展示“全部结果中的当前页”，而不是一次性把全部结果直接塞进网格。

### 6. 新增页码分页控件

- 新增文件 `lucide-compose/src/commonMain/kotlin/io/github/lucideicons/kmp/compose/picker/LucideIconPagination.kt`
  - 提供简洁分页 UI，包含：
    - 上一页
    - 当前页 / 总页数
    - 下一页
    - 可选总结果数展示
  - 仅在 `pageCount > 1` 时显示。
  - 采用 `Material3` 基础组件实现，避免引入额外依赖。
  - 目的：将分页显示逻辑与主 Picker 拆开，保持 `LucideIconPicker.kt` 结构清晰。

### 7. 小幅调整搜索框文案

- 修改 `lucide-compose/src/commonMain/kotlin/io/github/lucideicons/kmp/compose/picker/LucideIconSearchBar.kt`
  - 将 label 调整为更明确的双语提示，例如 `Search icons / 搜索图标`。
  - 目的：让组件直接提示“支持中文与英文检索”，减少使用成本。

### 8. 更新测试覆盖

- 修改 `lucide-core/src/commonTest/kotlin/io/github/lucideicons/kmp/core/IconSearchEngineTest.kt`
  - 新增或改造测试，验证：
    - 英文前缀/标签匹配保持有效
    - 中文别名可命中对应图标
    - 中文搜索不会破坏现有英文排序行为
- 修改 `lucide-compose/src/commonTest/kotlin/io/github/lucideicons/kmp/compose/picker/LucideIconPickerStateTest.kt`
  - 新增状态测试，验证：
    - 初始页码为第一页
    - 变更查询词后页码重置
    - 变更分类后页码重置
    - 手动切页状态可正确保存

### 9. 更新文档与示例

- 修改 `README.md`
  - 更新 `LucideIconPicker` 的能力说明，补充“页码分页”和“中文 / 英文搜索”。
- 修改 `LOCAL_USAGE.md`
  - 同步说明新的 `pageSize` 参数与搜索行为。
- 可选修改 `sample-compose/src/commonMain/kotlin/io/github/lucideicons/kmp/sample/screens/IconPickerDemoScreen.kt`
  - 若示例需要更明确展示分页，可显式传入一个较小的 `pageSize`（例如 6 或 9）以方便演示。
  - 若不需要演示效果，也可以不改示例代码，仅依赖默认值。

## Assumptions & Decisions

- 已确认采用“页码分页”，不使用“加载更多”或“无限滚动”。
- 已确认保留现有分类筛选；分类作为搜索结果上的附加过滤条件。
- 已确认中文搜索范围为“中文别名 + 现有英文搜索”，不扩展到拼音或首字母检索。
- 分页默认值定为 `pageSize = 48`，与当前默认 `columns = 6` 搭配时可形成 8 行一页，兼顾信息密度和滚动长度。
- `IconRegistry.search()` 的默认行为保持不变，避免影响库的其他调用方；“获取全量结果再分页”只在 Picker 内部显式处理。
- 中文别名先通过 `LucideIconMetadata.searchAliases` 承载，避免额外引入新的搜索索引类型，同时对未来自定义图标预留扩展空间。

## Verification Steps

1. 运行 `lucide-core` 单元测试，确认英文搜索回归通过且新增中文搜索用例通过。
2. 运行 `lucide-compose` 单元测试，确认 Picker 状态与分页相关测试通过。
3. 打开 `sample-compose` 中的 Picker 示例，手动验证：
   - 空搜索时可翻页浏览全部已注册图标。
   - 输入英文关键字时结果正确、页码正确重置。
   - 输入中文关键字时能命中对应图标。
   - 切换分类时结果收敛且页码回到第一页。
4. 检查 README / LOCAL_USAGE 文档示例与新参数说明是否一致。
