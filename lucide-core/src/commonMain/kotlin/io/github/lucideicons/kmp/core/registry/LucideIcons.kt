package io.github.lucideicons.kmp.core.registry

object LucideIcons {
    val registry: MutableIconRegistry by lazy {
        DefaultIconRegistry().apply {
            BuiltInIconRegistrar.registerInto(this)
        }
    }
}
