package com.shermant.core.custom

import com.shermant.core.model.LucideIconMetadata
import com.shermant.core.registry.LucideIconCreator

data class CustomIconSet(
    val icons: List<Pair<LucideIconMetadata, LucideIconCreator>>,
)
