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
import com.shermant.lucideiconkmp.core.registry.ParameterizedIconProvider
import com.shermant.lucideiconkmp.core.registry.create

val betweenVerticalStartProvider = ParameterizedIconProvider { parameters ->
    buildBetweenVerticalStart(parameters)
}

val BetweenVerticalStart: ImageVector
    get() = betweenVerticalStartProvider.create()

private val betweenVerticalStartPath0 = PathParser().parsePathString("M 4 8 H 9 A 1 1 0 0 1 10 9 V 20 A 1 1 0 0 1 9 21 H 4 A 1 1 0 0 1 3 20 V 9 A 1 1 0 0 1 4 8 Z").toNodes()
private val betweenVerticalStartPath1 = PathParser().parsePathString("m15 2-3 3-3-3").toNodes()
private val betweenVerticalStartPath2 = PathParser().parsePathString("M 15 8 H 20 A 1 1 0 0 1 21 9 V 20 A 1 1 0 0 1 20 21 H 15 A 1 1 0 0 1 14 20 V 9 A 1 1 0 0 1 15 8 Z").toNodes()

private fun buildBetweenVerticalStart(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "between-vertical-start",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = betweenVerticalStartPath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = betweenVerticalStartPath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = betweenVerticalStartPath2,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
