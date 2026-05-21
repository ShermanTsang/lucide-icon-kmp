# lucide-icon-kmp

[English](README.md)

`lucide-icon-kmp` 是一个面向 Kotlin Multiplatform 与 Compose Multiplatform 的 Lucide Icons 封装库，提供懒加载图标注册表、可配置的 `LucideIcon`、可搜索的 `LucideIconPicker`、自定义图标注册能力，以及用于内置图标生成的构建期生成器。

## 项目概览

当前仓库采用多模块 Kotlin Multiplatform 结构：

- `lucide-core`：共享图标模型、元数据、懒加载注册表、搜索能力，以及内置图标注册。
- `lucide-compose`：Compose Multiplatform UI 组件，包括 `LucideIcon` 和 `LucideIconPicker`。
- `lucide-generator`：JVM 生成器，用于读取仓库内置的 Lucide SVG 文件并生成 Kotlin 源码与索引。
- `sample-compose`：示例应用模块，用来演示图标渲染、图标选择器和自定义图标注册。

## Maven 坐标

当前发布坐标如下：

- `group`：`com.shermant`
- `version`：`0.1.0-SNAPSHOT`
- `lucide-core`：`com.shermant:lucide-icon-kmp:0.1.0-SNAPSHOT`
- `lucide-compose`：`com.shermant:lucide-icon-kmp-compose:0.1.0-SNAPSHOT`

当前本地发布环境中的 snapshot 仓库地址配置为：

```text
https://central.sonatype.com/repository/maven-snapshots/
```

只有在对应 snapshot 制品已经发布到该仓库时，才应在消费端直接使用这个地址。发布工作流仍然通过 `MAVEN_REPOSITORY_URL` 读取仓库地址，因此可以按环境覆盖。

依赖声明示例：

```kotlin
repositories {
    maven("https://central.sonatype.com/repository/maven-snapshots/")
    google()
    mavenCentral()
}

dependencies {
    implementation("com.shermant:lucide-icon-kmp:0.1.0-SNAPSHOT")
    implementation("com.shermant:lucide-icon-kmp-compose:0.1.0-SNAPSHOT")
}
```

如果你要在另一个 Kotlin Multiplatform 项目中本地联调，请参考 [LOCAL_USAGE.md](LOCAL_USAGE.md)。

## 平台支持

### 库模块支持的平台

`lucide-core` 和 `lucide-compose` 当前声明了以下 Kotlin Multiplatform targets：

- Android
- Desktop JVM
- JS (IR)
- WasmJs
- iOS（`iosX64`、`iosArm64`、`iosSimulatorArm64`）

### 示例应用当前覆盖的平台

`sample-compose` 当前提供了以下可运行入口：

- Android
- Desktop JVM
- WasmJs 浏览器端
- iOS

当前需要特别说明的限制：

- 示例应用目前没有单独的 JS (IR) Demo 入口。
- 库模块与示例模块在本地和 CI 构建中都会保留完整声明的平台集合，包括 iOS variants。

## 使用示例

### 显示图标

```kotlin
LucideIcon(
    name = "activity",
    size = 24.dp,
    color = Color(0xFF2563EB),
    strokeWidth = 2.5f,
)
```

### 使用图标选择器

```kotlin
val state = rememberLucideIconPickerState()

LucideIconPicker(
    state = state,
    locale = LucideLocale.Zh,
    columns = 4,
    iconSize = 20.dp,
    onIconSelected = { metadata ->
        println(metadata.key.value)
    },
)
```

### 注册自定义图标

```kotlin
LucideIcons.registry.registerCustomIcon(
    name = "brand-logo",
    tags = setOf("brand"),
    imageVector = customImageVector,
)
```

## 生成器流程

仓库在 `lucide-generator/src/main/resources/lucide-icons` 下维护了一份固定版本的 Lucide 快照，并将生成后的 Kotlin 输出直接提交到 `lucide-core` 中。

```bash
./gradlew :lucide-generator:generateBundledLucide
```

该任务会重新生成位于 `lucide-core/src/commonMain/kotlin/com/shermant/core/generated` 下的内置注册表、元数据分片和图标 provider。

生成后的图标文件应作为仓库内容提交，这样使用者在正常接入库时不需要再次执行生成。

当前固定的 Lucide 快照版本为 `1.16.0`；准确的 tag、commit 与资源数量记录在 `lucide-generator/src/main/resources/lucide-icons/VERSION.txt`。

## 本地校验发布流程

该仓库在 GitHub Actions 与本地 `act` 运行中都保持严格的远端发布配置校验。
真正的全平台发布会在 GitHub Actions 的 `macos-latest` runner 上执行，以包含 Apple variants。

本地执行发布工作流前，请先完成以下准备：

1. 在本地创建 `.env` 文件。
2. 在本地创建 `.secrets` 文件。
3. 在 `.secrets` 中为 `MAVEN_REPOSITORY_URL` 设置一个真实可用的远端 Maven 仓库地址。
4. 如果仓库需要鉴权，请在 `.secrets` 中填写 `MAVEN_USERNAME` 与 `MAVEN_PASSWORD`。
5. 如果需要签名，请在 `.secrets` 中提供单行 Base64 编码的 `SIGNING_KEY_BASE64`，并同时提供 `SIGNING_PASSWORD`。
6. 在 `.env` 中保留 `ACT=true`，这样本地 `act` 运行会在校验后停止。

本地运行校验工作流：

```bash
act
```

通过 `act` 运行时，工作流会在 `Validate publish configuration` 后结束。
要产出包含 iOS 制品的完整发布，需要使用 GitHub Actions 的 macOS runner，或在本地 macOS 主机上执行。
如果缺少必要的发布变量，工作流会在 `Validate publish configuration` 步骤直接失败，Gradle `publish` 不会开始执行。

## 运行示例

启动 Wasm 浏览器示例：

```bash
./gradlew :sample-compose:wasmJsBrowserDevelopmentRun
```

构建 Wasm 生产包：

```bash
./gradlew :sample-compose:wasmJsBrowserDistribution
```

`sample-compose` 中也包含 Desktop、Android 和 iOS 的示例入口，但能否运行仍取决于本地工具链和宿主环境是否满足要求。

## 搜索行为

- 支持不区分大小写的精确匹配。
- 支持前缀匹配。
- 支持子串匹配。
- 支持基于标签的匹配。
- 支持 `en` 与 `zh` 两种 locale 的选择器文案。
- 在设置对应 `locale` 后，内置图标支持使用英文和中文搜索。

## 许可证

- 项目源码基于 MIT License 发布，见 [LICENSE](LICENSE)。
- Lucide Icons 也采用 MIT License。
- Lucide 的版权声明保留在 [licenses/lucide-license.txt](licenses/lucide-license.txt)。
- 归属说明见 [NOTICE](NOTICE)。
