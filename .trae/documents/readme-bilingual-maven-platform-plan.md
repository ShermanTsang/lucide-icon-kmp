# README 双语与 Maven 仓库信息更新计划

## Summary

- 按当前仓库实现与发布配置，更新英文主文档 `README.md`。
- 新增中文文档 `README.zh-CN.md`，与英文文档保持同等信息密度并互相跳转。
- 在文档中明确区分“库模块支持的平台”与“示例应用当前覆盖的平台”，并补充当前未完全打通或本地发布时受限的平台说明。
- 结合当前 `.env.publish` / 发布工作流配置，更新 Maven 仓库地址与依赖坐标相关说明。

## Current State Analysis

- 仓库当前只有一个英文 `README.md`，没有现成中文 README。
- 根项目发布坐标来自 `build.gradle.kts` 与 `gradle.properties`：
  - `group = com.shermant`
  - `artifactId` 映射为：
    - `:lucide-core` -> `lucide-icon-kmp`
    - `:lucide-compose` -> `lucide-icon-kmp-compose`
  - `version = 0.1.0-SNAPSHOT`
- 当前本地发布环境文件 `.env.publish` 中的 `MAVEN_REPOSITORY_URL` 指向 `https://central.sonatype.com/repository/maven-snapshots/`。
- 发布工作流 `.github/workflows/publish-maven.yml` 会严格校验远端发布配置，缺少 `MAVEN_REPOSITORY_URL` 会直接失败。
- `lucide-core` 与 `lucide-compose` 当前声明的平台 target 为：
  - Android
  - Desktop JVM
  - JS(IR)
  - WasmJs
  - iOS (`iosX64` / `iosArm64` / `iosSimulatorArm64`)
- 但在 `ACT=true` 或 `-PlocalPublish=true` 时，`lucide-core`、`lucide-compose`、`sample-compose` 都会跳过 iOS target。
- `sample-compose` 当前只提供以下示例入口源码：
  - Android
  - Desktop
  - WasmJs
  - iOS
- `sample-compose` 没有单独的 JS(IR) 示例入口，因此 README 需要避免把“库支持 JS”与“示例已提供 JS Demo”混为一谈。
- 现有 README 还存在两处与当前状态不完全一致的内容：
  - 生成目录说明写成了 `io/github/...` 路径，但当前生成包名实际为 `com.shermant.core.generated`
  - 平台支持部分没有区分库模块与示例应用，也没有写明本地发布场景下的 iOS 限制

## Proposed Changes

### 1. 更新 `README.md`

- 调整文档结构，加入中英文互链入口，指向 `README.zh-CN.md`。
- 基于当前仓库内容重写或补强以下章节：
  - 项目概览：准确描述四个模块及其职责。
  - Maven / Dependency 信息：
    - 明确当前 `group` 与两个 artifact 坐标。
    - 标注当前版本为 `0.1.0-SNAPSHOT`。
    - 说明当前本地发布配置中的 Maven 仓库地址为 Sonatype snapshots。
    - 给出典型依赖声明示例，分别覆盖 `lucide-icon-kmp` 与 `lucide-icon-kmp-compose`。
  - Platform Support：
    - 单列“Library targets”说明 core/compose 实际声明的平台。
    - 单列“Sample coverage”说明 sample 当前覆盖 Android/Desktop/Wasm/iOS。
    - 明确说明 sample 当前没有单独 JS(IR) demo。
    - 明确说明本地发布与 `act` 场景会跳过 iOS targets，因此相关产物/构建验证不能视为已在该流程中覆盖。
  - Publish / Local validation：
    - 保留严格远端发布校验说明。
    - 将 Maven 仓库地址说明与 `.env.publish` 当前值保持一致。
  - Generator Flow：
    - 修正生成输出路径描述，使之与 `com.shermant.core.generated` 一致。
- 保留已有使用示例，但检查章节顺序与术语，避免前后不一致。

### 2. 新增 `README.zh-CN.md`

- 按 `README.md` 的最终结构提供中文镜像文档，而不是简化版摘要。
- 与英文文档保持以下信息一致：
  - 模块职责
  - 依赖坐标与仓库地址
  - 平台支持与示例覆盖差异
  - 本地发布跳过 iOS 的限制
  - 生成器与发布验证说明
- 在顶部加入到英文文档 `README.md` 的跳转链接。
- 中文表述采用面向使用者的说明风格，避免逐句生硬翻译，但事实信息必须与英文一致。

## Assumptions & Decisions

- 文档结构采用“英文主 README + 中文 README.zh-CN”的双文件方案。
- 平台说明采用“库支持平台”和“示例覆盖平台”分开展示的方案。
- Maven 仓库地址说明以仓库当前可见配置为依据，即 `.env.publish` 中的 Sonatype snapshots 地址；同时保留其可通过环境变量调整的事实。
- 不在本次任务中修改构建配置、发布流程或示例代码，只更新 README 文档。
- 不扩展到 `LOCAL_USAGE.md` 或其他文档，除非在实施时发现 README 与其交叉引用必须同步修正才能避免明显冲突。

## Verification Steps

- 检查 `README.md` 与 `README.zh-CN.md` 是否都存在且互相链接正确。
- 对照以下文件确认文档事实一致：
  - `build.gradle.kts`
  - `gradle.properties`
  - `.env.publish`
  - `.github/workflows/publish-maven.yml`
  - `lucide-core/build.gradle.kts`
  - `lucide-compose/build.gradle.kts`
  - `sample-compose/build.gradle.kts`
- 确认 README 中的依赖坐标、仓库地址、版本、平台列表与当前实现一致。
- 确认平台限制描述足够明确：
  - 本地发布/`act` 跳过 iOS
  - sample 没有单独 JS(IR) demo
- 通读中英文文档，确保术语统一、章节对应且不存在互相矛盾的信息。
