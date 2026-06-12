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

val alignCenterHorizontalProvider = ParameterizedIconProvider { parameters ->
    buildAlignCenterHorizontal(parameters)
}

val AlignCenterHorizontal: ImageVector
    get() = alignCenterHorizontalProvider.create()

private val alignCenterHorizontalPath0 = PathParser().parsePathString("M2 12h20").toNodes()
private val alignCenterHorizontalPath1 = PathParser().parsePathString("M10 16v4a2 2 0 0 1-2 2H6a2 2 0 0 1-2-2v-4").toNodes()
private val alignCenterHorizontalPath2 = PathParser().parsePathString("M10 8V4a2 2 0 0 0-2-2H6a2 2 0 0 0-2 2v4").toNodes()
private val alignCenterHorizontalPath3 = PathParser().parsePathString("M20 16v1a2 2 0 0 1-2 2h-2a2 2 0 0 1-2-2v-1").toNodes()
private val alignCenterHorizontalPath4 = PathParser().parsePathString("M14 8V7c0-1.1.9-2 2-2h2a2 2 0 0 1 2 2v1").toNodes()

private fun buildAlignCenterHorizontal(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "align-center-horizontal",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = alignCenterHorizontalPath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = alignCenterHorizontalPath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = alignCenterHorizontalPath2,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = alignCenterHorizontalPath3,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = alignCenterHorizontalPath4,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
