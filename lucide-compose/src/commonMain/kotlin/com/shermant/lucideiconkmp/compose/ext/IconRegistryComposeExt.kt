package com.shermant.lucideiconkmp.compose.ext

import androidx.compose.ui.graphics.vector.ImageVector
import com.shermant.lucideiconkmp.core.model.LucideIconKey
import com.shermant.lucideiconkmp.core.registry.IconRegistry
import com.shermant.lucideiconkmp.core.registry.IconRenderParameters

fun IconRegistry.resolveImageVector(
    name: String,
    size: Float? = null,
    strokeWidth: Float? = null,
): ImageVector? = resolve(
    name = name,
    parameters = IconRenderParameters(size = size, strokeWidth = strokeWidth),
)

fun IconRegistry.resolveImageVector(
    key: LucideIconKey,
    size: Float? = null,
    strokeWidth: Float? = null,
): ImageVector? = resolve(
    key = key,
    parameters = IconRenderParameters(size = size, strokeWidth = strokeWidth),
)
