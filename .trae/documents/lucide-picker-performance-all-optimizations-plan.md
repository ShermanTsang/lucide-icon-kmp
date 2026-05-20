# Lucide Icon Picker 全量性能优化计划

## Summary

- 目标：对 `LucideIconPicker` 当前已识别出的全部真实性能热点做一次完整优化，优先降低搜索阶段的同步计算成本，并补上参数化图标的共享缓存。
- 成功标准：
  - 修改分类时不再重复执行整轮搜索。
  - 搜索路径不再在每次调用时重复构造同样的元数据列表与空查询排序结果。
  - 相同 `name + size + strokeWidth + colorArgb` 的参数化图标可跨懒加载网格单元复用，而不是每个新建单元都重新构建 `ImageVector`。
  - 对外 API、默认交互行为、搜索结果语义保持不变。
  - 现有核心搜索与注册表测试继续通过，并补充覆盖新增缓存行为的测试。

## Current State Analysis

- `lucide-compose/src/commonMain/kotlin/com/shermant/compose/picker/LucideIconPicker.kt`
  - `results` 当前由 `remember(registry, locale, state.query, state.selectedCategory)` 直接计算。
  - 这意味着只要分类切换，就会重新调用 `registry.search(...)`，即使查询字符串没有变化。
- `lucide-compose/src/commonMain/kotlin/com/shermant/compose/picker/LucideIconGrid.kt`
  - 网格已经使用 `LazyVerticalGrid`，并带有稳定 key，说明“同时加载所有图标”的问题并不存在。
  - 当前渲染压力主要来自每个可见单元调用 `LucideIcon(...)` 时的向量解析成本。
- `lucide-compose/src/commonMain/kotlin/com/shermant/compose/LucideIcon.kt`
  - 组件内部只使用 `remember(name, size, strokeWidth, registry)` 做单个组合实例级缓存。
  - 懒加载列表中的 item 被销毁后，重新进入视口时仍可能重复解析同参数图标。
- `lucide-core/src/commonMain/kotlin/com/shermant/core/registry/IconFactory.kt`
  - 当前只为默认参数图标维护 `cachedIcons: MutableMap<String, ImageVector>`。
  - 只要参数非默认且存在 `ParameterizedIconProvider`，就直接 `provider.create(parameters)`，不会进入共享缓存。
- `lucide-core/src/commonMain/kotlin/com/shermant/core/registry/MutableIconRegistry.kt`
  - `search()` 每次都做 `entries.values.map { it.metadata }`。
  - `keys()` 与 `byCategory()` 也都基于 `entries.values` 重新映射/过滤。
- `lucide-core/src/commonMain/kotlin/com/shermant/core/search/DefaultIconSearchStrategy.kt`
  - 搜索为同步串行执行。
  - 空查询时会对完整集合重新排序。
  - 非空查询时会对每个条目构造多个临时列表并在末尾统一排序后 `take(limit)`。
- 构建配置现状：
  - `lucide-compose/build.gradle.kts` 与 `lucide-core/build.gradle.kts` 的 `commonMain` 未声明 `kotlinx.coroutines`。
  - 因此本轮优化不引入协程、防抖或异步状态流，避免新增依赖与交互时延变化。

## Proposed Changes

### 1. 拆分 Picker 中“搜索”和“分类过滤”的计算职责

- 目标文件：
  - `lucide-compose/src/commonMain/kotlin/com/shermant/compose/picker/LucideIconPicker.kt`
- 调整内容：
  - 将当前单一 `results` 计算拆为两段：
    - `searchResults`：仅依赖 `registry`、`locale`、`state.query`
    - `filteredResults`：仅依赖 `searchResults`、`state.selectedCategory`
  - 分类切换时只执行内存中过滤，不再触发 `registry.search(...)`。
  - 保持两个 `LucideIconPicker(...)` 重载的公开签名不变。
- 实现方式：
  - 优先使用清晰变量名，例如 `searchResults`、`visibleResults`。
  - 不引入新的状态对象，也不改变 `onQueryChange` / `onIconSelected` 的回调契约。
- 预期收益：
  - 降低分类切换时的主线程 CPU 与临时对象分配。

### 2. 为注册表增加可复用的元数据快照缓存

- 目标文件：
  - `lucide-core/src/commonMain/kotlin/com/shermant/core/registry/MutableIconRegistry.kt`
- 调整内容：
  - 在 `DefaultIconRegistry` 内维护派生缓存，而不是每次从 `entries.values` 重新生成：
    - `metadataSnapshot`
    - `keySnapshot`
    - `categoryIndex`
  - `register(...)` 成功写入后统一刷新或失效这些缓存。
  - `keys()`、`search()`、`byCategory()` 改为优先读取缓存结果。
- 实现方式：
  - 保持注册表对外接口不变。
  - 若 `replace = true` 且同名图标被替换，需要同时失效相关派生缓存，避免旧数据残留。
- 预期收益：
  - 避免高频 `map/filter` 造成的重复分配。
  - 为后续搜索缓存与分类查询提供稳定基础数据。

### 3. 为搜索路径增加“空查询排序缓存”和“重复查询结果缓存”

- 目标文件：
  - `lucide-core/src/commonMain/kotlin/com/shermant/core/registry/MutableIconRegistry.kt`
  - `lucide-core/src/commonMain/kotlin/com/shermant/core/search/DefaultIconSearchStrategy.kt`
- 调整内容：
  - 在注册表层维护按 `locale` 划分的空查询排序缓存。
    - 当 `query` 为空时，直接返回当前 locale 的已排序元数据列表，再按 `limit` 截断。
  - 在注册表层维护按 `normalized query + locale + limit` 组合键划分的搜索结果缓存。
    - 相同查询重复出现时直接返回缓存结果。
  - 任何 `register(...)` 或替换注册都要清理搜索相关缓存。
- 实现方式：
  - 缓存建在注册表而不是 Compose 层，保证同一注册表实例可被多个 UI 入口复用。
  - 继续复用现有 `DefaultIconSearchStrategy` 的匹配语义，不改变前缀、包含、标签匹配与 locale fallback 规则。
- 预期收益：
  - 降低清空搜索框、重复输入相同查询、不同界面重复搜索时的重复排序与扫描成本。

### 4. 收紧搜索策略内部的临时对象分配

- 目标文件：
  - `lucide-core/src/commonMain/kotlin/com/shermant/core/search/DefaultIconSearchStrategy.kt`
- 调整内容：
  - 保留现有匹配顺序与排序规则，但重写内部实现，减少 `buildList`、`distinct`、链式 `map` 带来的中间集合分配。
  - 优先改为显式局部变量和直接循环判断，必要时只保留最小中间集合。
- 实现方式：
  - 不引入新的公开类型。
  - 保证中英文搜索、标签搜索和排序结果与当前行为兼容。
- 预期收益：
  - 降低每次搜索时的内存抖动与 GC 压力。

### 5. 为参数化图标增加共享缓存

- 目标文件：
  - `lucide-core/src/commonMain/kotlin/com/shermant/core/registry/IconFactory.kt`
  - 如有必要：`lucide-core/src/commonMain/kotlin/com/shermant/core/registry/ParameterizedIconProvider.kt`
- 调整内容：
  - 在 `IconFactory` 中新增参数化图标缓存，而不是仅缓存默认参数图标。
  - 缓存键包含：
    - `name`
    - `size`
    - `strokeWidth`
    - `colorArgb`
  - 默认参数仍走现有默认缓存路径；非默认参数改为先查参数化缓存，再决定是否创建。
- 实现方式：
  - 若 `IconRenderParameters` 适合作为缓存键，则优先让其具备稳定键语义；否则新建内部缓存键类型。
  - `replace = true` 覆盖注册时，同名图标的默认缓存与参数化缓存都必须失效。
- 预期收益：
  - `LazyVerticalGrid` 中同一图标在滚动出入视口时可复用已构建的 `ImageVector`。
  - 对 `LucideIconPicker` 默认 `iconSize = 20.dp` 的场景收益最明显。

### 6. 保持 Compose 层 API 稳定，不引入异步防抖

- 目标文件：
  - `lucide-compose/src/commonMain/kotlin/com/shermant/compose/picker/LucideIconPicker.kt`
  - `lucide-compose/build.gradle.kts`
  - `lucide-core/build.gradle.kts`
- 决策内容：
  - 不新增 `kotlinx.coroutines` 依赖。
  - 不引入 `debounce` / `snapshotFlow` / `LaunchedEffect` 的异步搜索管线。
- 原因：
  - 当前仓库 common 模块未声明协程依赖。
  - 用户偏好倾向复用现有代码和保持结构清晰。
  - 本轮主要热点可以通过同步缓存和职责拆分解决，收益更稳，行为变化更小。

### 7. 补充测试覆盖，验证行为不变且缓存生效

- 目标文件：
  - `lucide-core/src/commonTest/kotlin/com/shermant/core/IconRegistryTest.kt`
  - `lucide-core/src/commonTest/kotlin/com/shermant/core/IconSearchEngineTest.kt`
  - 如需新增测试文件，则放在：
    - `lucide-core/src/commonTest/kotlin/com/shermant/core/`
- 调整内容：
  - 在 `IconRegistryTest.kt` 中补充：
    - 相同参数化请求返回同一个缓存实例。
    - 不同参数返回不同实例。
    - `replace = true` 后旧缓存失效，不再返回旧向量实例。
  - 在 `IconSearchEngineTest.kt` 中补充：
    - 空查询与中英文查询在加入缓存后仍返回相同语义结果。
    - 重复查询结果一致，分类相关行为不变。
- 说明：
  - 当前 `lucide-compose` 仅有状态/样式层级测试，没有现成 Compose UI 测试基础设施。
  - 本轮不额外引入 UI 测试框架，重点通过核心逻辑测试保证兼容性。

## Assumptions & Decisions

- 假设本轮范围仅针对当前已确认的两类真实热点：
  - 搜索路径重复计算
  - 参数化图标重复构建
- 决策：保持所有公开 API、默认参数、搜索匹配规则、UI 交互行为不变。
- 决策：不新增协程依赖，不引入异步搜索或输入防抖。
- 决策：优先在 `lucide-core` 做共享缓存，因为这是跨 Compose 入口复用收益最高的位置。
- 决策：缓存必须支持注册表替换注册的正确失效，避免为了性能引入陈旧数据问题。

## Verification Steps

- 静态检查：
  - 对本轮修改过的 Kotlin 文件执行诊断检查，确认未引入新的语法或类型错误。
- 单元测试：
  - 执行 `lucide-core` 模块测试，重点验证搜索行为与缓存行为。
  - 执行 `lucide-compose` 模块测试，确认 picker 状态与样式相关已有测试不受影响。
- 手工回归重点：
  - 搜索框输入英文关键字，确认结果仍正确更新。
  - 使用 `LucideLocale.Zh` 输入中文关键字，确认中文搜索结果保持正确。
  - 仅切换分类、不改查询时，结果应即时变化且语义不变。
  - 在图标网格中滚动并重复进入相同区域，确认没有功能回退或明显卡顿。

## Implementation Order

1. 调整 `LucideIconPicker.kt`，拆分搜索与分类过滤。
2. 调整 `MutableIconRegistry.kt`，引入元数据快照、分类索引与搜索缓存。
3. 调整 `DefaultIconSearchStrategy.kt`，减少内部临时对象分配。
4. 调整 `IconFactory.kt`，引入参数化图标共享缓存与失效逻辑。
5. 补充 `lucide-core` 测试，验证行为兼容和缓存命中语义。
6. 运行诊断与相关测试，完成回归验证。
