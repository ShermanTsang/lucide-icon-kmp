package com.shermant.lucideiconkmp.core.registry

import androidx.compose.ui.graphics.vector.ImageVector
import com.shermant.lucideiconkmp.core.model.IconSourceSet
import com.shermant.lucideiconkmp.core.model.LucideIconCategory
import com.shermant.lucideiconkmp.core.model.LucideIconKey
import com.shermant.lucideiconkmp.core.model.LucideIconMetadata

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
