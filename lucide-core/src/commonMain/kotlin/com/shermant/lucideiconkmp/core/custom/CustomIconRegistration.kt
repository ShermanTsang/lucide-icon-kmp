package com.shermant.lucideiconkmp.core.custom

import com.shermant.lucideiconkmp.core.registry.IconRegistry

fun IconRegistry.registerIconSet(iconSet: CustomIconSet, replace: Boolean = false) {
    iconSet.icons.forEach { (metadata, creator) ->
        register(metadata = metadata, creator = creator, replace = replace)
    }
}
