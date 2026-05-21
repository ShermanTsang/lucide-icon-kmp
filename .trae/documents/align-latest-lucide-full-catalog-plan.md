# 与最新版 Lucide Icons 对齐并全量生成注册计划

## Summary

- 目标：将仓库当前固定的 Lucide 快照从 `lucide-generator/src/main/resources/lucide-icons/VERSION.txt` 中记录的旧提交更新到 Lucide 官方最新稳定 release，并基于现有生成链路全量生成、提交并注册所有图标。
- 成功标准：
  - `lucide-generator` 中 vendored 的上游图标资源与最新稳定 release 对齐。
  - `lucide-core/src/commonMain/kotlin/com/shermant/core/generated` 中的生成产物与新快照一致。
  - 运行时继续保持现有“全量注册 + 按需懒构建 `ImageVector`”架构，不改公开消费 API。
  - 注册表计数、元数据计数、资源计数三者一致，并能抽样解析多个新增/变更图标。

## Current State Analysis

- 当前项目采用“仓库内置快照 -> JVM 生成器 -> 提交生成后的 Kotlin 代码 -> 运行时懒注册/懒构建”的链路。
- 上游固定快照位于 `lucide-generator/src/main/resources/lucide-icons`，当前 `VERSION.txt` 记录的是旧快照，且 `svg_count=1695`、`metadata_json_count=1695`。
- 生成入口是 `:lucide-generator:generateBundledLucide`，定义在 `lucide-generator/build.gradle.kts`。
- 生成主流程在 `lucide-generator/src/main/kotlin/com/shermant/generator/GeneratorMain.kt`，会把输入资源转换为：
  - `lucide-core/src/commonMain/kotlin/com/shermant/core/generated/icons/*.kt`
  - `lucide-core/src/commonMain/kotlin/com/shermant/core/generated/LucideGeneratedMetadata*.kt`
  - `lucide-core/src/commonMain/kotlin/com/shermant/core/generated/LucideGeneratedRegistry.kt`
- 运行时注册入口是 `lucide-core/src/commonMain/kotlin/com/shermant/core/registry/BuiltInIconRegistrar.kt`，它调用生成的 `registerGeneratedIcons(registry)`。
- 运行时加载并不是预构建全部图标，而是 `IconFactory` 在 `lucide-core/src/commonMain/kotlin/com/shermant/core/registry/IconFactory.kt` 里通过 `getOrPut` 按需缓存构建。
- 当前实现并非“只注册少量图标”；现状更像是“当前只对齐到旧快照”，以及“运行时只构建被实际访问的子集”。
- 当前生成器的潜在兼容性边界在 `lucide-generator/src/main/kotlin/com/shermant/generator/parser/SvgIconParser.kt`：
  - 目前只显式支持 `path`、`circle`、`ellipse`、`line`、`polyline`、`polygon`、`rect`。
  - 若最新版 Lucide 引入新的 SVG 结构或属性组合，生成器需要先补强再重新生成。
- 现有验证基础已经存在：
  - `lucide-generator/src/test/kotlin/com/shermant/generator/GeneratorMainIntegrationTest.kt`
  - `lucide-generator/src/test/kotlin/com/shermant/generator/SvgIconParserTest.kt`
  - `lucide-core/src/commonTest/kotlin/com/shermant/core/IconRegistryTest.kt`

## Proposed Changes

### 1. 更新 vendored Lucide 快照

- 文件/目录：
  - `lucide-generator/src/main/resources/lucide-icons/icons`
  - `lucide-generator/src/main/resources/lucide-icons/VERSION.txt`
- 变更内容：
  - 用 Lucide 官方最新稳定 release 的图标资源替换当前 vendored `icons` 内容。
  - 更新 `VERSION.txt` 中的来源、tag/commit、`svg_count`、`metadata_json_count` 等信息，使其能准确反映这次同步后的上游基线。
- 原因：
  - 当前全量覆盖只针对旧快照，不代表已经覆盖最新版官方图标集。
- 实施方式：
  - 以 GitHub Releases latest 对应的稳定 tag 为同步基准。
  - 只同步生成链路所需的上游输入资源，避免把上游仓库无关文件引入本项目。

### 2. 审核并补强 SVG 解析兼容性

- 文件：
  - `lucide-generator/src/main/kotlin/com/shermant/generator/parser/SvgIconParser.kt`
  - 若需要，连带更新其依赖的模型或辅助函数文件：
    - `lucide-generator/src/main/kotlin/com/shermant/generator/model/ParsedIcon.kt`
    - `lucide-generator/src/main/kotlin/com/shermant/generator/writer/IconFileWriter.kt`
- 变更内容：
  - 用最新版上游资源跑生成任务，定位是否存在无法解析的新增 SVG 元素、属性或路径场景。
  - 若发现不兼容，优先在 `SvgIconParser.kt` 做最小必要扩展，保持现有代码结构和命名规则不变。
- 原因：
  - 只有资源更新而没有解析器兼容性，可能导致一部分最新版图标无法进入生成产物。
- 实施方式：
  - 先以失败样本驱动修复。
  - 仅支持 Lucide 最新 release 实际用到的结构，避免过度抽象。
  - 保持生成输出风格与现有 provider 文件一致，尽量复用已有写出逻辑。

### 3. 保持元数据与命名链路兼容最新版图标集

- 文件：
  - `lucide-generator/src/main/kotlin/com/shermant/generator/source/LucideSvgSource.kt`
  - `lucide-generator/src/main/kotlin/com/shermant/generator/NamingUtils.kt`
  - `lucide-generator/src/main/kotlin/com/shermant/generator/writer/IconCategoryWriter.kt`
  - `lucide-generator/src/main/kotlin/com/shermant/generator/writer/IconRegistryWriter.kt`
- 变更内容：
  - 确认最新版图标命名、别名、分类、标签、元数据结构仍能被现有读取和写出逻辑正确处理。
  - 若上游 JSON 结构出现差异，补齐读取兼容逻辑。
  - 若新增图标名称触发 Kotlin 标识符冲突或数字/符号命名边界，补强 `NamingUtils.kt`。
- 原因：
  - “全量实现”不仅是生成 `icons/*.kt`，还要求 metadata、search、registry 三条链路完整贯通。
- 实施方式：
  - 以生成成功和 registry/search 可见为验收标准，而不是只看 provider 文件是否产出。

### 4. 重新生成并提交 core 侧全量产物

- 文件/目录：
  - `lucide-core/src/commonMain/kotlin/com/shermant/core/generated/icons/*.kt`
  - `lucide-core/src/commonMain/kotlin/com/shermant/core/generated/LucideGeneratedMetadata.kt`
  - `lucide-core/src/commonMain/kotlin/com/shermant/core/generated/LucideGeneratedMetadataChunk*.kt`
  - `lucide-core/src/commonMain/kotlin/com/shermant/core/generated/LucideGeneratedRegistry.kt`
- 变更内容：
  - 执行 `:lucide-generator:generateBundledLucide`，用新快照重新生成全部 built-in 图标 provider、metadata chunk 和 registry 代码。
- 原因：
  - 当前仓库消费侧依赖已提交的生成 Kotlin 产物，而不是在安装时动态生成。
- 实施方式：
  - 不手工改生成目录下的单图标文件，统一通过生成器产出，保持可重复生成。

### 5. 补充或收紧验证用例

- 文件：
  - `lucide-core/src/commonTest/kotlin/com/shermant/core/IconRegistryTest.kt`
  - `lucide-generator/src/test/kotlin/com/shermant/generator/GeneratorMainIntegrationTest.kt`
  - 如有必要：`lucide-generator/src/test/kotlin/com/shermant/generator/SvgIconParserTest.kt`
- 变更内容：
  - 将当前偏宽松的覆盖断言升级为与新快照计数联动的验证，避免只检查“大于某个阈值”。
  - 增加对最新版新增或曾经失败样本图标的抽样生成/解析断言。
  - 继续验证 metadata chunk、category、tag、localized name 的生成没有回退。
- 原因：
  - 这次改动的核心风险是“看起来生成成功，但少图标、少元数据或少注册”。
- 实施方式：
  - 测试保持聚焦，不为每个图标逐个写低价值断言。
  - 重点验证计数一致性、代表性样本和回归风险点。

### 6. 同步文档中的版本与生成说明

- 文件：
  - `README.md`
  - `README.zh-CN.md`
- 变更内容：
  - 更新关于 pinned snapshot 的描述，使文档与新的 `VERSION.txt` 一致。
  - 若执行步骤或生成注意事项发生变化，同步更新对应说明。
- 原因：
  - 当前 README 已明确说明仓库维护固定 Lucide 快照并提交生成产物；版本同步后文档也应反映新基线。

## Assumptions & Decisions

- 决策：以 Lucide 官方“最新稳定 release”为对齐基准，不追 `main` 最新提交。
- 决策：保留当前 `LucideIcons.registry` 的懒初始化与 `IconFactory` 的按需 `ImageVector` 构建，不改为运行时预加载全部图标。
- 决策：优先复用现有生成器、注册表、搜索和 Compose 消费链路，不重做架构。
- 决策：`lucide-core` 下的 generated 代码继续作为仓库提交产物存在。
- 假设：新增图标即使暂时没有完整中文名，也不阻塞“全量图标生成与注册”目标；中文本地化保持兼容和尽力同步，但不作为本次唯一验收门槛。
- 假设：最新版 release 的上游资源仍然提供当前生成器依赖的 SVG 与元数据文件布局；若布局变化，则在 `LucideSvgSource.kt` 做兼容适配。

## Verification Steps

1. 确认 `lucide-generator/src/main/resources/lucide-icons/VERSION.txt` 已更新到新的稳定 release 基线，且资源计数准确。
2. 运行 `./gradlew :lucide-generator:generateBundledLucide`，确保生成阶段无解析异常。
3. 检查 `lucide-core/src/commonMain/kotlin/com/shermant/core/generated` 中的 provider、metadata、registry 已全部更新。
4. 运行与本次改动直接相关的测试：
   - `./gradlew :lucide-generator:test`
   - `./gradlew :lucide-core:test`
5. 验证计数一致性：
   - vendored `icons/*.svg` 数量
   - `VERSION.txt` 中记录的 `svg_count`
   - generated metadata `all` 的实际条目数
   - registry 注册后的 `registeredCount`
6. 抽样验证多个图标可以被成功解析和构建，至少覆盖：
   - 历史上已验证的常见图标
   - 这次同步新增的代表性图标
   - 若存在解析器修复，对应失败样本必须纳入回归验证
7. 检查 README 与 `VERSION.txt` 的叙述一致，避免文档继续指向旧快照。

## Execution Order

1. 拉取并确认 Lucide 最新稳定 release 基线。
2. 更新 `lucide-generator` 的 vendored 资源和 `VERSION.txt`。
3. 运行生成任务并定位任何解析失败。
4. 按失败样本最小化修复生成器兼容性。
5. 重新生成 `lucide-core` 全量产物。
6. 更新测试断言与必要的回归样本。
7. 运行相关测试并修复问题。
8. 同步 README/README.zh-CN 的版本说明。
