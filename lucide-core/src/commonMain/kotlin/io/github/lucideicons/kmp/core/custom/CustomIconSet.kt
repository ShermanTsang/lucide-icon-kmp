package io.github.lucideicons.kmp.core.custom

import io.github.lucideicons.kmp.core.model.LucideIconMetadata
import io.github.lucideicons.kmp.core.registry.LucideIconCreator

data class CustomIconSet(
    val icons: List<Pair<LucideIconMetadata, LucideIconCreator>>,
)
