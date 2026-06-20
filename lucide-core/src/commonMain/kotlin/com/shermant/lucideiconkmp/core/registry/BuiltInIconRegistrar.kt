package com.shermant.lucideiconkmp.core.registry

import com.shermant.lucideiconkmp.core.generated.registerGeneratedIcons

object BuiltInIconRegistrar {
    fun registerInto(registry: MutableIconRegistry) {
        registerGeneratedIcons(registry)
    }
}
