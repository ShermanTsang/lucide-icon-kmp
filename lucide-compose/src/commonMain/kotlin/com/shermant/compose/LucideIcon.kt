package com.shermant.compose

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import com.shermant.compose.ext.resolveImageVector
import com.shermant.core.model.LucideIconKey
import com.shermant.core.registry.IconRegistry
import com.shermant.core.registry.LucideIcons

@Composable
fun LucideIcon(
    name: String,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    size: Dp = LucideIconDefaults.Size,
    color: Color = Color.Unspecified,
    strokeWidth: Float = LucideIconDefaults.StrokeWidth,
    registry: IconRegistry = LucideIcons.registry,
    missingIcon: ImageVector? = null,
) {
    val icon = remember(name, size, strokeWidth, registry) {
        registry.resolveImageVector(
            name = name,
            size = size.value,
            strokeWidth = strokeWidth,
        ) ?: missingIcon
    }

    if (icon != null) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            modifier = modifier,
            tint = color,
        )
    }
}

@Composable
fun LucideIcon(
    key: LucideIconKey,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    size: Dp = LucideIconDefaults.Size,
    color: Color = Color.Unspecified,
    strokeWidth: Float = LucideIconDefaults.StrokeWidth,
    registry: IconRegistry = LucideIcons.registry,
    missingIcon: ImageVector? = null,
) {
    LucideIcon(
        name = key.value,
        modifier = modifier,
        contentDescription = contentDescription,
        size = size,
        color = color,
        strokeWidth = strokeWidth,
        registry = registry,
        missingIcon = missingIcon,
    )
}
