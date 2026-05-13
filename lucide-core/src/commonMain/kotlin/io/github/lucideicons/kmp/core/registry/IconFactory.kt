package io.github.lucideicons.kmp.core.registry

import androidx.compose.ui.graphics.vector.ImageVector

internal class IconFactory {
    private val cachedIcons = mutableMapOf<String, ImageVector>()
    private val cacheLock = Any()

    fun create(name: String, entry: IconEntry, parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
        val provider = entry.parameterizedProvider
        if (!parameters.isDefault && provider != null) {
            return provider.create(parameters)
        }

        return synchronized(cacheLock) {
            cachedIcons.getOrPut(name) {
                entry.creator.create()
            }
        }
    }
}
