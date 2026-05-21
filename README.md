# lucide-icon-kmp

[简体中文](README.zh-CN.md)

`lucide-icon-kmp` is a Kotlin Multiplatform wrapper around Lucide Icons for Compose Multiplatform. It provides a lazy icon registry, configurable `LucideIcon`, searchable `LucideIconPicker`, custom icon registration, and a build-time generator for bundled icons.

## Overview

This repository is organized as a multi-module Kotlin Multiplatform build:

- `lucide-core`: shared icon models, metadata, lazy registry, search, and bundled icon registration.
- `lucide-compose`: Compose Multiplatform UI components such as `LucideIcon` and `LucideIconPicker`.
- `lucide-generator`: JVM generator that reads vendored Lucide SVG files and emits Kotlin source files and indexes.
- `sample-compose`: sample app targets that demonstrate icon rendering, picker usage, and custom icon registration.

## Maven Coordinates

Current publish coordinates are:

- `group`: `com.shermant`
- `version`: `0.1.0-SNAPSHOT`
- `lucide-core`: `com.shermant:lucide-icon-kmp:0.1.0-SNAPSHOT`
- `lucide-compose`: `com.shermant:lucide-icon-kmp-compose:0.1.0-SNAPSHOT`

For the current snapshot publish setup, the local publish environment points to:

```text
https://central.sonatype.com/repository/maven-snapshots/
```

Use that repository only when your snapshot artifacts have been published there. The publish workflow still reads the repository URL from `MAVEN_REPOSITORY_URL`, so it can be changed per environment.

Example dependency setup:

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

For local integration from another Kotlin Multiplatform project, see [LOCAL_USAGE.md](LOCAL_USAGE.md).

## Platform Support

### Library targets

`lucide-core` and `lucide-compose` currently declare these Kotlin Multiplatform targets:

- Android
- Desktop JVM
- JS (IR)
- WasmJs
- iOS (`iosX64`, `iosArm64`, `iosSimulatorArm64`)

### Sample coverage

`sample-compose` currently includes runnable entry points for:

- Android
- Desktop JVM
- WasmJs browser
- iOS

Current gaps and caveats:

- The sample app does not currently provide a dedicated JS (IR) demo entry point.
- Library and sample modules keep their full declared target set during local and CI builds, including iOS variants.

## Usage

### Show an icon

```kotlin
LucideIcon(
    name = "activity",
    size = 24.dp,
    color = Color(0xFF2563EB),
    strokeWidth = 2.5f,
)
```

### Pick an icon

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

### Register a custom icon

```kotlin
LucideIcons.registry.registerCustomIcon(
    name = "brand-logo",
    tags = setOf("brand"),
    imageVector = customImageVector,
)
```

## Generator Flow

The repository vendors a pinned Lucide snapshot under `lucide-generator/src/main/resources/lucide-icons` and commits the generated Kotlin outputs in `lucide-core`.

```bash
./gradlew :lucide-generator:generateBundledLucide
```

This task regenerates the built-in registry, metadata chunks, and icon providers under `lucide-core/src/commonMain/kotlin/com/shermant/core/generated`.

Generated icon files are intended to be committed so consumers do not need to regenerate them during normal library usage.

The pinned Lucide snapshot version is recorded in `lucide-generator/src/main/resources/lucide-icons/VERSION.txt`.

## Validate Publish Workflow Locally

This repository keeps strict remote publish validation in both GitHub Actions and local `act` runs.
Actual full-platform publication runs on `macos-latest` in GitHub Actions so Apple variants are included.

Before running the publish workflow locally:

1. Create a local `.env` file.
2. Create a local `.secrets` file.
3. Set `MAVEN_REPOSITORY_URL` in `.secrets` to a real remote Maven repository.
4. If authentication is required, fill in `MAVEN_USERNAME` and `MAVEN_PASSWORD` in `.secrets`.
5. If signing is required, set `SIGNING_KEY_BASE64` in `.secrets` to the Base64-encoded ASCII-armored private key on a single line, then provide `SIGNING_PASSWORD`.
6. Keep `ACT=true` in `.env` so the workflow stops after validation during local `act` runs.

Run the validation workflow locally with:

```bash
act
```

When running through `act`, the workflow stops after `Validate publish configuration`.
Full publication, including iOS artifacts, requires the GitHub Actions macOS runner or a local macOS host.
If any required publish variables are missing, the workflow fails in `Validate publish configuration` before Gradle `publish` starts.

## Run Sample Targets

Launch the Wasm browser sample with:

```bash
./gradlew :sample-compose:wasmJsBrowserDevelopmentRun
```

Build the production Wasm distribution with:

```bash
./gradlew :sample-compose:wasmJsBrowserDistribution
```

Desktop, Android, and iOS sample entry points also exist in `sample-compose`, but their execution depends on the corresponding local toolchains and host environment.

## Search Behavior

- Supports case-insensitive exact matching.
- Supports prefix matching.
- Supports substring matching.
- Supports tag-based matching.
- Supports localized picker labels for `en` and `zh`.
- Supports searching built-in icons in English and Chinese when `locale` is set accordingly.

## License

- Project source code is released under the MIT License. See [LICENSE](LICENSE).
- Lucide Icons are licensed under the MIT License.
- The Lucide copyright notice is preserved in [licenses/lucide-license.txt](licenses/lucide-license.txt).
- See [NOTICE](NOTICE) for attribution details.
