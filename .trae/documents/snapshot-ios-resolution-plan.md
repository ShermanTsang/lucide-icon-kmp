# 公共 SNAPSHOT iOS 变体解析失败修复计划

## Summary

- 目标：解决外部 Kotlin Multiplatform 项目通过 Maven 仓库引用 `0.2.0-SNAPSHOT` 时，`iosArm64` / `iosSimulatorArm64` 变体无法解析的问题。
- 成功标准：
  - 公共 SNAPSHOT 发布后，消费端能够解析 `iosArm64` 与 `iosSimulatorArm64` 相关制品。
  - Windows 本地不再允许发布会缺失 Apple variants 的公共 SNAPSHOT，避免继续产出“元数据可见但 iOS 制品缺失”的坏快照。
  - 文档明确区分“内部模块名”和“对外 Maven 坐标”，并同步当前 `0.2.0-SNAPSHOT` 版本说明。
- 约束：
  - 当前仓库运行环境是 Windows 11。
  - 仓库内两个可发布库模块都声明了 iOS targets，因此公开发布必须覆盖 Apple variants。
  - 当前仓库没有任何 GitHub Actions / CI 发布工作流，只有本地 Gradle 与 PowerShell 发布入口。

## Current State Analysis

- `gradle.properties`
  - 当前版本已经是 `VERSION_NAME=0.2.0-SNAPSHOT`。
  - 当前 group 为 `com.shermant`。
- `build.gradle.kts`
  - 仅 `:lucide-core` 与 `:lucide-compose` 会参与发布。
  - 对外 artifact base id 已映射为：
    - `:lucide-core` -> `lucide-icon-kmp`
    - `:lucide-compose` -> `lucide-icon-kmp-compose`
  - 根任务入口只有 `publishCentralSnapshot` 与 `publishCentralRelease`。
- `lucide-core/build.gradle.kts`
  - 声明了 `iosX64()`、`iosArm64()`、`iosSimulatorArm64()`。
- `lucide-compose/build.gradle.kts`
  - 同样声明了 `iosX64()`、`iosArm64()`、`iosSimulatorArm64()`。
- `README.md` / `README.zh-CN.md`
  - Maven 依赖示例仍写死为 `0.1.0-SNAPSHOT`，与当前仓库版本不一致。
  - 已明确说明 Windows 远端发布不保证完整 Apple variants。
- `scripts/publish-central.ps1`
  - 当前只负责把 `.secrets` 映射到 Gradle 属性，然后直接执行发布任务。
  - 没有在脚本层面阻止 Windows 发出对外公共 SNAPSHOT。
- 用户报错特征
  - 消费端报错集中在 `*-iosarm64` 和 `*-iossimulatorarm64`，说明问题不是通用元数据不可见，而是 Apple 目标制品没有在公共 SNAPSHOT 仓库中正确可用。
  - 报错里的 artifact id 为 `lucide-core-iosarm64` / `lucide-compose-iosarm64`，这与当前 README 公布的对外坐标不一致，说明还需要显式校验并澄清“内部模块名 vs 对外发布坐标”的边界。

## Proposed Changes

### 1. `build.gradle.kts`

- 新增公共发布前置校验，作为 `publishCentralSnapshot` 与 `publishCentralRelease` 的硬前提。
- 校验内容：
  - 当前发布是否包含 Apple targets。
  - 当前宿主机是否为 macOS。
  - 当前生成的 publication / artifact id 是否匹配公开坐标基名，而不是回退到内部模块名。
- 实施方式：
  - 提取一个根级校验任务，例如 `validateApplePublicationHost`，当可发布模块声明了 iOS targets 且宿主机不是 macOS 时直接失败。
  - 提取一个根级 publication 命名校验任务，例如 `validatePublishedCoordinates`，检查生成 publication 的 artifact id 是否以 `lucide-icon-kmp` / `lucide-icon-kmp-compose` 为基名，而不是 `lucide-core` / `lucide-compose`。
  - 让 `publishCentralSnapshot` 与 `publishCentralRelease` 依赖以上校验任务，保证坏快照无法继续发布。
- 原因：
  - 当前仓库已经把 iOS targets 作为对外支持平台声明出来，发布链路必须与声明一致。
  - 用户当前遇到的是“公共快照被消费时缺 Apple variants”，最佳修复不是继续允许 Windows 发布，而是从源头阻止不完整发布。
  - 针对 publication 命名增加自动校验，可以防止再次发布出基于内部模块名的错误变体坐标。

### 2. `scripts/publish-central.ps1`

- 增加与 Gradle 一致的宿主机预检，并在 Windows 下给出清晰失败信息。
- 实施方式：
  - 在调用 `gradlew.bat` 前检测当前平台。
  - 当任务为 `publishCentralSnapshot` 或 `publishCentralRelease` 时，直接提示“公共 Apple variants 发布必须在 macOS 或 macOS CI 上执行”。
  - 保留现有 `.secrets` 到 Gradle 属性的桥接逻辑不变。
- 原因：
  - 让 Windows 用户在脚本入口就收到明确反馈，而不是等到外部消费失败后再排查。
  - 与 README 中现有“Windows 不保证完整 Apple variants”的事实说明保持一致，并把它升级为真正可执行的约束。

### 3. `.github/workflows/publish-central.yml`

- 新增 macOS 发布工作流，作为公共 SNAPSHOT 与正式版的标准发布入口。
- 实施方式：
  - 使用 `workflow_dispatch` 触发。
  - 运行环境固定为 `macos-latest`。
  - 配置 JDK 17 与 Android SDK。
  - 从 GitHub Secrets 注入：
    - `MAVEN_CENTRAL_USERNAME`
    - `MAVEN_CENTRAL_PASSWORD`
    - `SIGNING_KEY_BASE64`
    - `SIGNING_PASSWORD`
  - 根据输入参数执行：
    - `publishCentralSnapshot`
    - 或 `publishCentralRelease`
- 原因：
  - 当前仓库没有任何 CI 发布入口，而用户目标是“公共 SNAPSHOT 可用”。
  - 既然 Windows 不适合作为 Apple variants 的公开发布宿主机，就需要把仓库内的标准发布路径补到 macOS CI。
  - 将发布入口收敛到单一 macOS 工作流，能够稳定产出完整 Apple 制品，并降低本地环境差异。

### 4. `README.md`

- 修正文档中的公开依赖说明与发布路径说明。
- 实施方式：
  - 将 `0.1.0-SNAPSHOT` 示例更新为当前 `0.2.0-SNAPSHOT`，或改为“以 `VERSION_NAME` 为准”的说明，避免再次过期。
  - 明确对外公开依赖坐标只有：
    - `com.shermant:lucide-icon-kmp`
    - `com.shermant:lucide-icon-kmp-compose`
  - 增加一段故障排查：
    - 如果消费者看到 `lucide-core-iosarm64` / `lucide-compose-iosarm64` 这类坐标，要先确认引用的不是内部模块名，也要确认当前 SNAPSHOT 已经通过 macOS 发布链路重新发布。
  - 将公共 SNAPSHOT 发布入口说明为 macOS CI / macOS 主机，而不是 Windows PowerShell。
- 原因：
  - 当前文档版本号与真实仓库版本不一致。
  - 用户报错中的坐标与 README 公布坐标不一致，文档必须明确“模块名不等于公开 Maven 坐标”。

### 5. `README.zh-CN.md`

- 与英文 README 做完全等效的修订。
- 实施方式：
  - 同步更新当前 SNAPSHOT 版本说明。
  - 明确公开坐标与内部模块名的区别。
  - 明确公共 SNAPSHOT 的标准发布路径是 macOS 主机或 macOS CI。
  - 增加针对 `lucide-core-iosarm64` / `lucide-compose-iosarm64` 报错的中文排查说明。
- 原因：
  - 当前用户就是中文语境，中文文档必须把问题边界和发布约束讲清楚。

### 6. `LOCAL_USAGE.md`

- 保持“本地联调优先使用 composite build”的主线不变，只补一个清晰兜底说明。
- 实施方式：
  - 在常见问题中补充说明：如果公共 SNAPSHOT 尚未通过 macOS 发布链路更新，iOS 消费端不要依赖旧 SNAPSHOT 进行验证，优先使用 `includeBuild`。
- 原因：
  - 这不是本次主修复路径，但能为仓库维护者和使用者提供可操作的临时绕行方案。

## Assumptions & Decisions

- 决策：公共 SNAPSHOT 想要对 iOS 消费端可用，必须从 macOS 宿主机发布；Windows 本地发布不再视为支持路径。
- 决策：仓库继续保留当前 iOS targets 声明，不通过“删 iOS targets”来规避问题。
- 决策：公开 Maven 坐标以根脚本定义的 artifact id 映射为准，不以内部项目名 `lucide-core` / `lucide-compose` 对外暴露。
- 决策：CI 工作流采用单文件、手动触发、macOS 固定宿主机方案，避免引入不必要的自动发布复杂度。
- 假设：Central Portal namespace、token、签名配置已经由仓库维护者准备好。
- 假设：当前用户贴出的错误来自一个已经发布出去但不完整的 `0.2.0-SNAPSHOT`，而不是纯本地 composite build 问题。

## Verification Steps

- 发布前置校验验证
  - 在 Windows 上执行 `publishCentralSnapshot` 时，应在新的宿主机校验阶段直接失败，并明确提示 Apple variants 需要 macOS。
  - 在 Windows 上执行 `publishCentralRelease` 时，也应得到相同的前置失败，而不是继续发布。
- publication 命名验证
  - 校验任务应能识别并阻止 `lucide-core*` / `lucide-compose*` 这类内部模块名前缀对外发布。
  - 校验任务应允许 `lucide-icon-kmp*` / `lucide-icon-kmp-compose*` 这类公开坐标基名通过。
- macOS 发布链路验证
  - 在 GitHub Actions 的 `macos-latest` 上运行新工作流，`publishCentralSnapshot` 应能进入正常上传流程。
  - 若执行正式版，同一工作流应调用 `publishCentralRelease`。
- 消费端验证
  - 在一个干净的外部 KMP 工程中配置：
    - `maven("https://central.sonatype.com/repository/maven-snapshots/")`
    - `google()`
    - `mavenCentral()`
  - 使用公开坐标：
    - `implementation("com.shermant:lucide-icon-kmp:0.2.0-SNAPSHOT")`
    - `implementation("com.shermant:lucide-icon-kmp-compose:0.2.0-SNAPSHOT")`
  - 验证 `iosArm64` 与 `iosSimulatorArm64` 相关 source set 的依赖解析通过。
- 文档验证
  - `README.md` 与 `README.zh-CN.md` 中的版本号、公开坐标、发布入口、故障排查说明保持一致。
  - `LOCAL_USAGE.md` 中的本地联调兜底说明与主文档不冲突。
