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

val gripProvider = ParameterizedIconProvider { parameters ->
    buildGrip(parameters)
}

val Grip: ImageVector
    get() = gripProvider.create()

private val gripPath0 = PathParser().parsePathString("M 13 5 A 1 1 0 1 0 11 5 A 1 1 0 1 0 13 5 Z").toNodes()
private val gripPath1 = PathParser().parsePathString("M 20 5 A 1 1 0 1 0 18 5 A 1 1 0 1 0 20 5 Z").toNodes()
private val gripPath2 = PathParser().parsePathString("M 6 5 A 1 1 0 1 0 4 5 A 1 1 0 1 0 6 5 Z").toNodes()
private val gripPath3 = PathParser().parsePathString("M 13 12 A 1 1 0 1 0 11 12 A 1 1 0 1 0 13 12 Z").toNodes()
private val gripPath4 = PathParser().parsePathString("M 20 12 A 1 1 0 1 0 18 12 A 1 1 0 1 0 20 12 Z").toNodes()
private val gripPath5 = PathParser().parsePathString("M 6 12 A 1 1 0 1 0 4 12 A 1 1 0 1 0 6 12 Z").toNodes()
private val gripPath6 = PathParser().parsePathString("M 13 19 A 1 1 0 1 0 11 19 A 1 1 0 1 0 13 19 Z").toNodes()
private val gripPath7 = PathParser().parsePathString("M 20 19 A 1 1 0 1 0 18 19 A 1 1 0 1 0 20 19 Z").toNodes()
private val gripPath8 = PathParser().parsePathString("M 6 19 A 1 1 0 1 0 4 19 A 1 1 0 1 0 6 19 Z").toNodes()

private fun buildGrip(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "grip",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = gripPath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = gripPath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = gripPath2,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = gripPath3,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = gripPath4,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = gripPath5,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = gripPath6,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = gripPath7,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = gripPath8,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
