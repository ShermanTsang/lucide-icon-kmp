package io.github.lucideicons.kmp.core.model

data class LucideIconMetadata(
    val key: LucideIconKey,
    val displayName: String = key.value,
    val tags: Set<String> = emptySet(),
    val categories: Set<LucideIconCategory> = emptySet(),
    val defaultStrokeWidth: Float = 2f,
    val sourceSet: IconSourceSet = IconSourceSet.BuiltIn,
)
