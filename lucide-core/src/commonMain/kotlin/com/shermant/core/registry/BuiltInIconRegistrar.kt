package com.shermant.core.registry

import com.shermant.core.generated.registerGeneratedIcons

object BuiltInIconRegistrar {
    fun registerInto(registry: MutableIconRegistry) {
        registerGeneratedIcons(registry)
    }
}
