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

val layoutGridProvider = ParameterizedIconProvider { parameters ->
    buildLayoutGrid(parameters)
}

private val layoutGridPath0 = PathParser().parsePathString("M 4 3 H 9 A 1 1 0 0 1 10 4 V 9 A 1 1 0 0 1 9 10 H 4 A 1 1 0 0 1 3 9 V 4 A 1 1 0 0 1 4 3 Z").toNodes()
private val layoutGridPath1 = PathParser().parsePathString("M 15 3 H 20 A 1 1 0 0 1 21 4 V 9 A 1 1 0 0 1 20 10 H 15 A 1 1 0 0 1 14 9 V 4 A 1 1 0 0 1 15 3 Z").toNodes()
private val layoutGridPath2 = PathParser().parsePathString("M 15 14 H 20 A 1 1 0 0 1 21 15 V 20 A 1 1 0 0 1 20 21 H 15 A 1 1 0 0 1 14 20 V 15 A 1 1 0 0 1 15 14 Z").toNodes()
private val layoutGridPath3 = PathParser().parsePathString("M 4 14 H 9 A 1 1 0 0 1 10 15 V 20 A 1 1 0 0 1 9 21 H 4 A 1 1 0 0 1 3 20 V 15 A 1 1 0 0 1 4 14 Z").toNodes()

private fun buildLayoutGrid(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "layout-grid",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = layoutGridPath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = layoutGridPath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = layoutGridPath2,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = layoutGridPath3,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
