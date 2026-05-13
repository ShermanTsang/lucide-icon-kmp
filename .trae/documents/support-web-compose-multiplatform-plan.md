# Support Web End Of Compose Multiplatform

## Summary

Add Compose Multiplatform web support using the `wasmJs` target for both published UI modules and the runnable sample app, while keeping the current Android, Desktop, and iOS behavior unchanged.

## Current State Analysis

- `lucide-core/build.gradle.kts` currently targets `androidTarget()`, `jvm("desktop")`, and iOS only. Its production code is entirely in `commonMain`, so the icon registry and generated vectors are already shared and should be reusable from web once `wasmJs` is enabled.
- `lucide-compose/build.gradle.kts` has the same platform matrix as `lucide-core`. Its components (`LucideIcon`, picker state, picker UI, grid, search bar, category tabs) live in `commonMain`, which makes web support primarily a target/build concern unless compile-time API gaps appear.
- `sample-compose/build.gradle.kts` only wires Android, Desktop, and iOS launchers today. The shared UI already lives in `sample-compose/src/commonMain/kotlin/io/github/lucideicons/kmp/sample/App.kt`, while platform launchers exist only under `androidMain`, `desktopMain`, and `iosMain`.
- There is no `sample-compose/src/wasmJsMain` source set, no web host page, and no documented web run/build workflow in `README.md`.
- `lucide-generator` is JVM-only and does not participate in runtime platform support, so it does not need web target changes.

## Assumptions & Decisions

- Web support targets `wasmJs` only. `js(IR)` is out of scope for this change.
- “Support web” includes both library modules (`lucide-core`, `lucide-compose`) and a runnable browser sample in `sample-compose`.
- The initial goal is functional support, not web-specific UX redesign. The shared `App()` layout remains the same unless a compile/runtime issue forces a minimal compatibility adjustment.
- The web entrypoint stays isolated in `wasmJsMain`; shared business/UI logic remains in `commonMain`.
- Existing Android, Desktop, and iOS configuration must remain intact.

## Proposed Changes

### 1. Enable `wasmJs` in reusable modules

#### `lucide-core/build.gradle.kts`

- Add a `wasmJs()` target to the existing `kotlin {}` block.
- Keep dependencies in `commonMain` and tests in `commonTest`; do not introduce a `wasmJsMain` source set unless compilation proves it is required.
- Reason: this makes generated icon vectors, registry APIs, and search logic available to web consumers without duplicating code.

#### `lucide-compose/build.gradle.kts`

- Add a `wasmJs()` target alongside Android, Desktop, and iOS.
- Reuse the existing `commonMain` Compose dependencies (`runtime`, `foundation`, `material3`, `ui`) so the same composables compile for web.
- Keep `commonTest` as the existing shared test location.
- Reason: `LucideIcon` and picker UI are already implemented in common code, so target expansion is the main missing piece.

### 2. Add a browser-executable web target to the sample app

#### `sample-compose/build.gradle.kts`

- Add `@OptIn(ExperimentalWasmDsl::class)` and a `wasmJs` target configured for browser execution.
- Configure the target as executable with `binaries.executable()`.
- Set a stable web output/module name so the host HTML can reference a predictable script file instead of depending on plugin-generated defaults.
- Leave the shared `commonMain` dependencies unchanged unless a missing web-only dependency is revealed during compilation.
- Reason: the sample needs an actual browser entrypoint, not only library compilation.

### 3. Add the Wasm browser launcher

#### New file: `sample-compose/src/wasmJsMain/kotlin/io/github/lucideicons/kmp/sample/main.kt`

- Add a web `main()` function that mounts the shared `App()` composable into the browser page.
- Use the current Compose Multiplatform web entrypoint style based on `ComposeViewport(...)` so the app reuses the existing shared UI instead of introducing a separate web UI stack.
- Keep this file minimal: startup only, no duplicated business logic.

### 4. Add the browser host page

#### New file: `sample-compose/src/wasmJsMain/resources/index.html`

- Create a minimal host page for the Wasm app.
- Include:
  - page title for the sample
  - viewport meta tag
  - a root element matching the `ComposeViewport(...)` container ID
  - basic CSS to make the app fill the browser viewport
  - a script tag pointing to the stable output file configured in `sample-compose/build.gradle.kts`
- Keep styling minimal and functional; no separate web-only design pass is planned.

### 5. Update project documentation

#### `README.md`

- Update the module/sample description to say the sample now supports Android, Desktop, iOS, and Web.
- Extend the “Platform Support” section to include Web (`wasmJs` / browser).
- Add a short “Run Web Sample” note with the expected Gradle task for local browser development.
- Add a production/distribution note for generating the web artifact.
- Reason: platform support and entry tasks should be discoverable for users of the repo.

## File-Level Execution Order

1. Update `lucide-core/build.gradle.kts` to add `wasmJs`.
2. Update `lucide-compose/build.gradle.kts` to add `wasmJs`.
3. Update `sample-compose/build.gradle.kts` to add executable `wasmJs` browser support and a stable JS output name.
4. Add `sample-compose/src/wasmJsMain/kotlin/io/github/lucideicons/kmp/sample/main.kt`.
5. Add `sample-compose/src/wasmJsMain/resources/index.html`.
6. Update `README.md` to document web support and usage.

## Risks And Mitigations

- Compose API compatibility risk: some shared UI APIs may behave differently on Wasm.
  - Mitigation: keep all shared code as-is first, then only make narrowly scoped compatibility fixes if the Wasm compile fails.
- Host page/script-name mismatch risk:
  - Mitigation: explicitly configure the sample web output name in Gradle and match `index.html` to that exact name.
- Regression risk for existing targets:
  - Mitigation: do not restructure current Android/Desktop/iOS launchers or dependencies beyond adding the new target.

## Verification Steps

Run these checks after implementation:

1. `./gradlew :lucide-core:compileKotlinWasmJs`
2. `./gradlew :lucide-compose:compileKotlinWasmJs`
3. `./gradlew :sample-compose:wasmJsBrowserDevelopmentRun`
4. `./gradlew :sample-compose:wasmJsBrowserDistribution`
5. Re-run at least one existing non-web compile path, preferably `:sample-compose:compileKotlinDesktop` and one Android/iOS-safe shared compile task, to confirm the new target did not break the current matrix.

## Acceptance Criteria

- `lucide-core` compiles for `wasmJs`.
- `lucide-compose` compiles for `wasmJs`.
- The sample app launches in a browser and renders the shared `App()` content from `commonMain`.
- The repo documentation clearly advertises web support and explains how to run/build it.
- Existing Android, Desktop, and iOS setup remains intact.
