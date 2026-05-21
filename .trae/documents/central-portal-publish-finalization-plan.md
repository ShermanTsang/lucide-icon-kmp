# Central Portal Snapshot 与正式发布收尾计划

## Summary

- 当前仓库已经完成从“通用远端 Maven 仓库发布”到“仅面向 Maven Central 的双通道发布”切换。
- 已确认根脚本中存在 `publishCentralSnapshot`、`publishCentralRelease`、`validateCentralSnapshotConfig`、`validateCentralReleaseConfig`，并且发布插件已切换到 `com.vanniktech.maven.publish`。
- 当前真正剩余的问题不是发布模型设计，而是把现有方案收尾为“可落地、可理解、可验证”的状态：
  - 明确本地执行发布前的 Android SDK 前置条件；
  - 保持 snapshot 与正式版命令边界清晰；
  - 在不改动现有 Central 发布架构的前提下完成针对性验证。

## Current State Analysis

- `build.gradle.kts`
  - 已采用 `com.vanniktech.maven.publish` 插件。
  - 已保留统一的 `group`、`version`、POM metadata、artifactId 映射与可发布模块集合。
  - 已使用 `mavenCentralUsername` / `mavenCentralPassword` 与 `SIGNING_KEY_BASE64` / `SIGNING_PASSWORD` 兼容桥接。
  - 已通过根任务区分 snapshot 与正式版发布：
    - `publishCentralSnapshot`
    - `publishCentralRelease`
    - `validateCentralSnapshotConfig`
    - `validateCentralReleaseConfig`
- `gradle/libs.versions.toml`
  - 已引入 `com.vanniktech.maven.publish`，版本为 `0.34.0`，与当前 Gradle Wrapper 兼容。
- `gradle.properties`
  - 已包含 Central 发布相关静态配置，以及 Central 所需的 POM/SCM 元数据。
  - 当前默认版本仍为 `0.1.0-SNAPSHOT`，与 snapshot 发布入口匹配。
- `README.md` 与 `README.zh-CN.md`
  - 已改为区分 snapshot 与正式发布两套命令入口。
  - 已说明 `.secrets` 不会被 Gradle 自动读取。
  - 已说明 Windows 对完整 Apple variants 不构成保证，完整 Apple 制品仍建议在 macOS 上发布。
- `lucide-core/build.gradle.kts` 与 `lucide-compose/build.gradle.kts`
  - 两个可发布库模块都包含 Android target。
  - 仓库根目录当前不存在 `local.properties`，说明本机若未设置 `ANDROID_HOME` 或等效 SDK 路径，完整发布任务会在 Android 任务图解析阶段失败。
- 结论
  - 用户要求的“snapshot 走 snapshots、正式版走 Central Portal”的结构性调整已经落地。
  - 执行阶段应聚焦“补齐运行前提说明 + 做一次面向当前环境的发布路径验证”，而不是重新设计发布架构。

## Proposed Changes

### 1. `README.md`

- 调整内容：
  - 在现有发布章节中新增或强化“本地发布前置条件”说明。
  - 明确指出 `publishCentralSnapshot` / `publishCentralRelease` 会解析 Android publication，因此本地需要预先配置 Android SDK。
  - 明确推荐两种可接受的本地 SDK 注入方式：
    - 根目录 `local.properties` 中配置 `sdk.dir=...`
    - 通过 `ANDROID_HOME` 或等效环境变量配置 SDK
  - 保留现有 Central token、签名、namespace、macOS Apple variants 说明，不改变已有 Central-only 文档结构。
- 原因：
  - 当前 README 已讲清发布目标与凭据来源，但尚未把“Android SDK 是完整发布任务的本地前置条件”表达得足够显式。
  - 这正是当前本地执行 `publishCentralSnapshot` / `publishCentralRelease` 时最先遇到的现实阻塞点。
- 实施方式：
  - 不改动命令入口，不改动凭据命名，不改动发布模型。
  - 仅补充操作性说明，使文档与当前构建行为完全一致。

### 2. `README.zh-CN.md`

- 调整内容：
  - 与英文 README 保持等效更新。
  - 补充“本地发布前需要先配置 Android SDK，否则会在 Android 发布任务解析阶段失败”的中文说明。
  - 明确 `local.properties` 与环境变量两种配置方式。
- 原因：
  - 中文文档已覆盖 Central Portal 逻辑，但还缺少对当前本机执行门槛的直接说明。
- 实施方式：
  - 保持现有 snapshot / 正式版章节结构不变，只增加前置条件与失败原因说明。

### 3. `build.gradle.kts`

- 本次执行默认不做结构性改动。
- 原因：
  - 当前根发布逻辑已经满足用户关于 snapshot 与正式发布的方案调整要求。
  - Android SDK 缺失是环境前置条件问题，不适合通过提交仓库内固定 `local.properties` 来“修复”。
  - 若后续验证发现发布任务命名、依赖链或凭据桥接与现状不一致，再做最小修复；否则保持不变。
- 实施方式：
  - 先以现状为准进行验证。
  - 只有在验证发现真实脚本缺陷时，才进行最小范围修正。

### 4. `gradle.properties`

- 本次执行默认不做改动。
- 原因：
  - 当前 `GROUP`、`VERSION_NAME`、Central 发布开关、SCM metadata 已经与 Central-only 方案对齐。
  - 正式发布时是否切换为非 `-SNAPSHOT` 版本，应由真实发布动作前再设置，而不是在当前计划阶段提前改动仓库默认版本。

### 5. 验证流程

- 将执行以下验证，确认当前方案对“snapshot / 正式版 / 当前 Windows 环境”三者的边界表达清楚且行为符合预期：
  - 确认发布任务仍然存在且命名未漂移：
    - `.\gradlew.bat tasks --group publishing`
  - 确认 snapshot 校验逻辑仍有效：
    - `.\gradlew.bat validateCentralSnapshotConfig`
  - 确认正式版校验逻辑仍有效：
    - `.\gradlew.bat validateCentralReleaseConfig`
  - 在当前环境下复核完整发布入口的实际阻塞点是否仍为 Android SDK 前置条件，而不是发布脚本回退或 Central 配置缺失。
- 说明：
  - 该验证以“发布路径是否正确、失败点是否符合预期”为目标，不以实际上传到 Central 为前提。
  - 真实上传仍取决于本地凭据、签名、Android SDK、namespace 状态，以及是否在 macOS 上执行完整 Apple variants 发布。

## Assumptions & Decisions

- 决策：不再重新设计发布架构，继续沿用现有 Central-only 双通道方案。
- 决策：snapshot 入口保持为 `publishCentralSnapshot`，正式版入口保持为 `publishCentralRelease`。
- 决策：正式版继续采用 Central Portal 对应方式，并保持自动发布策略。
- 决策：继续保留当前凭据兼容层：
  - `mavenCentralUsername` / `mavenCentralPassword`
  - `ORG_GRADLE_PROJECT_*`
  - `MAVEN_CENTRAL_USERNAME` / `MAVEN_CENTRAL_PASSWORD`
  - `SIGNING_KEY_BASE64` / `SIGNING_PASSWORD`
- 决策：不在仓库中添加 `local.properties`，因为该文件是机器相关配置，不应作为通用仓库内容提交。
- 假设：`com.shermant` 对应 namespace 已由用户在 Central Portal 侧完成验证。
- 假设：若需要完整 Apple artifacts，最终正式发布仍应在 macOS 主机执行。

## Verification Steps

- 文档验证
  - 确认 `README.md` 明确说明 Android SDK 是完整发布任务的本地前置条件。
  - 确认 `README.zh-CN.md` 与英文文档保持一致。
  - 确认文档仍然清楚区分 snapshot 与正式版命令入口，没有回退到通用仓库 URL 叙述。
- 任务验证
  - 确认 `tasks --group publishing` 能列出：
    - `publishCentralSnapshot`
    - `publishCentralRelease`
    - `validateCentralSnapshotConfig`
    - `validateCentralReleaseConfig`
  - 在默认 `VERSION_NAME=0.1.0-SNAPSHOT` 下：
    - `validateCentralSnapshotConfig` 应执行到 Central 凭据校验逻辑。
    - `validateCentralReleaseConfig` 应因版本模式不匹配或缺少正式发布条件而失败。
  - 完整发布入口在未配置 Android SDK 的环境中，应体现为 Android SDK 前置条件问题，而不是 Central-only 方案缺失。
- 质量验证
  - 若对文档有修改，检查改动文件没有明显格式或内容错误。
  - 若验证阶段发现脚本存在真实缺陷，再针对相关文件做最小修复，并补充对应回归验证。
