package io.github.lucideicons.kmp.core.custom

import io.github.lucideicons.kmp.core.registry.IconRegistry

fun IconRegistry.registerIconSet(iconSet: CustomIconSet, replace: Boolean = false) {
    iconSet.icons.forEach { (metadata, creator) ->
        register(metadata = metadata, creator = creator, replace = replace)
    }
}
