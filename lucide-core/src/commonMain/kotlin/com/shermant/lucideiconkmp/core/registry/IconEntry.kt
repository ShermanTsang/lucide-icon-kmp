package com.shermant.lucideiconkmp.core.registry

import com.shermant.lucideiconkmp.core.model.LucideIconMetadata

internal data class IconEntry(
    val metadata: LucideIconMetadata,
    val creator: LucideIconCreator,
    val parameterizedProvider: ParameterizedIconProvider? = null,
)
