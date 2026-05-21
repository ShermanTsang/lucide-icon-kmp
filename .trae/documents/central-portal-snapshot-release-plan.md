# 基于 Snapshot 与 Central Portal 正式发布的方案调整计划

## Summary

- 目标：将当前“通用远端 Maven 仓库”发布模型调整为仅面向 Maven Central 的双通道发布模型。
- Snapshot 发布：通过 Gradle 直接发布到 Central snapshots。
- 正式发布：通过 Central Portal 对应的 Gradle 发布方式完成上传，并启用自动发布。
- 命名空间：继续使用当前 `GROUP=com.shermant`，前提是该 namespace 已在 Central Portal 完成验证。
- 方案选择：采用 `vanniktech/gradle-maven-publish-plugin` 作为 Central Portal 兼容发布插件，替换当前手写的通用 `publishRemote` 逻辑。

## Current State Analysis

- 当前根发布逻辑集中在 `build.gradle.kts`：
  - 手动为 `:lucide-core` 和 `:lucide-compose` 应用 `maven-publish` 与 `signing`。
  - 通过 `mavenRepositoryUrl` / `MAVEN_REPOSITORY_URL` 动态注入一个名为 `Remote` 的通用仓库。
  - 提供 `validateRemotePublishConfig` 与 `publishRemote` 任务，要求传入仓库 URL、用户名、密码和签名信息。
- 当前设计适合：
  - 私有 Nexus / Artifactory / 任意远端 Maven 仓库。
  - Central snapshots 仓库地址这种“直接 repository URL 上传”的场景。
- 当前设计不适合：
  - Maven Central 正式发布，因为 Central Portal 正式版并不是“给一个 repository URL 后直接上传”即可完成，而是基于 Portal 的发布流程。
- 当前文档 `README.md` / `README.zh-CN.md` 仍以 `publishRemote` 为主入口，并把 `MAVEN_REPOSITORY_URL` 作为核心发布参数。
- 当前 `gradle.properties` 中已有基础 POM 信息：
  - `GROUP=com.shermant`
  - `VERSION_NAME=0.1.0-SNAPSHOT`
  - `POM_URL`、license、developer 基础信息
- 当前 POM 元数据仍有缺口：
  - `build.gradle.kts` 只设置了 `scm.url`，没有 `scm.connection` 和 `scm.developerConnection`
  - Central 要求中明确要求完整 SCM 信息
- 当前仓库没有引入任何 Central Portal Gradle 插件，也没有 Dokka / javadoc / 自动 sources-javadoc 发布增强配置。
- 当前 `lucide-core/build.gradle.kts` 与 `lucide-compose/build.gradle.kts` 都是标准 KMP 库模块，没有额外发布插件或文档产物逻辑。
- 当前 `.gitignore` 忽略 `.env` 与 `.secrets`，但仓库里没有受版本管理的样例文件；并且现在已经没有 `act` / GitHub Actions 来自动消费这些文件，因此后续文档需要明确 Gradle 凭据的实际注入方式。

## Proposed Changes

### 1. `gradle/libs.versions.toml`

- 新增 `com.vanniktech.maven.publish` 插件版本定义与插件别名。
- 原因：
  - Kotlin 官方 Maven Central 教程直接采用该插件。
  - 该插件已适配 Central Portal，并且会自动处理 KMP 常见的 sources / javadoc 发布需求。
  - 对当前 KMP 库项目比手写 Portal API 对接更稳妥，也比继续维护通用仓库 URL 模式更符合目标。

### 2. `build.gradle.kts`

- 移除当前“通用远端仓库发布”相关实现：
  - 删除 `repositoryUrlProvider`
  - 删除 `repositoryUsernameProvider`
  - 删除 `repositoryPasswordProvider`
  - 删除 `validateRemotePublishConfig`
  - 删除 `publishRemote`
  - 删除对 `PublishToMavenRepository` 的 `Remote` 仓库绑定逻辑
- 保留并复用现有的结构化配置：
  - `publishableLibraryProjects`
  - `publishedArtifactIds`
  - `publishedPomNames`
  - 现有 `group` / `version` / `description` provider 逻辑
  - 现有 POM 元数据 provider 模式
- 引入 Vanniktech 发布插件并在可发布子模块上统一配置：
  - 将 Central 发布逻辑改为对 `:lucide-core` 与 `:lucide-compose` 应用该插件
  - 继续由根脚本统一下发 artifactId 与 POM 信息，避免模块脚本重复
- 将 POM 配置补全到 Central 要求：
  - 增加 `POM_SCM_CONNECTION`
  - 增加 `POM_SCM_DEV_CONNECTION`
  - 视需要补充 `POM_DEVELOPER_URL`
  - 保留现有 license、description、url、developer id/name
- 调整签名与凭据读取模型：
  - 以 Central 发布插件要求的属性为主，例如 `mavenCentralUsername` / `mavenCentralPassword`
  - 保留对当前单行 `SIGNING_KEY_BASE64` + `SIGNING_PASSWORD` 的兼容桥接，将 Base64 解码后喂给插件使用的内存签名属性
  - 这样既保留现有单行密钥习惯，也让发布机制切换到 Central 兼容模式
- 新增根级别的模式化发布校验任务，明确区分 snapshot 与正式版：
  - `validateCentralSnapshotConfig`
    - 要求版本以 `-SNAPSHOT` 结尾
    - 要求 Central 用户 token 已提供
    - 签名保持可选，但如提供则必须成对
  - `validateCentralReleaseConfig`
    - 要求版本不以 `-SNAPSHOT` 结尾
    - 要求 Central 用户 token 已提供
    - 要求签名信息必填且成对
- 新增两个清晰的根命令入口：
  - `publishCentralSnapshot`
    - 依赖 `validateCentralSnapshotConfig`
    - 最终触发 Vanniktech 的 snapshot 上传任务
  - `publishCentralRelease`
    - 依赖 `validateCentralReleaseConfig`
    - 最终触发 Vanniktech 的自动发布任务，例如 `publishAndReleaseToMavenCentral`
- 任务命名策略：
  - 文档主入口暴露自定义根任务，避免让用户记插件底层任务名
  - 底层插件任务仍保留可用，但不作为主要说明入口

### 3. `gradle.properties`

- 保留当前 `GROUP=com.shermant` 与 `VERSION_NAME` 配置。
- 新增或补全 Central 相关静态配置项：
  - `mavenCentralPublishing=true`
  - 视实现选择是否加入 `mavenCentralAutomaticPublishing=true`
  - `signAllPublications=true`
  - `POM_SCM_CONNECTION`
  - `POM_SCM_DEV_CONNECTION`
  - 可选：`POM_DEVELOPER_URL`
- 若需要明确自动发布后的等待策略，可增加：
  - `mavenCentralDeploymentValidation=VALIDATED` 或 `PUBLISHED`
- 本次决策：
  - 采用“自动发布”
  - 默认选择较稳妥的验证终态配置，并在文档中明确“构件在门户发布后到 Central 可见仍可能有延迟”

### 4. `README.md`

- 将当前“Publish To A Remote Maven Repository”章节改成 Central 专用发布说明。
- 文档结构调整为两部分：
  - `Publish SNAPSHOTs To Central`
  - `Publish Releases Through Central Portal`
- 新内容应明确：
  - snapshot 与正式版的版本命名规则不同
  - snapshot 推荐命令为 `./gradlew publishCentralSnapshot`
  - 正式版推荐命令为 `./gradlew publishCentralRelease`
  - 正式版要求 namespace 已在 Central Portal 验证完成，并启用了对应发布能力
  - Central Portal 需要 user token，而不是门户登录密码
  - 当前项目继续接受单行 `SIGNING_KEY_BASE64`
  - `.secrets` 不是 Gradle 自动读取文件；推荐使用 `~/.gradle/gradle.properties` 或显式环境变量
  - Windows 本地不保证 Apple variants 完整产物；完整 Apple 制品仍建议在 macOS 主机执行相同发布命令
- 保留已有 snapshot 消费仓库地址说明，但把“读取仓库 URL”改为“snapshot 上传后可从该仓库消费”，不再把其描述成当前发布流程的核心配置项。

### 5. `README.zh-CN.md`

- 与英文 README 做对等调整。
- 重点说明：
  - `snapshot` 与正式版是两套命令入口
  - 正式版走 Central Portal 对应发布方式
  - 自动发布是默认策略
  - `com.shermant` 必须已在 Central Portal 验证
  - `~/.gradle/gradle.properties` / 环境变量才是 Gradle 真实读取入口
  - `.secrets` 如继续使用，只能作为你自己的本地凭据文件，需自行导入环境，Gradle 不会自动加载

### 6. `LOCAL_USAGE.md`

- 不改为发布指南，仍保持“本地联调优先推荐 composite build”的主旨。
- 仅做必要同步：
  - 如涉及“仓库已具备发布配置”的表述，更新为“仓库已具备 Central snapshot / release 发布配置”
  - 不新增 Central Portal 细节，避免文档职责混乱

## Assumptions & Decisions

- 决策：发布范围仅覆盖 Maven Central，不再保留通用远端 Maven 仓库 URL 发布模型。
- 决策：正式版发布采用 Central Portal 对应的 Gradle 方案，并启用自动发布。
- 决策：继续使用 `com.shermant` 作为 `groupId` / namespace，假设该 namespace 已在 Central Portal 验证通过。
- 决策：选用 `com.vanniktech.maven.publish`，原因是：
  - Kotlin 官方教程直接采用它
  - 已适配 Central Portal
  - 对 KMP 的 sources / javadoc 处理更完善
  - 比纯手写 Portal API 更省维护成本
- 决策：保留当前单行 `SIGNING_KEY_BASE64` 思路，通过桥接兼容插件，而不是强制用户改成多行 ASCII armored 密钥配置格式。
- 决策：对用户暴露两个明确命令：
  - `publishCentralSnapshot`
  - `publishCentralRelease`
- 假设：Central Portal 账号、namespace 验证、snapshot 开启、user token 生成这些账号侧前置条件由用户自行完成。
- 假设：正式发布若要产出完整 Apple variants，仍需要在 macOS 主机执行；Windows 只负责本地开发与有限验证说明。

## Verification Steps

- 静态检查：
  - 确认 `build.gradle.kts` 中不再保留 `publishRemote` / `validateRemotePublishConfig` / `MAVEN_REPOSITORY_URL` 通用远端仓库逻辑。
  - 确认 `gradle/libs.versions.toml` 已加入 Central Portal 发布插件版本与别名。
  - 确认 `gradle.properties` 已补齐 Central 所需的 SCM 连接与相关发布属性。
- 文档检查：
  - 确认 `README.md` 与 `README.zh-CN.md` 不再把 `MAVEN_REPOSITORY_URL` 作为正式发布核心配置。
  - 确认两份 README 都明确区分 snapshot 与正式版命令入口。
  - 确认文档说明 `.secrets` 不会被 Gradle 自动消费。
- 任务验证：
  - `VERSION_NAME` 为 `*-SNAPSHOT` 且未提供 Central token 时，`publishCentralSnapshot` 应在自定义校验阶段失败。
  - `VERSION_NAME` 为非 `-SNAPSHOT` 且缺少签名配置时，`publishCentralRelease` 应在自定义校验阶段失败。
  - `VERSION_NAME` 为 `*-SNAPSHOT` 时执行正式版任务，应因版本规则失败。
  - `VERSION_NAME` 为非 `-SNAPSHOT` 时执行 snapshot 任务，应因版本规则失败。
- 正向验证：
  - 当 `VERSION_NAME` 为 `*-SNAPSHOT` 且配置完整时，应能进入 Central snapshots 上传流程。
  - 当 `VERSION_NAME` 为正式版且 token 与签名完整时，应能进入 Central Portal 自动发布流程。
- 质量检查：
  - 对修改后的 `build.gradle.kts`、`gradle.properties`、`README.md`、`README.zh-CN.md`、`LOCAL_USAGE.md` 运行诊断检查，确保没有明显语法或格式错误。
