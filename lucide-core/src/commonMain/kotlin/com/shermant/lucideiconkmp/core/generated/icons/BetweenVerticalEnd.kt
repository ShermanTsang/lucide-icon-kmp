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

val betweenVerticalEndProvider = ParameterizedIconProvider { parameters ->
    buildBetweenVerticalEnd(parameters)
}

val BetweenVerticalEnd: ImageVector
    get() = betweenVerticalEndProvider.create()

val LucideIcons.BetweenVerticalEnd: ImageVector
    get() = betweenVerticalEndProvider.create()

private val betweenVerticalEndPath0 = PathParser().parsePathString("M 4 3 H 9 A 1 1 0 0 1 10 4 V 15 A 1 1 0 0 1 9 16 H 4 A 1 1 0 0 1 3 15 V 4 A 1 1 0 0 1 4 3 Z").toNodes()
private val betweenVerticalEndPath1 = PathParser().parsePathString("m9 22 3-3 3 3").toNodes()
private val betweenVerticalEndPath2 = PathParser().parsePathString("M 15 3 H 20 A 1 1 0 0 1 21 4 V 15 A 1 1 0 0 1 20 16 H 15 A 1 1 0 0 1 14 15 V 4 A 1 1 0 0 1 15 3 Z").toNodes()

private fun buildBetweenVerticalEnd(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "between-vertical-end",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = betweenVerticalEndPath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = betweenVerticalEndPath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = betweenVerticalEndPath2,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
