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

val alignHorizontalDistributeEndProvider = ParameterizedIconProvider { parameters ->
    buildAlignHorizontalDistributeEnd(parameters)
}

val AlignHorizontalDistributeEnd: ImageVector
    get() = alignHorizontalDistributeEndProvider.create()

private val alignHorizontalDistributeEndPath0 = PathParser().parsePathString("M 6 5 H 8 A 2 2 0 0 1 10 7 V 17 A 2 2 0 0 1 8 19 H 6 A 2 2 0 0 1 4 17 V 7 A 2 2 0 0 1 6 5 Z").toNodes()
private val alignHorizontalDistributeEndPath1 = PathParser().parsePathString("M 16 7 H 18 A 2 2 0 0 1 20 9 V 15 A 2 2 0 0 1 18 17 H 16 A 2 2 0 0 1 14 15 V 9 A 2 2 0 0 1 16 7 Z").toNodes()
private val alignHorizontalDistributeEndPath2 = PathParser().parsePathString("M10 2v20").toNodes()
private val alignHorizontalDistributeEndPath3 = PathParser().parsePathString("M20 2v20").toNodes()

private fun buildAlignHorizontalDistributeEnd(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "align-horizontal-distribute-end",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = alignHorizontalDistributeEndPath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = alignHorizontalDistributeEndPath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = alignHorizontalDistributeEndPath2,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = alignHorizontalDistributeEndPath3,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
