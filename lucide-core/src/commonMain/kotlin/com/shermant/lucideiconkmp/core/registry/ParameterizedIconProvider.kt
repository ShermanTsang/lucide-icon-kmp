package com.shermant.lucideiconkmp.core.registry

import androidx.compose.ui.graphics.vector.ImageVector

data class IconRenderParameters(
    val size: Float? = null,
    val strokeWidth: Float? = null,
    val colorArgb: Long? = null,
) {
    val isDefault: Boolean = size == null && strokeWidth == null && colorArgb == null
}

fun interface ParameterizedIconProvider {
    fun create(parameters: IconRenderParameters): ImageVector
}

fun ParameterizedIconProvider.create(): ImageVector = create(IconRenderParameters())
