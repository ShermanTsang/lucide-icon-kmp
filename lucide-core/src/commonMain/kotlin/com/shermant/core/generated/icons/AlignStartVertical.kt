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

val alignStartVerticalProvider = ParameterizedIconProvider { parameters ->
    buildAlignStartVertical(parameters)
}

private val alignStartVerticalPath0 = PathParser().parsePathString("M 8 14 H 13 A 2 2 0 0 1 15 16 V 18 A 2 2 0 0 1 13 20 H 8 A 2 2 0 0 1 6 18 V 16 A 2 2 0 0 1 8 14 Z").toNodes()
private val alignStartVerticalPath1 = PathParser().parsePathString("M 8 4 H 20 A 2 2 0 0 1 22 6 V 8 A 2 2 0 0 1 20 10 H 8 A 2 2 0 0 1 6 8 V 6 A 2 2 0 0 1 8 4 Z").toNodes()
private val alignStartVerticalPath2 = PathParser().parsePathString("M2 2v20").toNodes()

private fun buildAlignStartVertical(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "align-start-vertical",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = alignStartVerticalPath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = alignStartVerticalPath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = alignStartVerticalPath2,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
