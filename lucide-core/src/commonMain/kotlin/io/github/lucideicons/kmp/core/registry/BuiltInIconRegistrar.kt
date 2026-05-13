package io.github.lucideicons.kmp.core.registry

import io.github.lucideicons.kmp.core.generated.registerGeneratedIcons

object BuiltInIconRegistrar {
    fun registerInto(registry: MutableIconRegistry) {
        registerGeneratedIcons(registry)
    }
}
