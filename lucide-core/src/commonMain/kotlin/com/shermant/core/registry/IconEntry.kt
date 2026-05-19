package com.shermant.core.registry

import com.shermant.core.model.LucideIconMetadata

internal data class IconEntry(
    val metadata: LucideIconMetadata,
    val creator: LucideIconCreator,
    val parameterizedProvider: ParameterizedIconProvider? = null,
)
