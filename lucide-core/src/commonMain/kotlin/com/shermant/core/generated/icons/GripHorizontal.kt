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

val gripHorizontalProvider = ParameterizedIconProvider { parameters ->
    buildGripHorizontal(parameters)
}

val GripHorizontal: ImageVector
    get() = gripHorizontalProvider.create()

private val gripHorizontalPath0 = PathParser().parsePathString("M 13 9 A 1 1 0 1 0 11 9 A 1 1 0 1 0 13 9 Z").toNodes()
private val gripHorizontalPath1 = PathParser().parsePathString("M 20 9 A 1 1 0 1 0 18 9 A 1 1 0 1 0 20 9 Z").toNodes()
private val gripHorizontalPath2 = PathParser().parsePathString("M 6 9 A 1 1 0 1 0 4 9 A 1 1 0 1 0 6 9 Z").toNodes()
private val gripHorizontalPath3 = PathParser().parsePathString("M 13 15 A 1 1 0 1 0 11 15 A 1 1 0 1 0 13 15 Z").toNodes()
private val gripHorizontalPath4 = PathParser().parsePathString("M 20 15 A 1 1 0 1 0 18 15 A 1 1 0 1 0 20 15 Z").toNodes()
private val gripHorizontalPath5 = PathParser().parsePathString("M 6 15 A 1 1 0 1 0 4 15 A 1 1 0 1 0 6 15 Z").toNodes()

private fun buildGripHorizontal(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "grip-horizontal",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = gripHorizontalPath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = gripHorizontalPath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = gripHorizontalPath2,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = gripHorizontalPath3,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = gripHorizontalPath4,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = gripHorizontalPath5,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
