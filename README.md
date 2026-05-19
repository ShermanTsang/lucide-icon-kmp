# lucide-icon-kotlin-kmp

A Kotlin / Kotlin Multiplatform project that wraps Lucide Icons for Compose Multiplatform and provides a lazy icon registry, configurable `LucideIcon`, searchable `LucideIconPicker`, custom icon registration, and a build-time generator.

## Modules

- `lucide-core`: shared icon models, metadata, lazy registry, search, and built-in icon registration.
- `lucide-compose`: Compose Multiplatform UI components including `LucideIcon` and `LucideIconPicker`.
- `lucide-generator`: JVM generator that reads Lucide SVG files and emits Kotlin source files and indexes.
- `sample-compose`: Android, Desktop, iOS, and Web sample entry points demonstrating preview, picker usage, and custom icons.

## Platform Support

- Android: supported via Compose Android.
- Desktop: supported via Compose Desktop.
- iOS: supported via Compose Multiplatform iOS entry points.
- Web: supported via Compose Multiplatform `wasmJs` in the browser.

## Usage

For local integration from another Kotlin Multiplatform project, see [LOCAL_USAGE.md](LOCAL_USAGE.md).

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

The generator reads SVG files and emits Kotlin source outputs for icon files and indexes.

```bash
./gradlew :lucide-generator:run --args="inputSvgDir outputDir io.github.generated"
```

Generated icon files are intended to be committed so consumers do not need to regenerate them during normal library usage.

## Run Web Sample

Launch the browser sample with:

```bash
./gradlew :sample-compose:wasmJsBrowserDevelopmentRun
```

Build the production web distribution with:

```bash
./gradlew :sample-compose:wasmJsBrowserDistribution
```

## Search Behavior

- Supports case-insensitive exact matching.
- Supports prefix matching.
- Supports substring matching.
- Supports tag-based matching.

## License

- Project source code is released under the MIT License. See [LICENSE](LICENSE).
- Lucide Icons are licensed under the MIT License.
- The Lucide copyright notice is preserved in [licenses/lucide-license.txt](licenses/lucide-license.txt).
- See [NOTICE](NOTICE) for attribution details.
