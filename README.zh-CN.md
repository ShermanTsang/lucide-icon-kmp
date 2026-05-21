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

只有在对应 snapshot 制品已经上传到 Central snapshots 之后，才应在消费端直接使用这个地址。这个地址是消费仓库地址，不是正式版发布的主上传入口。

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
    pageSize = 24,
    searchLimit = 100,
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

## 搜索与分页行为

- 支持大小写不敏感的精确匹配。
- 支持前缀匹配。
- 支持子串匹配。
- 支持基于标签的匹配。
- 支持 `en` 和 `zh` 的本地化 Picker 文案。
- 当 `locale` 对应语言可用时，支持中英文搜索内置图标。
- 当查询为空时，Picker 会显示全部已注册图标。
- 通过 `pageSize` 控制每页显示数量。
- `searchLimit` 仅在查询非空时生效。

## 发布 SNAPSHOT 到 Central

该仓库现在只面向 Maven Central。
当 `VERSION_NAME` 以 `-SNAPSHOT` 结尾时，请使用 `publishCentralSnapshot`。

对于当前仓库的本地发布，建议每类配置只保留一个来源：

- `VERSION_NAME` 这类受版本控制的项目元数据继续放在 `gradle.properties`
- 本地发布凭据统一放在仓库根目录 `.secrets`
- `.env` 不再参与发布流程
- Windows PowerShell 本地发布统一通过 `scripts/publish-central.ps1`，由它把 `.secrets` 转成发布插件实际读取的 Gradle 属性

执行 snapshot 发布前，请在 `.secrets` 中设置 Central Portal token：

- `MAVEN_CENTRAL_USERNAME`
- `MAVEN_CENTRAL_PASSWORD`

由于这些发布任务会包含 Android publication，请先在本机配置 Android SDK：

- 在仓库根目录的本地 `local.properties` 中添加 `sdk.dir=...`
- 或在当前 shell 环境中设置 `ANDROID_HOME` / `ANDROID_SDK_ROOT`

Snapshot 发布不强制要求签名。
如果你希望在本地对 snapshot 也签名，请把以下配置写入 `.secrets`：

- `SIGNING_KEY_BASE64` / `SIGNING_PASSWORD`

推荐使用以下命令发布 snapshot：

```bash
./gradlew publishCentralSnapshot
```

在 Windows PowerShell 中可使用：

```powershell
.\scripts\publish-central.ps1 -Task publishCentralSnapshot
```

如果版本不是 snapshot，或者缺少 Central token，任务会在进入上传前直接失败。

## 通过 Central Portal 发布正式版

当 `VERSION_NAME` 为正式版且不带 `-SNAPSHOT` 后缀时，请使用 `publishCentralRelease`。

正式版发布要求：

- `com.shermant` 已在 Central Portal 完成 namespace 验证
- 如果你也要发 snapshot，则对应 namespace 已开启 snapshots
- 使用的是 Central Portal user token，而不是门户网站登录密码
- 正式版所有构件都具备签名配置

推荐的凭据来源包括：

- 本地 `.secrets`，包含 `MAVEN_CENTRAL_USERNAME`、`MAVEN_CENTRAL_PASSWORD`、`SIGNING_KEY_BASE64`、`SIGNING_PASSWORD`
- 如需 CI 或自动化环境，仍可按需改用环境变量

由于正式版发布同样会解析 Android 相关构件，请先在本机配置 Android SDK：

- 在仓库根目录的本地 `local.properties` 中添加 `sdk.dir=...`
- 或在当前 shell 环境中设置 `ANDROID_HOME` / `ANDROID_SDK_ROOT`

推荐使用以下命令上传并自动发布正式版：

```bash
./gradlew publishCentralRelease
```

在 Windows PowerShell 中可使用：

```powershell
.\scripts\publish-central.ps1 -Task publishCentralRelease
```

当前构建会在上传前先校验正式版模式：

- `VERSION_NAME` 不能以 `-SNAPSHOT` 结尾
- 必须提供 Central token
- 必须同时提供签名私钥和签名密码

对于本地 PowerShell 发布，`scripts/publish-central.ps1` 会读取仓库根目录 `.secrets`，并转成发布插件实际读取的 Gradle 属性。
只有在你明确需要 CI 或 shell 注入时，才建议直接改用环境变量。

在 Windows 上执行远端发布时，不保证能够产出完整的 Apple variants。
如果你需要完整的 Apple 制品集合，请在 macOS 主机上执行相同的 Gradle 发布命令。

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
