package io.github.lucideicons.kmp.core.registry

import androidx.compose.ui.graphics.vector.ImageVector
import io.github.lucideicons.kmp.core.model.IconSourceSet
import io.github.lucideicons.kmp.core.model.LucideIconCategory
import io.github.lucideicons.kmp.core.model.LucideIconKey
import io.github.lucideicons.kmp.core.model.LucideIconMetadata

fun IconRegistry.registerCustomIcon(
    name: String,
    categories: Set<LucideIconCategory> = setOf(LucideIconCategory.Custom),
    tags: Set<String> = emptySet(),
    replace: Boolean = false,
    creator: LucideIconCreator,
) {
    register(
        metadata = LucideIconMetadata(
            key = LucideIconKey(name),
            displayName = name,
            tags = tags,
            categories = categories,
            sourceSet = IconSourceSet.Custom,
        ),
        creator = creator,
        replace = replace,
    )
}

fun IconRegistry.registerCustomIcon(
    name: String,
    imageVector: ImageVector,
    categories: Set<LucideIconCategory> = setOf(LucideIconCategory.Custom),
    tags: Set<String> = emptySet(),
    replace: Boolean = false,
) {
    registerCustomIcon(
        name = name,
        categories = categories,
        tags = tags,
        replace = replace,
        creator = LucideIconCreator { imageVector },
    )
}
