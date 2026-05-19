package com.shermant.core.custom

import com.shermant.core.registry.IconRegistry

fun IconRegistry.registerIconSet(iconSet: CustomIconSet, replace: Boolean = false) {
    iconSet.icons.forEach { (metadata, creator) ->
        register(metadata = metadata, creator = creator, replace = replace)
    }
}
