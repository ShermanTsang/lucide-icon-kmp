# Terminal#447-795 失败定位与修复计划

## Summary

- 目标：定位 `Terminal#447-795` 中本地 `act` 执行 `publish-maven.yml` 失败原因，并给出保持“远端发布严格校验”前提下的修复方案。
- 结论：失败发生在工作流的 `Validate publish configuration` 步骤，原因是运行环境中缺少 `MAVEN_REPOSITORY_URL`，导致工作流在进入 Gradle `publish` 前直接退出。
- 修复方向：不放宽校验逻辑；改为增强工作流报错信息，并补齐本地 `act` 运行所需的说明与示例，避免再次因未注入 `.env.publish` / `.secrets.publish` 而失败。

## Current State Analysis

### 终端失败现状

- 通过终端日志确认，失败步骤为 `.github/workflows/publish-maven.yml` 中的 `Main Validate publish configuration`。
- 终端关键报错为：`MAVEN_REPOSITORY_URL is required.`。
- 失败发生在执行 `bash ./gradlew publish -PlocalPublish=true` 之前，说明当前问题不是 Gradle 发布逻辑本身，而是工作流前置环境校验未通过。

### 已确认的仓库实现

- `.github/workflows/publish-maven.yml` 当前会强制要求：
  - `MAVEN_REPOSITORY_URL`
  - 用户名/密码成对出现
  - 签名密钥/密码成对出现
- 根构建脚本 `build.gradle.kts` 已支持：
  - 从环境变量读取 `MAVEN_REPOSITORY_URL`、`MAVEN_USERNAME`、`MAVEN_PASSWORD`
  - 从环境变量读取 `SIGNING_KEY`、`SIGNING_PASSWORD`
  - 始终配置 `mavenLocal()`，在提供远端仓库地址时再附加远端仓库
- `lucide-core`、`lucide-compose`、`sample-compose` 中的 `localPublish` / `ACT=true` 仅用于跳过 iOS target，不会绕过发布配置校验。
- `.env.publish.example` 与 `.secrets.publish.example` 已存在，但仓库内没有面向 `act` 的明确执行说明，也没有在失败提示中指出应如何注入这些文件。

### 约束与决策

- 用户已明确要求：`act` 本地运行时仍保持远端发布严格校验，不改为本地兜底发布。
- 因此本次修复不能通过“跳过 `MAVEN_REPOSITORY_URL` 校验”来解决。

## Proposed Changes

### 1. 更新 `.github/workflows/publish-maven.yml`

- 目的：保留严格校验，同时把当前模糊失败改成可直接操作的错误提示。
- 修改内容：
  - 在 `Validate publish configuration` 的各类 `echo` 报错中补充本地 `act` 运行提示。
  - 当检测到缺少 `MAVEN_REPOSITORY_URL`、用户名/密码不成对、签名参数不成对时，输出明确的下一步操作建议：
    - 需要提供 `.env.publish` / `.secrets.publish`
    - 本地 `act` 运行时需要通过 `--env-file` 和 `--secret-file` 注入
  - 保持现有 `exit 1` 行为不变，避免弱化 CI 约束。
- 原因：当前日志只显示“缺少变量”，但没有指出仓库里已经存在示例文件，也没有说明 `act` 默认不会自动读取这些文件。

### 2. 更新 `.env.publish.example`

- 目的：让环境变量示例文件不仅描述“填什么”，还描述“怎么被 `act` 使用”。
- 修改内容：
  - 在文件顶部添加简短注释，说明：
    - 复制为 `.env.publish`
    - 通过 `act --env-file .env.publish` 传入
  - 明确 `MAVEN_REPOSITORY_URL` 在远端发布校验中是必填项。
- 原因：当前示例文件只列出变量，没有把“复制并传入 act”这一步显式写出来。

### 3. 更新 `.secrets.publish.example`

- 目的：补齐凭据类变量的本地注入说明。
- 修改内容：
  - 在文件顶部添加简短注释，说明：
    - 复制为 `.secrets.publish`
    - 通过 `act --secret-file .secrets.publish` 传入
  - 说明签名字段是按需填写，但若填写其中一个则必须成对提供。
- 原因：当前示例文件虽然列出了变量，但没有与工作流校验条件形成清晰闭环。

### 4. 更新 `README.md`

- 目的：把“如何本地验证发布工作流”放到用户最容易看到的位置。
- 修改内容：
  - 增加一个简短的发布验证章节，说明本地使用 `act` 验证 `publish-maven.yml` 的前提：
    - 复制示例文件
    - 填入真实远端仓库地址与凭据
    - 使用 `--env-file` / `--secret-file`
  - 明确说明：本地 `act` 运行与远端 CI 一样，会严格校验远端发布配置。
- 原因：当前 README 没有发布验证入口，容易让人误以为 `ACT=true` 会自动切换到本地发布模式。

## Assumptions & Decisions

- 假设 `Terminal#447-795` 的失败场景是本地使用 `act` 触发 `.github/workflows/publish-maven.yml`。
- 假设用户期望修复“本地调试流程不清晰导致的失败”，而不是改造为本地仓库发布模式。
- 决策：不修改 `build.gradle.kts` 的仓库选择逻辑，不新增本地兜底仓库，不改变现有严格校验语义。
- 决策：修复重点放在工作流可诊断性和本地使用说明，确保下次失败时日志能直接指向正确操作。

## Verification Steps

### 日志与行为验证

- 检查 `.github/workflows/publish-maven.yml` 的校验步骤仍会在缺少 `MAVEN_REPOSITORY_URL` 时失败。
- 检查失败日志新增了针对 `act` 的可执行提示，而不是仅输出笼统缺参信息。
- 检查用户名/密码和签名参数不成对时，也会输出同样风格的说明。

### 文档验证

- 检查 `.env.publish.example` 与 `.secrets.publish.example` 中的说明，与 README 中的 `act` 用法保持一致。
- 检查 README 中的命令示例与工作流文件名、示例文件名一致，没有路径或参数拼写错误。

### 验收标准

- 再次遇到相同缺参场景时，开发者仅阅读终端日志即可知道失败原因和下一步操作。
- 远端发布严格校验仍然保留，不会因为本地 `act` 运行而被绕过。
- 本地发布工作流的说明形成闭环：工作流报错、示例文件、README 三处信息一致。
