package com.shermant.compose.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.shermant.compose.LucideIconDefaults

data class LucideIconRenderStyle(
    val size: Dp = LucideIconDefaults.Size,
    val color: Color = Color.Unspecified,
    val strokeWidth: Float = LucideIconDefaults.StrokeWidth,
)
