package com.shermant.core.generated.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.unit.dp
import com.shermant.core.registry.IconRenderParameters
import com.shermant.core.registry.ParameterizedIconProvider
import com.shermant.core.registry.create

val bubblesProvider = ParameterizedIconProvider { parameters ->
    buildBubbles(parameters)
}

val Bubbles: ImageVector
    get() = bubblesProvider.create()

private val bubblesPath0 = PathParser().parsePathString("M7.001 15.085A1.5 1.5 0 0 1 9 16.5").toNodes()
private val bubblesPath1 = PathParser().parsePathString("M 22 8.5 A 3.5 3.5 0 1 0 15 8.5 A 3.5 3.5 0 1 0 22 8.5 Z").toNodes()
private val bubblesPath2 = PathParser().parsePathString("M 13 16.5 A 5.5 5.5 0 1 0 2 16.5 A 5.5 5.5 0 1 0 13 16.5 Z").toNodes()
private val bubblesPath3 = PathParser().parsePathString("M 10 4.5 A 2.5 2.5 0 1 0 5 4.5 A 2.5 2.5 0 1 0 10 4.5 Z").toNodes()

private fun buildBubbles(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "bubbles",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = bubblesPath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = bubblesPath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = bubblesPath2,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = bubblesPath3,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
