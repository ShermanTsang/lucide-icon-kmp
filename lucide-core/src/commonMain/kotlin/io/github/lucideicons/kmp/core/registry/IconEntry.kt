package io.github.lucideicons.kmp.core.registry

import io.github.lucideicons.kmp.core.model.LucideIconMetadata

internal data class IconEntry(
    val metadata: LucideIconMetadata,
    val creator: LucideIconCreator,
    val parameterizedProvider: ParameterizedIconProvider? = null,
)
