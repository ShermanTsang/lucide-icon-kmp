package com.shermant.lucideiconkmp.core.custom

import com.shermant.lucideiconkmp.core.model.LucideIconMetadata
import com.shermant.lucideiconkmp.core.registry.LucideIconCreator

data class CustomIconSet(
    val icons: List<Pair<LucideIconMetadata, LucideIconCreator>>,
)
