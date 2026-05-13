# Lucide Kotlin / KMP 项目设计计划

## Summary

目标是在一个全新的 Kotlin Multiplatform 仓库中设计并落地一套面向 Android、Desktop、iOS 的 Lucide Icons 封装方案，提供：

- 按名称懒加载图标的共享运行时
- 面向 Compose Multiplatform 的 `LucideIcon` 与 `LucideIconPicker` 组件
- 构建期图标生成工具，将 Lucide SVG 转换为独立 Kotlin `ImageVector` 文件
- 用户自定义图标注册能力
- 样例应用与 README，覆盖授权说明与基础使用方式

当前计划以 `core + ui + generator + sample` 四模块为主，默认启用 Full Compose iOS 设计，图标来源采用构建期生成。

## Current State Analysis

仓库当前状态：

- 根目录 `d:\Code\Project\lucide-icon-kotlin@kmp` 为空
- 不存在现有 Gradle、KMP、Compose 或源码结构
- 不存在 `.trae` 以外的既有约束，因此本次计划按“从零初始化工程”设计

由此得出的设计前提：

- 模块边界、包结构、构建脚本、发布结构需要一起规划
- 不需要迁移已有实现，但需要从一开始约束清楚 API 稳定层与生成产物目录
- 需要避免把“运行时职责”和“生成工具职责”混在同一模块

## Assumptions & Decisions

核心决策：

- iOS 纳入首版架构范围，`commonMain` 共享 API，`iosMain` 只承载必要的平台接线或示例入口，不复制图标逻辑
- 图标生成采用构建期生成：以 Lucide 官方 SVG 资产为输入，生成每个图标独立的 Kotlin 文件
- 发布面分为运行时核心层与 UI 层：`lucide-core` 提供注册表、懒加载与基础数据模型；`lucide-compose` 提供 Compose 组件
- 生成器单独放在 `lucide-generator`，避免将 SVG 解析依赖带入消费者运行时
- 样例应用单独放在 `sample-compose`，用于展示搜索、分类浏览、自定义图标注册与跨平台使用方式
- 默认将生成后的 Kotlin 文件提交到仓库中，保证消费侧不需要本地生成步骤；但生成器保留用于升级 Lucide 版本时重新生成

API 决策：

- 以字符串名称作为主查询键，额外提供类型安全包装 `LucideIconKey`
- `size` 优先使用 Compose 侧 `Dp`，同时在底层数据模型保留 `defaultSize: Float` 与视口尺寸，避免平台耦合
- `strokeWidth` 在组件层暴露为 `Float`
- `color` 在 Compose 组件层使用 `Color`
- `LucideIconPicker` 返回 `LucideIconKey` 与 `String` 两种回调入口，便于简单场景和类型安全场景共存
- 搜索先支持前缀匹配与大小写不敏感子串匹配；模糊匹配作为可扩展策略接口，不在第一版引入复杂评分算法

边界决策：

- 第一版不支持运行时直接解析任意 SVG 字符串为 Compose 图标；自定义图标通过注册 `ImageVector` 或注册 `creator` lambda 实现
- 第一版不做远程图标下载，不做资源压缩包分发
- 第一版以 Compose Multiplatform 为主，不单独设计 XML View 或 UIKit 封装

## Proposed Changes

### 1. 根工程与模块结构

新增根级文件：

- `settings.gradle.kts`
  - 作用：声明多模块工程与版本目录
  - 做法：包含 `:lucide-core`、`:lucide-compose`、`:lucide-generator`、`:sample-compose`
- `build.gradle.kts`
  - 作用：根级公共插件与仓库管理
  - 做法：统一 Kotlin Multiplatform、Compose Multiplatform、Kotlin Serialization 等版本入口
- `gradle/libs.versions.toml`
  - 作用：集中管理 Kotlin、Compose、测试与工具依赖版本
- `README.md`
  - 作用：项目介绍、安装、使用示例、生成流程、授权说明
- `LICENSE`
  - 作用：项目自身许可证
- `NOTICE` 或 `licenses/lucide-license.txt`
  - 作用：保留 Lucide Icons MIT License 原文

建议目录：

```text
lucide-icon-kotlin@kmp/
├─ build.gradle.kts
├─ settings.gradle.kts
├─ gradle/libs.versions.toml
├─ README.md
├─ LICENSE
├─ NOTICE
├─ lucide-core/
├─ lucide-compose/
├─ lucide-generator/
└─ sample-compose/
```

### 2. `lucide-core` 模块

职责：

- 图标元数据模型
- 图标注册表
- 懒加载工厂
- 自定义图标扩展点
- 图标名称、分类、查询能力

建议文件：

- `lucide-core/build.gradle.kts`
  - KMP 模块，启用 `androidTarget`、`jvm("desktop")`、`iosX64`、`iosArm64`、`iosSimulatorArm64`
  - `commonMain` 仅依赖 Kotlin 标准库与必要的不可视化共享依赖
- `lucide-core/src/commonMain/kotlin/.../model/LucideIconKey.kt`
  - 定义 `@JvmInline value class LucideIconKey(val value: String)`
  - 作用：避免裸字符串滥用，同时保留字符串互操作
- `lucide-core/src/commonMain/kotlin/.../model/LucideIconCategory.kt`
  - 定义分类模型，支持官方分类与 `Custom`
- `lucide-core/src/commonMain/kotlin/.../model/LucideIconMetadata.kt`
  - 包含 `key`、`displayName`、`tags`、`categories`、`defaultStrokeWidth`、`sourceSet`
- `lucide-core/src/commonMain/kotlin/.../registry/IconRegistry.kt`
  - 提供注册、查询、列出全部名称、按分类过滤
  - 内部结构采用 `MutableMap<String, IconEntry>`，值对象持有 metadata 与 creator
- `lucide-core/src/commonMain/kotlin/.../registry/IconEntry.kt`
  - 定义 `metadata + creator + cache policy`
- `lucide-core/src/commonMain/kotlin/.../registry/IconFactory.kt`
  - 负责懒创建与缓存策略
  - 第一版默认“按名称缓存已生成 `ImageVector`”
- `lucide-core/src/commonMain/kotlin/.../registry/IconSearchEngine.kt`
  - 提供 `prefix`、`contains`、`tag`、`category` 检索
  - 模糊搜索通过接口 `IconSearchStrategy`
- `lucide-core/src/commonMain/kotlin/.../registry/BuiltInIconRegistrar.kt`
  - 提供批量注册内建图标入口，由生成代码调用
- `lucide-core/src/commonMain/kotlin/.../registry/CustomIconRegistrar.kt`
  - 对外暴露开发者注册自定义图标的 API
- `lucide-core/src/commonMain/kotlin/.../registry/LucideIcons.kt`
  - 对外 facade，提供统一访问点

核心 API 草案：

```kotlin
@JvmInline
value class LucideIconKey(val value: String)

data class LucideIconMetadata(
    val key: LucideIconKey,
    val displayName: String,
    val tags: Set<String> = emptySet(),
    val categories: Set<LucideIconCategory> = emptySet(),
    val defaultStrokeWidth: Float = 2f,
    val sourceSet: IconSourceSet = IconSourceSet.BuiltIn,
)

fun interface LucideIconCreator {
    fun create(): ImageVector
}

interface IconRegistry {
    fun register(metadata: LucideIconMetadata, creator: LucideIconCreator)
    fun register(key: String, creator: LucideIconCreator)
    fun get(key: LucideIconKey): ImageVector?
    fun get(name: String): ImageVector?
    fun require(key: LucideIconKey): ImageVector
    fun contains(name: String): Boolean
    fun keys(): List<LucideIconKey>
    fun metadata(name: String): LucideIconMetadata?
    fun search(query: String, limit: Int = 100): List<LucideIconMetadata>
    fun byCategory(category: LucideIconCategory): List<LucideIconMetadata>
}
```

实现说明：

- `register()` 只注册 `creator`，不立即创建 `ImageVector`
- `get()` 首次调用时通过 `creator.create()` 生成并写入缓存
- 缓存层使用线程安全 map；KMP 下优先选择简单可移植实现
- `keys()` 和 `metadata()` 直接基于注册表元数据返回，不触发图标实例化

### 3. `lucide-compose` 模块

职责：

- Compose UI 组件
- 图标展示层属性映射
- Icon Picker 搜索和网格 UI

建议文件：

- `lucide-compose/build.gradle.kts`
  - 依赖 `:lucide-core` 与 Compose UI/Foundation/Material3
- `lucide-compose/src/commonMain/kotlin/.../LucideIcon.kt`
  - 核心图标显示组件
- `lucide-compose/src/commonMain/kotlin/.../LucideIconDefaults.kt`
  - 默认大小、颜色、描边宽度策略
- `lucide-compose/src/commonMain/kotlin/.../picker/LucideIconPicker.kt`
  - 对外主 Picker API
- `lucide-compose/src/commonMain/kotlin/.../picker/LucideIconPickerState.kt`
  - 搜索词、分类、选中项、布局配置
- `lucide-compose/src/commonMain/kotlin/.../picker/LucideIconGrid.kt`
  - 图标网格列表
- `lucide-compose/src/commonMain/kotlin/.../picker/LucideIconSearchBar.kt`
  - 搜索输入
- `lucide-compose/src/commonMain/kotlin/.../picker/LucideIconCategoryTabs.kt`
  - 分类筛选 UI
- `lucide-compose/src/commonMain/kotlin/.../model/LucideIconRenderStyle.kt`
  - 组件层渲染属性封装
- `lucide-compose/src/commonMain/kotlin/.../ext/IconRegistryComposeExt.kt`
  - 将注册表查询结果映射给 Compose 组件

`LucideIcon` API 草案：

```kotlin
@Composable
fun LucideIcon(
    name: String,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    size: Dp = LucideIconDefaults.Size,
    color: Color = LocalContentColor.current,
    strokeWidth: Float = LucideIconDefaults.StrokeWidth,
    registry: IconRegistry = LucideIcons.registry,
)

@Composable
fun LucideIcon(
    key: LucideIconKey,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    size: Dp = LucideIconDefaults.Size,
    color: Color = LocalContentColor.current,
    strokeWidth: Float = LucideIconDefaults.StrokeWidth,
    registry: IconRegistry = LucideIcons.registry,
)
```

渲染策略：

- 内部通过 `registry.get(name)` 懒取图标
- 对未找到图标提供可配置兜底：
  - 默认显示空白占位或错误占位符
  - 可扩展 `missingIconContent` slot
- `strokeWidth` 不建议在运行时直接修改已生成 `ImageVector` path 数据；计划中采用两层方案：
  - 内建图标生成时保留默认描边
  - 组件层 `strokeWidth` 首版作为“生成参数覆写”能力，仅对通过 `IconFactory` 动态创建的图标生效
  - 为避免误导，README 和 API 注释中明确：若图标来自已生成定型 `ImageVector`，`strokeWidth` 只有在启用参数化工厂时才会改变视觉结果

说明：

- 这是当前需求里最关键的技术约束，因为标准 `ImageVector` 通常是静态路径定义
- 因此第一版设计需要把“按名称获取独立文件图标”和“可变描边参数”拆成两种实现通道

参数化扩展设计：

- 新增 `ParameterizedIconProvider`
- 若某图标支持参数化生成，则 `LucideIcon` 传入的 `strokeWidth` 会交给 provider 重新创建 `ImageVector`
- 若不支持，则回退到默认生成版本

```kotlin
interface ParameterizedIconProvider {
    fun create(
        size: Float? = null,
        strokeWidth: Float? = null,
        colorArgb: Long? = null,
    ): ImageVector
}
```

`LucideIconPicker` API 草案：

```kotlin
@Stable
class LucideIconPickerState(
    initialQuery: String = "",
    initialCategory: LucideIconCategory? = null,
)

@Composable
fun rememberLucideIconPickerState(
    initialQuery: String = "",
    initialCategory: LucideIconCategory? = null,
): LucideIconPickerState

@Composable
fun LucideIconPicker(
    state: LucideIconPickerState,
    modifier: Modifier = Modifier,
    registry: IconRegistry = LucideIcons.registry,
    columns: Int = 6,
    iconSize: Dp = 20.dp,
    iconColor: Color = LocalContentColor.current,
    strokeWidth: Float = LucideIconDefaults.StrokeWidth,
    showSearchBar: Boolean = true,
    showCategories: Boolean = true,
    onIconSelected: (LucideIconMetadata) -> Unit,
)
```

Picker 行为：

- 搜索输入实时过滤
- 分类与搜索组合过滤
- 支持传入自定义 item 内容 slot，便于接入业务 UI 风格
- 支持返回 `LucideIconMetadata`，消费侧可自行取 `name` 或 `ImageVector`

建议再补一个简化重载：

```kotlin
@Composable
fun LucideIconPicker(
    modifier: Modifier = Modifier,
    query: String,
    onQueryChange: (String) -> Unit,
    onIconSelected: (String) -> Unit,
)
```

### 4. `lucide-generator` 模块

职责：

- 拉取或读取 Lucide SVG 源文件
- 解析 SVG 为内部向量模型
- 生成每个图标独立的 Kotlin 文件
- 生成注册表与元数据索引文件

建议文件：

- `lucide-generator/build.gradle.kts`
  - JVM 模块或 Kotlin/JVM CLI 工具
- `lucide-generator/src/main/kotlin/.../GeneratorMain.kt`
  - 生成入口
- `lucide-generator/src/main/kotlin/.../config/GeneratorConfig.kt`
  - 输入目录、输出目录、包名、版本号配置
- `lucide-generator/src/main/kotlin/.../source/LucideSvgSource.kt`
  - 处理本地 SVG 目录或上游 release 资源
- `lucide-generator/src/main/kotlin/.../parser/SvgIconParser.kt`
  - 解析 SVG path、viewBox、基础属性
- `lucide-generator/src/main/kotlin/.../model/ParsedIcon.kt`
  - 生成前中间模型
- `lucide-generator/src/main/kotlin/.../writer/IconFileWriter.kt`
  - 生成每个图标独立 Kotlin 文件
- `lucide-generator/src/main/kotlin/.../writer/IconRegistryWriter.kt`
  - 生成批量注册代码与 metadata 索引
- `lucide-generator/src/main/kotlin/.../writer/IconCategoryWriter.kt`
  - 生成分类映射

生成输出目标：

- `lucide-core/src/commonMain/kotlin/.../generated/icons/*.kt`
- `lucide-core/src/commonMain/kotlin/.../generated/LucideGeneratedRegistry.kt`
- `lucide-core/src/commonMain/kotlin/.../generated/LucideGeneratedMetadata.kt`

单图标生成代码形态建议：

```kotlin
public val LucideIcons.Activity: ImageVector
    get() = activity ?: buildActivity().also { activity = it }

private var activity: ImageVector? = null

private fun buildActivity(): ImageVector = Builder(
    name = "activity",
    defaultWidth = 24.dp,
    defaultHeight = 24.dp,
    viewportWidth = 24f,
    viewportHeight = 24f,
).apply {
    // path(...)
}.build()
```

但结合“按名称懒加载”目标，注册表层不应在 `LucideIcons.Activity` 属性初始化时批量访问这些属性。应改为生成 creator 引用：

```kotlin
internal fun registerGeneratedIcons(registry: MutableIconRegistry) {
    registry.register(
        metadata = LucideGeneratedMetadata.Activity,
        creator = LucideIconCreator { buildActivity() }
    )
}
```

这样只有实际命中 `get("activity")` 时才会创建 `ImageVector`。

### 5. 分类、搜索与元数据

新增文件：

- `lucide-core/src/commonMain/kotlin/.../search/IconQuery.kt`
- `lucide-core/src/commonMain/kotlin/.../search/IconSearchStrategy.kt`
- `lucide-core/src/commonMain/kotlin/.../search/DefaultIconSearchStrategy.kt`

默认搜索规则：

- 规范化输入：小写化、去除 `-`、`_`、空格差异
- 匹配优先级：
  1. 完全匹配
  2. 前缀匹配
  3. 子串匹配
  4. tag 命中
- 返回结果限制默认 100 条，避免 Picker 首次搜索过多渲染

分类数据来源：

- 优先使用 Lucide 官方标签或维护映射表
- 若上游无稳定分类，第一版可采用项目内维护的 `categories.json` 作为生成输入

### 6. 自定义图标扩展

新增文件：

- `lucide-core/src/commonMain/kotlin/.../custom/CustomIconSet.kt`
- `lucide-core/src/commonMain/kotlin/.../custom/CustomIconRegistration.kt`

对外 API：

```kotlin
fun IconRegistry.registerCustomIcon(
    name: String,
    categories: Set<LucideIconCategory> = setOf(LucideIconCategory.Custom),
    tags: Set<String> = emptySet(),
    creator: LucideIconCreator,
)
```

扩展点规则：

- 自定义图标与内建图标共用同一注册表接口
- 默认禁止覆盖同名内建图标，除非显式传入 `replace = true`
- README 中给出业务图标与品牌图标接入示例

### 7. 平台结构

考虑到大部分逻辑共享，平台目录保持最薄：

- `lucide-core/src/androidMain/kotlin/...`
- `lucide-core/src/desktopMain/kotlin/...`
- `lucide-core/src/iosMain/kotlin/...`
- `lucide-compose/src/androidMain/kotlin/...`
- `lucide-compose/src/desktopMain/kotlin/...`
- `lucide-compose/src/iosMain/kotlin/...`

平台层职责：

- 若无平台差异则保持空目录或仅保留包声明
- iOS 侧主要用于示例与预览集成，不复制注册逻辑
- 如后续 Compose iOS 对某些输入组件行为存在差异，可仅在 Picker 输入层做 expect/actual 或平台包装

### 8. `sample-compose` 模块

职责：

- 展示 `LucideIcon` 基础使用
- 展示 `LucideIconPicker` 搜索、分类、选择回调
- 展示自定义图标注册

建议文件：

- `sample-compose/build.gradle.kts`
- `sample-compose/src/commonMain/kotlin/.../App.kt`
- `sample-compose/src/commonMain/kotlin/.../screens/IconGalleryScreen.kt`
- `sample-compose/src/commonMain/kotlin/.../screens/IconPickerDemoScreen.kt`
- `sample-compose/src/commonMain/kotlin/.../screens/CustomIconsDemoScreen.kt`
- 平台入口：
  - `sample-compose/src/androidMain/kotlin/.../MainActivity.kt`
  - `sample-compose/src/desktopMain/kotlin/.../main.kt`
  - `sample-compose/src/iosMain/kotlin/...`

示例内容：

- 搜索 `activity`、`arrow` 等前缀结果
- 分类浏览
- 选择一个图标后回填名称
- 自定义注册一个 `brand-logo` 图标并在 Picker 中可见

### 9. README 与版权

`README.md` 需包含：

- 项目定位与模块说明
- 安装方式
- `LucideIcon` 最小示例
- `LucideIconPicker` 最小示例
- 自定义图标注册示例
- 如何重新生成图标
- 平台支持矩阵
- License 说明：
  - 本项目代码许可
  - Lucide Icons 使用 MIT License
  - Lucide 原始版权声明保留位置

需要补充的授权文件：

- 根目录 `NOTICE` 写明本项目包含基于 Lucide Icons 生成的图标数据
- `licenses/lucide-license.txt` 保存 Lucide MIT 原文

### 10. 测试与验证设计

建议测试文件：

- `lucide-core/src/commonTest/kotlin/.../IconRegistryTest.kt`
  - 注册、懒加载、重复注册、防覆盖策略
- `lucide-core/src/commonTest/kotlin/.../IconSearchEngineTest.kt`
  - 前缀、子串、tag、分类搜索
- `lucide-core/src/commonTest/kotlin/.../CustomIconRegistrationTest.kt`
  - 自定义图标注册与查询
- `lucide-compose/src/commonTest/kotlin/.../LucideIconPickerStateTest.kt`
  - 查询状态与过滤逻辑
- `lucide-generator/src/test/kotlin/.../SvgIconParserTest.kt`
  - SVG 解析输出的稳定性
- `lucide-generator/src/test/kotlin/.../IconFileWriterTest.kt`
  - 生成文件结构快照测试

UI 验证建议：

- `sample-compose` 手动验收：
  - Android 上显示图标、切换颜色、改变大小
  - Desktop 上搜索与分类过滤
  - iOS 上验证共享 API 可用与基础渲染

## Execution Steps

推荐实施顺序：

1. 初始化根级 KMP/Compose 多模块工程与版本目录
2. 实现 `lucide-core` 的模型、注册表、懒加载、搜索接口
3. 实现 `lucide-generator` 的 SVG 解析与代码生成流程
4. 生成一小批图标进行端到端联调，再扩展到完整 Lucide 集
5. 实现 `lucide-compose` 的 `LucideIcon` 与 Picker 组件
6. 实现 `sample-compose` 示例与跨平台入口
7. 补充 README、NOTICE、许可证与测试

## Verification Steps

完成实现后应验证：

- 工程可以成功同步并编译 `android`、`desktop`、`ios` 目标
- `lucide-generator` 能从指定 SVG 输入生成独立 Kotlin 图标文件和注册表文件
- `IconRegistry.get(name)` 不会在启动时实例化全部图标
- `keys()`、`metadata()`、`search()` 在不创建 `ImageVector` 的情况下可正常工作
- `LucideIcon(name = "activity")` 能正确渲染默认图标
- `LucideIconPicker` 支持搜索、分类筛选、点击回调
- 自定义图标注册后可立即通过 `LucideIcon` 与 `LucideIconPicker` 使用
- README 示例代码可直接运行
- 授权声明文件包含 Lucide MIT License 原文与归属说明

## Risks & Notes

- `strokeWidth` 的“运行时可配置”与“预生成静态 ImageVector”之间存在天然张力；实现时必须明确参数化生成与静态生成的适用边界
- 完整 Lucide 图标量较大，初次生成和 IDE 索引成本需要评估；必要时可以继续拆分为 `lucide-icons-base`、`lucide-icons-extended` 等子模块
- iOS 的 Compose 体验依赖 Compose Multiplatform 当前版本能力，首版应优先保证共享 API 和基础渲染，再优化交互体验
