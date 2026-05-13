package io.github.lucideicons.kmp.compose.ext

import androidx.compose.ui.graphics.vector.ImageVector
import io.github.lucideicons.kmp.core.model.LucideIconKey
import io.github.lucideicons.kmp.core.registry.IconRegistry
import io.github.lucideicons.kmp.core.registry.IconRenderParameters

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
