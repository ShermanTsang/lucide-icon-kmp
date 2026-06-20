package com.shermant.lucideiconkmp.core.registry

object LucideIcons {
    val registry: MutableIconRegistry by lazy {
        DefaultIconRegistry().apply {
            BuiltInIconRegistrar.registerInto(this)
        }
    }
}
