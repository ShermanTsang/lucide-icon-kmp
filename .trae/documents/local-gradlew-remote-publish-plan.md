# 本地 Gradlew 远端发布简化计划

## Summary

- 目标：移除当前基于 GitHub Actions 与 `act` 的发布流程，改为在本地直接通过 Gradle Wrapper 执行远端 Maven 发布。
- 发布入口：新增根任务 `publishRemote`，作为推荐的本地发布命令；不再把 GitHub Actions 工作流作为正式发布入口。
- 校验策略：把原先工作流里的远端发布参数校验迁移到根 `build.gradle.kts`，并只对远端发布任务生效。
- 平台说明：保留现有 Kotlin Multiplatform 发布配置，但在文档中明确说明 Windows 本地发布不保证 Apple variants 完整产出。

## Current State Analysis

- 根发布配置已经存在于 `build.gradle.kts`：
  - 为 `:lucide-core` 和 `:lucide-compose` 统一应用 `maven-publish` 与 `signing`。
  - 已支持从 Gradle properties 或环境变量读取 `MAVEN_REPOSITORY_URL`、`MAVEN_USERNAME`、`MAVEN_PASSWORD`、`SIGNING_KEY_BASE64`、`SIGNING_PASSWORD`。
  - 已配置 `mavenLocal()` 和可选远端仓库，且已设置统一 POM 元数据。
- 当前 GitHub Actions 文件 `.github/workflows/publish-maven.yml` 主要承担两件事：
  - 在 CI 中校验远端仓库地址、用户名密码成对性、签名参数成对性。
  - 在 `macos-latest` 上执行 `./gradlew publish`。
- 当前 `.actrc` 仅用于把本地 `act` 绑定到 `.env` 与 `.secrets`，属于即将移除的发布链路配套文件。
- 当前文档存在与现状不一致或即将过时的描述：
  - `README.md` 与 `README.zh-CN.md` 仍以 GitHub Actions / `act` 为发布校验入口。
  - `LOCAL_USAGE.md` 仍写明仓库没有现成 `maven-publish` / `publishToMavenLocal` 配置，这与现有根 `build.gradle.kts` 不一致。

## Proposed Changes

### 1. `build.gradle.kts`

- 新增一个根级发布校验任务，例如 `validateRemotePublishConfig`。
- 校验内容直接承接现有工作流规则：
  - `MAVEN_REPOSITORY_URL` 必填。
  - `MAVEN_USERNAME` 与 `MAVEN_PASSWORD` 必须成对出现。
  - `SIGNING_KEY_BASE64` 与 `SIGNING_PASSWORD` 必须成对出现。
- 校验失败时给出明确错误信息，并使用统一、清晰的变量命名，保持与现有 provider 命名一致。
- 新增根任务 `publishRemote`：
  - 该任务依赖 `validateRemotePublishConfig`。
  - 该任务聚合两个发布模块的远端发布任务，而不是本地仓库任务。
  - 推荐命令改为 Windows 下执行 `.\gradlew.bat publishRemote`，同时保持跨平台文档可写 `./gradlew publishRemote`。
- 将远端发布校验挂到真正的远端 Maven 发布任务上，避免误伤 `publishToMavenLocal` 或其他非远端任务。
  - 实现方式：对 `PublishToMavenRepository` 类型任务进行筛选，仅当目标仓库为远端仓库时依赖 `validateRemotePublishConfig`。
  - 这样即使用户直接调用底层远端发布任务，也会触发同一套参数校验。
- 保持现有 `maven-publish`、`signing`、POM 元数据与环境变量读取逻辑，尽量复用已有代码，不改动模块级发布结构。

### 2. `.github/workflows/publish-maven.yml`

- 删除该工作流文件。
- 原因：发布入口改为本地 Gradle；工作流中的校验与执行职责将完全迁移到 `build.gradle.kts`。

### 3. `.actrc`

- 删除该文件。
- 原因：移除 GitHub Actions 本地模拟执行后，`act` 不再是发布链路的一部分。

### 4. `README.md`

- 替换当前 “Validate Publish Workflow Locally” 相关章节，改为本地 Gradle 远端发布说明。
- 新内容应覆盖：
  - 本地远端发布所需参数及其来源：优先说明可使用 Gradle properties，也兼容环境变量。
  - 推荐命令：`./gradlew publishRemote`。
  - 可选命令：如果需要，也可直接执行 Gradle 底层远端发布任务，但文档主入口只推荐 `publishRemote`。
  - 参数缺失时由 Gradle 在发布前直接失败，而不是由 GitHub Actions / `act` 拦截。
  - 明确说明 Windows 本地流程不保证 Apple variants 完整发布；若需要完整 Apple 制品，需要 macOS 主机执行相同 Gradle 任务。
- 同时把前文中 “publish workflow still reads ...” 之类偏 CI 的表述改为 “publish configuration reads ...”。

### 5. `README.zh-CN.md`

- 与英文 README 做同等改造，保持中英文文档结构和信息一致。
- 重点同步以下内容：
  - 本地 `Gradlew` 远端发布入口。
  - Gradle 层面的发布前校验规则。
  - Windows 与 Apple variants 的限制说明。
  - 删除对 `act`、`.env`、`.secrets`、GitHub Actions 校验阶段的依赖描述。

### 6. `LOCAL_USAGE.md`

- 修正与当前仓库状态冲突的描述，至少更新以下内容：
  - 删除“当前没有 ready-to-use `maven-publish` 配置”的表述。
  - 删除“如果要 `publishToMavenLocal` 需先自行添加 publish 配置”的过时说明。
- 保持文档主旨仍为“本地联调优先推荐 composite build”，不要把它重写成发布指南。
- 仅补充一句说明：仓库现在已具备发布配置，但本地开发联调仍优先推荐 `includeBuild`，因为无需发布和安装制品即可直接验证源码修改。

## Assumptions & Decisions

- 假设用户所说的“本地 GDEW”指本地 `Gradlew` / `gradlew.bat` 命令入口。
- 决策：正式推荐入口为新增根任务 `publishRemote`，而不是继续让用户直接记忆底层 publish 任务名。
- 决策：本次仅保留远端 Maven 发布主流程，不把 `publishToMavenLocal` 作为本次简化目标的主入口。
- 决策：保留现有环境变量与 Gradle properties 的双通道读取，避免破坏已有配置兼容性。
- 决策：不修改发布模块集合，不新增或移除 `:lucide-core` 与 `:lucide-compose` 的发布范围。
- 决策：不尝试在 Windows 上人为裁剪 Apple 目标；仅在文档中明确宿主平台差异与产物限制。

## Verification Steps

- 静态检查：
  - 确认仓库内不再存在 `.github/workflows/publish-maven.yml` 与 `.actrc`。
  - 确认 `README.md`、`README.zh-CN.md`、`LOCAL_USAGE.md` 不再包含 `act` 发布流程和过时的 `maven-publish` 缺失描述。
- Gradle 任务验证：
  - 执行 `.\gradlew.bat publishRemote` 且不提供 `MAVEN_REPOSITORY_URL`，应在 Gradle 校验阶段直接失败并输出明确错误。
  - 仅提供 `MAVEN_USERNAME` 或仅提供 `MAVEN_PASSWORD` 时，应在 Gradle 校验阶段失败。
  - 仅提供 `SIGNING_KEY_BASE64` 或仅提供 `SIGNING_PASSWORD` 时，应在 Gradle 校验阶段失败。
- 正向验证：
  - 在提供完整远端仓库参数后执行 `.\gradlew.bat publishRemote`，应进入远端发布任务链。
  - 如当前主机缺乏 Apple 平台能力，文档需明确这是宿主环境限制，而不是发布脚本缺陷。
- 质量检查：
  - 对修改后的 `build.gradle.kts`、`README.md`、`README.zh-CN.md`、`LOCAL_USAGE.md` 运行诊断检查，确认未引入语法或明显格式错误。
