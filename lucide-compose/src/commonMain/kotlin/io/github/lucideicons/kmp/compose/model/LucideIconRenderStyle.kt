package io.github.lucideicons.kmp.compose.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import io.github.lucideicons.kmp.compose.LucideIconDefaults

data class LucideIconRenderStyle(
    val size: Dp = LucideIconDefaults.Size,
    val color: Color = Color.Unspecified,
    val strokeWidth: Float = LucideIconDefaults.StrokeWidth,
)
