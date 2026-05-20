# Support Picker Bilingual i18n Plan

## Summary

Add English/Chinese locale support for `LucideIconPicker` and bilingual icon search. The implementation will keep English as the default locale, introduce an explicit locale parameter for picker/search presentation, bundle an in-repo Chinese translation dataset for built-in Lucide metadata, and localize picker UI labels plus category names for `en` and `zh`.

## Current State Analysis

- `lucide-compose/src/commonMain/kotlin/com/shermant/compose/picker/LucideIconPicker.kt`
  - The picker computes results with `registry.search(state.query)` and does not pass locale information.
  - The picker itself has no i18n abstraction.
- `lucide-core/src/commonMain/kotlin/com/shermant/core/registry/IconRegistry.kt`
  - Search API is `search(query: String, limit: Int = 100)` and is locale-agnostic.
- `lucide-core/src/commonMain/kotlin/com/shermant/core/search/DefaultIconSearchStrategy.kt`
  - Search indexes only `metadata.key.value`, `metadata.displayName`, and `metadata.tags`.
  - Matching is case-insensitive exact/prefix/substring/tag matching.
- `lucide-core/src/commonMain/kotlin/com/shermant/core/model/LucideIconMetadata.kt`
  - Metadata currently stores only one `displayName` and one `tags` set, both effectively English for bundled icons.
- `lucide-core/src/commonMain/kotlin/com/shermant/core/model/LucideIconCategory.kt`
  - Category labels are English-only via `displayName`.
- `lucide-compose/src/commonMain/kotlin/com/shermant/compose/picker/LucideIconPickerDefaults.kt`
  - Picker UI text is hard-coded in English (`"Search icons"`, `"All"`).
- `lucide-generator/src/main/kotlin/com/shermant/generator/source/LucideSvgSource.kt`
  - Built-in icon metadata is loaded from vendored Lucide JSON files.
- `lucide-generator/src/main/kotlin/com/shermant/generator/parser/SvgIconParser.kt`
  - Parsed icons inherit English display name from icon key and English metadata from Lucide JSON.
- `lucide-generator/src/main/kotlin/com/shermant/generator/writer/IconCategoryWriter.kt`
  - Generated metadata chunks emit only English-facing fields.
- Existing tests cover search behavior and metadata exposure in:
  - `lucide-core/src/commonTest/kotlin/com/shermant/core/IconSearchEngineTest.kt`
  - `lucide-core/src/commonTest/kotlin/com/shermant/core/IconRegistryTest.kt`
  - generator tests under `lucide-generator/src/test/kotlin/com/shermant/generator`

## Assumptions & Decisions

- Locale scope: support exactly `en` and `zh` for this change.
- Locale selection: use an explicit API parameter, not implicit system locale detection.
- Translation source: bundle an in-repo Chinese translation dataset for built-in Lucide icons.
- Search scope: support both English and Chinese queries against built-in metadata.
- UI scope: localize picker search placeholder, `All` tab label, and category labels for `en` and `zh`.
- Backward compatibility:
  - Keep existing English-first metadata accessors usable.
  - Keep existing `IconRegistry.search(query, limit)` callable, with English-default behavior delegated to the new locale-aware implementation.
- Custom icon behavior:
  - Existing custom icon registration continues to work unchanged.
  - New localized metadata fields should be optional with English fallback when Chinese translations are absent.

## Proposed Changes

### 1. Add a shared locale model and localized metadata access

- `lucide-core/src/commonMain/kotlin/com/shermant/core/model/`
  - Add a new locale type, for example `LucideLocale` or `LucideIconLocale`, with `En` and `Zh`.
  - Add localized label helpers so code can resolve the correct text without duplicating `when` branches across UI and search code.
- `lucide-core/src/commonMain/kotlin/com/shermant/core/model/LucideIconMetadata.kt`
  - Extend metadata to store bilingual search/display data in a backward-compatible way.
  - Likely shape:
    - keep `displayName` and `tags` as English-default aliases
    - add optional Chinese-facing fields such as `localizedDisplayNames` and `localizedTags`, or a compact localized metadata structure
  - Add helper functions such as:
    - `displayName(locale)`
    - `searchTerms(locale)`
    - `allSearchTerms()` for bilingual matching when appropriate
- Why:
  - This keeps the search and picker code simple and centralizes fallback logic.

### 2. Make category labels locale-aware

- `lucide-core/src/commonMain/kotlin/com/shermant/core/model/LucideIconCategory.kt`
  - Replace the English-only `displayName` storage with locale-aware label resolution.
  - Keep an English default accessor for compatibility if other code expects `displayName`.
  - Add a localized label function used by picker tabs.
- Why:
  - Picker UI localization requires category labels in both languages.

### 3. Introduce locale-aware search APIs in core

- `lucide-core/src/commonMain/kotlin/com/shermant/core/registry/IconRegistry.kt`
  - Add an overload such as `search(query: String, locale: LucideLocale, limit: Int = 100)`.
  - Preserve the current method and define it as English-default behavior.
- `lucide-core/src/commonMain/kotlin/com/shermant/core/registry/MutableIconRegistry.kt`
  - Implement the new overload and delegate legacy search to it.
- `lucide-core/src/commonMain/kotlin/com/shermant/core/search/IconQuery.kt`
  - If helpful, extend query data to carry locale and/or normalized locale-aware search settings.
- `lucide-core/src/commonMain/kotlin/com/shermant/core/search/DefaultIconSearchStrategy.kt`
  - Update matching logic to search:
    - English key
    - localized display name for requested locale
    - localized tags for requested locale
    - optionally both English and Chinese terms for robustness when the locale is `zh`
  - Preserve result ranking semantics as much as possible:
    - exact > prefix > contains > tag
    - stable sorting by locale-aware display label or English fallback
- Why:
  - Search logic belongs in `lucide-core`; the picker should stay a thin UI layer.

### 4. Add bundled Chinese translation data to the generator pipeline

- `lucide-generator/src/main/resources/`
  - Add a new in-repo translation resource for Chinese icon labels/search terms.
  - The format should be deterministic and keyed by icon name, for example:
    - display name in Chinese
    - extra Chinese search tags/aliases
  - Include category label translations separately if they are not hard-coded in Kotlin.
- `lucide-generator/src/main/kotlin/com/shermant/generator/model/ParsedIcon.kt`
  - Extend parsed/raw metadata models to carry localized display names and tags.
- `lucide-generator/src/main/kotlin/com/shermant/generator/source/LucideSvgSource.kt`
  - Load the Chinese translation resource in addition to Lucide JSON metadata.
  - Merge translation data into each raw icon model by icon key.
- `lucide-generator/src/main/kotlin/com/shermant/generator/parser/SvgIconParser.kt`
  - Preserve English defaults while attaching Chinese localized metadata into `ParsedIcon`.
- `lucide-generator/src/main/kotlin/com/shermant/generator/writer/IconCategoryWriter.kt`
  - Generate bilingual metadata fields into `LucideGeneratedMetadataChunk*.kt`.
- Why:
  - Built-in icons are generated; localized search data must be generated too or it will drift.

### 5. Localize picker APIs and UI labels

- `lucide-compose/src/commonMain/kotlin/com/shermant/compose/picker/LucideIconPicker.kt`
  - Add an explicit locale parameter, defaulting to English.
  - Pass locale into `registry.search(...)`.
  - Use locale-aware category labels in tabs.
  - Ensure the overload that exposes `query`/`onQueryChange` also accepts the locale parameter and forwards it consistently.
- `lucide-compose/src/commonMain/kotlin/com/shermant/compose/picker/LucideIconPickerDefaults.kt`
  - Add locale-aware default style generation, or a localized text provider that populates `labelText` and `allLabelText`.
  - Keep caller overrides working, so explicit style text still wins over defaults.
- `lucide-compose/src/commonMain/kotlin/com/shermant/compose/picker/LucideIconCategoryTabs.kt`
  - Render localized category labels from the chosen locale instead of the current English-only `displayName`.
- Why:
  - The user request explicitly targets `LucideIconPicker.kt` and picker locale support.

### 6. Keep custom icon registration compatible and optionally extensible

- `lucide-core/src/commonMain/kotlin/com/shermant/core/registry/CustomIconRegistrar.kt`
  - Preserve existing overloads unchanged for English-only callers.
  - If the chosen metadata shape supports it cleanly, add an optional overload for localized custom metadata later in the same change.
- Why:
  - Avoid breaking callers while allowing future localized custom icons.

### 7. Update tests for bilingual metadata, search, and picker-facing behavior

- `lucide-core/src/commonTest/kotlin/com/shermant/core/IconSearchEngineTest.kt`
  - Add assertions for:
    - English search still works
    - Chinese search returns expected icons
    - locale-aware result ordering/fallback works
- `lucide-core/src/commonTest/kotlin/com/shermant/core/IconRegistryTest.kt`
  - Add metadata assertions for localized display labels.
- `lucide-core/src/commonTest/kotlin/com/shermant/core/CustomIconRegistrationTest.kt`
  - Verify legacy custom registration behavior still works after metadata changes.
- `lucide-generator/src/test/kotlin/com/shermant/generator/`
  - Update/add tests so the generator emits localized metadata deterministically.
- `lucide-compose/src/commonTest/kotlin/com/shermant/compose/picker/`
  - Add focused state/API-level coverage only if needed for locale parameter forwarding or localized defaults.
- Why:
  - This change crosses generator, core, and UI boundaries, so regression coverage should focus on search/index correctness and API compatibility.

### 8. Regenerate generated metadata and update docs

- `lucide-core/src/commonMain/kotlin/com/shermant/core/generated/`
  - Regenerate metadata chunks after generator changes.
- `README.md`
  - Update picker usage examples to show locale selection.
  - Update search behavior documentation to mention English/Chinese support.
- `README.zh-CN.md`
  - Mirror the feature documentation if this file already documents picker/search usage.
- Why:
  - Generated metadata must match source changes, and public API changes need documentation.

## File-Level Execution Order

1. Add locale model and localized metadata/category abstractions in `lucide-core`.
2. Extend core search API and search strategy for locale-aware matching.
3. Extend generator raw/parsed models plus translation resource loading.
4. Update generator writer and regenerate `lucide-core` generated metadata.
5. Update picker locale parameter flow and localized default labels.
6. Update tests in core/generator/compose.
7. Update README documentation.

## Verification Steps

- Run targeted unit tests:
  - `./gradlew :lucide-core:commonTest`
  - `./gradlew :lucide-generator:test`
  - `./gradlew :lucide-compose:commonTest`
- Regenerate bundled metadata and confirm the generated files compile:
  - `./gradlew :lucide-generator:generateBundledLucide`
- Run a broader compile/test pass if needed after regeneration:
  - `./gradlew check`
- Manual validation in sample or local preview:
  - picker shows English labels under `en`
  - picker shows Chinese labels under `zh`
  - English query finds expected icons
  - Chinese query finds the same icons through bundled Chinese metadata
  - category filtering still works under both locales

## Risks To Watch

- Translation dataset completeness: missing Chinese terms should fall back cleanly to English without breaking search.
- API bloat: localized metadata should stay simple enough for generated code size and custom icon ergonomics.
- Ranking drift: adding bilingual terms can accidentally change result ordering unless scoring stays deterministic.
- Generated file size: localized metadata will increase generated output, so the representation should stay compact.
