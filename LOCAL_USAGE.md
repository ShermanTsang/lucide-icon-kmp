# Local Usage Guide

This document explains how to use this library from another **local Kotlin Multiplatform project** for development and testing.

## Recommended Approach

For local integration, the recommended approach is to use a **Gradle composite build** with `includeBuild`.

Why this is recommended:

- This repository is already organized as a standalone multi-module build.
- `lucide-compose` depends on `lucide-core` internally.
- The repository currently does not provide a ready-to-use `maven-publish` setup for `mavenLocal()`.
- Composite build keeps this repository self-contained and lets your consumer project use the latest local source code directly.

## Modules

- `lucide-core`: shared icon models, registry, metadata, search, and built-in icon registration.
- `lucide-compose`: Compose Multiplatform UI components such as `LucideIcon` and `LucideIconPicker`.

Use `lucide-compose` if you want Compose UI usage.

Add `lucide-core` as well if you also want direct registry or metadata access in your own code.

## Step 1: Add `includeBuild` In Your Consumer Project

Open the `settings.gradle.kts` file in your own local KMP project and add this:

```kotlin
includeBuild("D:/Code/Project/lucide-icon-kotlin@kmp") {
    dependencySubstitution {
        substitute(module("local.test:lucide-core"))
            .using(project(":lucide-core"))
        substitute(module("local.test:lucide-compose"))
            .using(project(":lucide-compose"))
    }
}
```

Notes:

- Use your actual local path.
- On Windows, `D:/...` works well in Gradle.
- Keep this repository and your consumer project on the same machine.

## Step 2: Add Dependencies In Your App Module

In your consumer module `build.gradle.kts`, add the dependencies in `commonMain`:

```kotlin
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation("local.test:lucide-compose:0.0.0-local")
            implementation("local.test:lucide-core:0.0.0-local")
        }
    }
}
```

If you only need the Compose icon API, this is usually enough:

```kotlin
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation("local.test:lucide-compose:0.0.0-local")
        }
    }
}
```

`lucide-compose` already depends on `lucide-core`, so you do not need to declare both unless your own code directly imports `lucide-core` APIs.

## Step 3: Add A Quick Smoke Test

After Gradle sync, add a simple composable in your consumer project:

```kotlin
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.lucideicons.kmp.compose.LucideIcon

@Composable
fun LocalLucideSmokeTest() {
    LucideIcon(
        name = "activity",
        size = 24.dp,
        color = Color(0xFF2563EB),
        strokeWidth = 2.5f,
    )
}
```

If this icon renders correctly, the local integration works.

## Step 4: Test The Picker

You can also test the picker component:

```kotlin
import androidx.compose.runtime.Composable
import io.github.lucideicons.kmp.compose.picker.LucideIconPicker
import io.github.lucideicons.kmp.compose.picker.rememberLucideIconPickerState

@Composable
fun LocalLucidePickerTest() {
    val pickerState = rememberLucideIconPickerState()

    LucideIconPicker(
        state = pickerState,
        columns = 4,
        onIconSelected = { metadata ->
            println(metadata.key.value)
        },
    )
}
```

## How To Verify

Use your normal build or run flow in the consumer project:

- Android: run your Android target.
- Desktop: run your desktop entry.
- iOS: build from the iOS target in your usual setup.
- Web: run your browser target if your project includes `wasmJs`.

You can also run a Gradle task from the consumer project, for example:

```bash
./gradlew build
```

On Windows PowerShell:

```powershell
.\gradlew.bat build
```

If the build succeeds and your app can import `io.github.lucideicons.kmp.*`, the composite build setup is working.

## Common Issues

### Dependency Not Found

If Gradle cannot find `local.test:lucide-compose` or `local.test:lucide-core`:

- Check that `includeBuild("...")` points to the correct local folder.
- Check that the target folder contains this repository's `settings.gradle.kts`.
- Re-sync Gradle after editing `settings.gradle.kts`.

### Build Script Alias Problems

Do not copy `:lucide-core` and `:lucide-compose` directly into your own build unless you also plan to merge this repository's version catalog and plugin setup.

This repository uses its own Gradle version catalog, so composite build is the safer local testing setup.

### `mavenLocal()` Does Not Work

This repository does not currently include a ready-to-use publish configuration, so `publishToMavenLocal` is not the main workflow yet.

If you want that workflow, add `maven-publish`, `group`, and `version` configuration first.

## Suggested Workflow During Local Development

1. Keep this library repository open in one IDE window.
2. Keep your consumer KMP project open in another IDE window.
3. Use `includeBuild` in the consumer project.
4. Modify code in this repository.
5. Rebuild or rerun the consumer project to verify the change immediately.

This gives you the fastest edit-and-verify loop when developing the library locally.
