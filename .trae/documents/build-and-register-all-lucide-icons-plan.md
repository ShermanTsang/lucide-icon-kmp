# Build and Register All Lucide Icons

## Summary

Implement a reproducible full-catalog built-in icon pipeline for this repository so `lucide-core` ships and registers the complete Lucide icon set instead of the current 3 generated icons. The solution vendors a pinned Lucide source snapshot into the repo, upgrades the generator to emit production-ready icon providers plus rich metadata, and wires generation into a documented, repeatable build/update workflow. The result keeps `LucideIcons.registry`, `LucideIcon`, and `LucideIconPicker` working against the full built-in catalog without requiring consumers to generate icons themselves.

## Current State Analysis

- `lucide-core/src/commonMain/kotlin/io/github/lucideicons/kmp/core/generated/LucideGeneratedRegistry.kt` currently registers only `Activity`, `Airplay`, and `ArrowRight`.
- `lucide-core/src/commonMain/kotlin/io/github/lucideicons/kmp/core/generated/LucideGeneratedMetadata.kt` also exposes only those 3 metadata entries.
- `lucide-core/src/commonMain/kotlin/io/github/lucideicons/kmp/core/generated/icons` contains only `Activity.kt`, `Airplay.kt`, and `ArrowRight.kt`.
- `lucide-core/src/commonMain/kotlin/io/github/lucideicons/kmp/core/registry/BuiltInIconRegistrar.kt` delegates all built-in registration to `registerGeneratedIcons(registry)`, so generated output is the only built-in source.
- `lucide-generator/src/main/kotlin/io/github/lucideicons/kmp/generator/GeneratorMain.kt` loads SVG files from a caller-provided directory and writes generated outputs, but the checked-in generator writers do not match the checked-in `lucide-core` generated files.
- `lucide-generator/src/main/kotlin/io/github/lucideicons/kmp/generator/parser/SvgIconParser.kt` only extracts `<path d="...">` data and basic dimensions, which is too narrow for a full Lucide catalog rollout.
- `lucide-generator/src/main/kotlin/io/github/lucideicons/kmp/generator/source/LucideSvgSource.kt` loads flat SVG files from one directory only; it does not join icon metadata from sidecar files.
- `lucide-generator/src/main/kotlin/io/github/lucideicons/kmp/generator/writer/IconFileWriter.kt`, `IconRegistryWriter.kt`, and `IconCategoryWriter.kt` currently emit a simplified index format, not the `LucideGeneratedRegistry.kt` / `LucideGeneratedMetadata.kt` structure used by `lucide-core`.
- `lucide-core/src/commonMain/kotlin/io/github/lucideicons/kmp/core/model/LucideIconCategory.kt` currently defines only `General`, `Arrows`, `Devices`, and `Custom`, which is too small to preserve useful picker categorization for the full Lucide catalog.
- `lucide-compose/src/commonMain/kotlin/io/github/lucideicons/kmp/compose/picker/LucideIconPicker.kt` renders tabs from `LucideIconCategory.entries`, so any richer built-in categorization must remain compatible with this API.
- `lucide-core/src/commonMain/kotlin/io/github/lucideicons/kmp/core/search/DefaultIconSearchStrategy.kt` already supports exact, prefix, substring, and tag matching, so richer generated tags will directly improve picker search quality.
- `README.md` documents the generator as a manual CLI step and states generated icon files are committed, but it does not describe a pinned full-catalog source or a repo-local refresh workflow.

## Assumptions & Decisions

- Source of truth: vendor a pinned Lucide snapshot into this repository so generation is reproducible and does not depend on network access during normal builds.
- Metadata quality: generate rich built-in metadata for search and picker usage, not only icon names.
- Output strategy: keep generated Kotlin sources committed under `lucide-core/src/commonMain/kotlin/io/github/lucideicons/kmp/core/generated` so downstream consumers do not run the generator.
- Registration model: continue using `BuiltInIconRegistrar` and `LucideIcons.registry` as the single built-in registration path.
- Compatibility target: preserve existing public usage patterns for `LucideIcon`, `LucideIconPicker`, `LucideIcons.registry`, and custom icon registration.
- Category model: expand `LucideIconCategory` to match the richer built-in catalog rather than collapsing all icons into the current 3 non-custom groups.
- Performance tradeoff: prefer generated Kotlin providers plus lazy registry registration over runtime SVG parsing.
- Update workflow: provide a single documented generation task/command for maintainers to refresh vendored data and generated outputs when the pinned Lucide version changes.

## Proposed Changes

### 1. Vendor a pinned Lucide source snapshot

- Add a new repo-local source directory for the pinned upstream Lucide dataset, for example `lucide-generator/src/main/resources/lucide-icons/`.
- Store the Lucide icon SVG files and their sidecar metadata files in that directory using a layout the generator can read deterministically.
- Add a small version manifest file in the same area, for example `lucide-generator/src/main/resources/lucide-icons/VERSION.txt` or `manifest.json`, to record the pinned upstream version or commit used to produce the snapshot.
- Keep the existing tiny sample fixture only if generator unit tests still need a minimal fixture; otherwise replace it with test-specific temp fixtures so production generation is clearly sourced from the pinned snapshot.

### 2. Redesign generator models around full-catalog icon data

- Update `lucide-generator/src/main/kotlin/io/github/lucideicons/kmp/generator/model/ParsedIcon.kt` to represent the full data required for `lucide-core` generation:
  - stable icon key and display name
  - viewport/dimensions
  - drawable node list suitable for code generation
  - tags and aliases
  - mapped categories
  - default stroke width if needed
- Introduce additional generator-side model types as needed, such as:
  - `ParsedIconNode` sealed hierarchy or equivalent data classes for SVG node types
  - `ParsedLucideMetadata` for sidecar metadata
  - category mapping model if upstream categories differ from local enum names
- Keep naming clear and unified across parser, source loader, and writers.

### 3. Expand the source loader to join SVG + metadata

- Replace or extend `lucide-generator/src/main/kotlin/io/github/lucideicons/kmp/generator/source/LucideSvgSource.kt` so it:
  - scans the vendored Lucide source directory
  - loads SVG content plus sidecar metadata per icon
  - validates one metadata record per SVG icon
  - sorts deterministically by icon key
- Add explicit validation errors for missing SVG, missing metadata, duplicate names, and malformed inputs so generator failures are actionable.

### 4. Upgrade SVG parsing to full Lucide coverage

- Replace the current path-only parsing in `lucide-generator/src/main/kotlin/io/github/lucideicons/kmp/generator/parser/SvgIconParser.kt` with parsing that supports the SVG shapes actually used by Lucide.
- At minimum, support the node types required by the vendored full catalog, likely including:
  - `path`
  - `circle`
  - `ellipse`
  - `line`
  - `polyline`
  - `polygon`
  - `rect`
- Capture relevant attributes needed by Compose vector generation, including but not limited to:
  - `d`
  - `cx`, `cy`, `r`
  - `x`, `y`, `width`, `height`, `rx`, `ry`
  - `x1`, `y1`, `x2`, `y2`
  - `points`
- Normalize dimensions/viewBox handling so generated icons render consistently at 24x24 default Lucide sizing.
- Preserve deterministic output ordering for nodes.

### 5. Replace simplified writers with production `lucide-core` writers

- Rework `lucide-generator/src/main/kotlin/io/github/lucideicons/kmp/generator/writer/IconFileWriter.kt` to emit one provider file per icon under the exact package currently consumed by `lucide-core`, such as `com.shermant.core.generated.icons`.
- Generated icon files should follow the current production pattern already visible in `lucide-core/src/commonMain/kotlin/io/github/lucideicons/kmp/core/generated/icons/Activity.kt`:
  - `ParameterizedIconProvider`
  - private builder function
  - `ImageVector.Builder`
  - generated Compose vector path/node instructions
- Rework `lucide-generator/src/main/kotlin/io/github/lucideicons/kmp/generator/writer/IconRegistryWriter.kt` to emit:
  - `LucideGeneratedRegistry.kt`
  - imports for all generated providers
  - one `registry.register(...)` call per built-in icon
- Rework `lucide-generator/src/main/kotlin/io/github/lucideicons/kmp/generator/writer/IconCategoryWriter.kt` or replace it with a metadata writer that emits:
  - `LucideGeneratedMetadata.kt`
  - one `LucideIconMetadata` value per icon
  - a deterministic `all` list
- If categories and metadata generation become broader than the current writer naming suggests, rename writers to clearer names such as `GeneratedRegistryWriter` and `GeneratedMetadataWriter`.

### 6. Expand `lucide-core` category support for the full picker

- Update `lucide-core/src/commonMain/kotlin/io/github/lucideicons/kmp/core/model/LucideIconCategory.kt` so it can represent the richer pinned catalog categories used by generated metadata.
- Preserve `Custom` for user-registered icons.
- Keep enum names stable and readable for Compose tabs; if user-facing labels should differ from enum names, add a display-label property rather than leaking raw enum identifiers directly into UI.
- Ensure the generator maps upstream category strings into this enum deterministically.

### 7. Keep picker/search UX compatible with a much larger catalog

- Update `lucide-compose/src/commonMain/kotlin/io/github/lucideicons/kmp/compose/picker/LucideIconCategoryTabs.kt` if category labels need better formatting than `category.name`.
- Update `lucide-compose/src/commonMain/kotlin/io/github/lucideicons/kmp/compose/picker/LucideIconPicker.kt` only as needed to remain usable with many more categories and many more results.
- Avoid changing public picker signatures unless necessary; prefer internal display improvements over API changes.
- Rely on richer generated `tags` so `lucide-core/src/commonMain/kotlin/io/github/lucideicons/kmp/core/search/DefaultIconSearchStrategy.kt` continues to work without search algorithm changes unless testing reveals ranking issues at full scale.

### 8. Add a reproducible generation entry point

- Update `lucide-generator/build.gradle.kts` to expose a maintainable generation task for the vendored full catalog, for example by configuring the existing `run` entry or adding a dedicated Gradle task that targets:
  - input: vendored Lucide snapshot directory
  - output: `lucide-core/src/commonMain/kotlin/io/github/lucideicons/kmp/core/generated`
  - package: `com.shermant.core.generated`
- Ensure the task is deterministic and suitable for maintainers, but do not require normal library consumers to execute it.
- Keep task naming and variable names clear and unified.

### 9. Refresh generated `lucide-core` outputs

- Regenerate and commit the full set under:
  - `lucide-core/src/commonMain/kotlin/io/github/lucideicons/kmp/core/generated/LucideGeneratedRegistry.kt`
  - `lucide-core/src/commonMain/kotlin/io/github/lucideicons/kmp/core/generated/LucideGeneratedMetadata.kt`
  - `lucide-core/src/commonMain/kotlin/io/github/lucideicons/kmp/core/generated/icons/*.kt`
- Confirm the generated set replaces the current 3-icon output completely and that built-in registration remains fully generated.

### 10. Strengthen tests around the new full-catalog pipeline

- Extend generator tests in `lucide-generator/src/test/kotlin/io/github/lucideicons/kmp/generator/` to cover:
  - SVG parsing for each supported shape type
  - deterministic metadata loading
  - deterministic generated Kotlin snippets for representative icons
  - failure cases for malformed/missing inputs
- Update `lucide-core/src/commonTest/kotlin/io/github/lucideicons/kmp/core/IconRegistryTest.kt` to assert more meaningful built-in coverage, such as:
  - registry contains many built-in icons
  - specific representative icons from different categories resolve successfully
  - metadata list size is far greater than 3
- Update `lucide-core/src/commonTest/kotlin/io/github/lucideicons/kmp/core/IconSearchEngineTest.kt` to verify tag/category search behavior with generated full-catalog metadata.
- Update `lucide-compose` tests only if category display or picker filtering behavior changes.

### 11. Document the pinned source and refresh workflow

- Update `README.md` to explain:
  - the repository ships committed generated built-in icons
  - the full built-in set comes from a pinned vendored Lucide snapshot
  - the maintainer workflow for refreshing that snapshot and regenerating Kotlin outputs
- If the workflow is more detailed than the main README should carry, add a focused maintainer document such as `docs/GENERATING_ICONS.md` and link it from `README.md`.
- Keep `LOCAL_USAGE.md` unchanged unless the full-catalog rollout changes how consumers integrate the library.

## Implementation Order

1. Add the pinned Lucide source snapshot and source manifest.
2. Redesign generator models and source loading for SVG + metadata.
3. Expand SVG parsing to cover the full Lucide node set.
4. Rebuild generator writers to emit the exact `lucide-core` generated API shape.
5. Expand local category enum and any UI label formatting needed by picker tabs.
6. Add or refine the Gradle maintainer generation entry point.
7. Regenerate all `lucide-core` built-in outputs from the vendored snapshot.
8. Update and add targeted tests for parser, generator, registry, and search behavior.
9. Update README and any maintainer docs.

## Verification Steps

- Run generator unit tests in `:lucide-generator` and confirm parser/writer coverage passes for representative full-catalog shapes.
- Run `:lucide-core` tests and confirm:
  - built-in registry registration succeeds
  - representative icons resolve with default and custom stroke width
  - metadata/search assertions pass with the regenerated full set
- Run `:lucide-compose` tests if picker/category behavior changes.
- Run diagnostics on edited Kotlin files and fix any introduced issues.
- Manually inspect generated outputs to confirm:
  - `LucideGeneratedMetadata.all` contains the full catalog, not 3 entries
  - `LucideGeneratedRegistry.kt` has one registration per generated icon
  - generated icon provider files exist for the complete pinned set
- Optionally run the sample app and verify `LucideIconPicker` can browse and search the enlarged built-in catalog without API changes.

## Risks to Watch

- Full Lucide SVG coverage may require more node/attribute support than the current path-only parser assumes.
- A very large generated source set may increase compile time and repository size; deterministic output and lazy registry usage reduce runtime cost but not source volume.
- Category expansion may affect picker tab usability if too many tabs are exposed without display or ordering refinements.
- Rich metadata quality depends on the structure and stability of the vendored upstream metadata files; generator validation should fail fast on mismatches.
