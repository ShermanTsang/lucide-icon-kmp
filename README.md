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

Use that repository only after your snapshot artifacts have been uploaded to Central snapshots. It is a consumer repository URL, not the primary release upload endpoint.

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

The current pinned Lucide snapshot is `1.16.0`; the exact tag, commit, and resource counts are recorded in `lucide-generator/src/main/resources/lucide-icons/VERSION.txt`.

## Publish SNAPSHOTs To Central

This repository now targets Maven Central only.
Use `publishCentralSnapshot` when `VERSION_NAME` ends with `-SNAPSHOT`.

For local publishing in this repository, keep one source of truth per config type:

- checked-in project metadata such as `VERSION_NAME` stays in `gradle.properties`
- local publish credentials stay in the root `.secrets` file
- `.env` is not part of the publish flow
- local PowerShell publishing should go through `scripts/publish-central.ps1`, which loads `.secrets` into the Gradle properties expected by the publishing plugin

Before running a snapshot publish, set your Central Portal token in `.secrets`:

- `MAVEN_CENTRAL_USERNAME`
- `MAVEN_CENTRAL_PASSWORD`

Because these publish tasks include Android publications, configure a local Android SDK before running them:

- add `sdk.dir=...` to a local root `local.properties` file
- or set `ANDROID_HOME` / `ANDROID_SDK_ROOT` in your shell environment

Signing is optional for snapshots.
If you want to sign snapshot artifacts locally, add these to `.secrets`:

- `SIGNING_KEY_BASE64` / `SIGNING_PASSWORD`

Publish a snapshot with:

```bash
./gradlew publishCentralSnapshot
```

On Windows PowerShell, use:

```powershell
.\scripts\publish-central.ps1 -Task publishCentralSnapshot
```

The task fails early if the version is not a snapshot or if the Central token is missing.

## Publish Releases Through Central Portal

Use `publishCentralRelease` when `VERSION_NAME` is a formal release without the `-SNAPSHOT` suffix.

Formal releases require all of the following:

- a verified Central Portal namespace for `com.shermant`
- Central snapshots enabled if you also want snapshot uploads
- a Central Portal user token, not your website login password
- signing credentials for all release artifacts

Recommended credential sources are:

- local `.secrets` with `MAVEN_CENTRAL_USERNAME`, `MAVEN_CENTRAL_PASSWORD`, `SIGNING_KEY_BASE64`, and `SIGNING_PASSWORD`
- CI or automation can still use environment variables when needed

Configure a local Android SDK before running the release task because the publication graph also resolves Android artifacts:

- add `sdk.dir=...` to a local root `local.properties` file
- or set `ANDROID_HOME` / `ANDROID_SDK_ROOT` in your shell environment

Publish and auto-release through Central Portal with:

```bash
./gradlew publishCentralRelease
```

On Windows PowerShell, use:

```powershell
.\scripts\publish-central.ps1 -Task publishCentralRelease
```

This build validates the release mode before upload:

- `VERSION_NAME` must not end with `-SNAPSHOT`
- Central token credentials must be present
- signing key and signing password must both be present

For local PowerShell publishing, `scripts/publish-central.ps1` reads the tracked root `.secrets` file and exports the Gradle properties expected by the publishing plugin.
Use environment variables directly only when you intentionally need a CI or non-file-based shell setup.

Remote publishing from Windows does not guarantee complete Apple variants output.
If you need the full Apple artifact set, run the same publish command on a macOS host.

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
