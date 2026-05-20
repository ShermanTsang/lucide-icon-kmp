package com.shermant.core.registry

import androidx.compose.ui.graphics.vector.ImageVector

internal class IconFactory {
    private val cachedIcons = mutableMapOf<String, ImageVector>()
    private val parameterizedIcons = mutableMapOf<ParameterizedIconCacheKey, ImageVector>()

    fun create(name: String, entry: IconEntry, parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
        val provider = entry.parameterizedProvider
        if (!parameters.isDefault && provider != null) {
            val cacheKey = ParameterizedIconCacheKey(
                name = name,
                parameters = parameters,
            )
            return parameterizedIcons.getOrPut(cacheKey) {
                provider.create(parameters)
            }
        }

        return cachedIcons.getOrPut(name) {
            entry.creator.create()
        }
    }

    fun invalidate(name: String) {
        cachedIcons.remove(name)
        parameterizedIcons.keys.removeAll { it.name == name }
    }

    private data class ParameterizedIconCacheKey(
        val name: String,
        val parameters: IconRenderParameters,
    )
}
