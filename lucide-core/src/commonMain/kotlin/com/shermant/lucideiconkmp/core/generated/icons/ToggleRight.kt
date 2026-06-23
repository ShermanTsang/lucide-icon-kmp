package com.shermant.lucideiconkmp.core.generated.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.unit.dp
import com.shermant.lucideiconkmp.core.registry.IconRenderParameters
import com.shermant.lucideiconkmp.core.registry.LucideIcons
import com.shermant.lucideiconkmp.core.registry.ParameterizedIconProvider
import com.shermant.lucideiconkmp.core.registry.create

val toggleRightProvider = ParameterizedIconProvider { parameters ->
    buildToggleRight(parameters)
}

val ToggleRight: ImageVector
    get() = toggleRightProvider.create()

val LucideIcons.ToggleRight: ImageVector
    get() = toggleRightProvider.create()

private val toggleRightPath0 = PathParser().parsePathString("M 18 12 A 3 3 0 1 0 12 12 A 3 3 0 1 0 18 12 Z").toNodes()
private val toggleRightPath1 = PathParser().parsePathString("M 9 5 H 15 A 7 7 0 0 1 22 12 V 12 A 7 7 0 0 1 15 19 H 9 A 7 7 0 0 1 2 12 V 12 A 7 7 0 0 1 9 5 Z").toNodes()

private fun buildToggleRight(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "toggle-right",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = toggleRightPath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = toggleRightPath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
