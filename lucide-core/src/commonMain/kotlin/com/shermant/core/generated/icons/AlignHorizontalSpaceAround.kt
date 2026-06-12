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

val alignHorizontalSpaceAroundProvider = ParameterizedIconProvider { parameters ->
    buildAlignHorizontalSpaceAround(parameters)
}

val AlignHorizontalSpaceAround: ImageVector
    get() = alignHorizontalSpaceAroundProvider.create()

private val alignHorizontalSpaceAroundPath0 = PathParser().parsePathString("M 11 7 H 13 A 2 2 0 0 1 15 9 V 15 A 2 2 0 0 1 13 17 H 11 A 2 2 0 0 1 9 15 V 9 A 2 2 0 0 1 11 7 Z").toNodes()
private val alignHorizontalSpaceAroundPath1 = PathParser().parsePathString("M4 22V2").toNodes()
private val alignHorizontalSpaceAroundPath2 = PathParser().parsePathString("M20 22V2").toNodes()

private fun buildAlignHorizontalSpaceAround(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "align-horizontal-space-around",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = alignHorizontalSpaceAroundPath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = alignHorizontalSpaceAroundPath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = alignHorizontalSpaceAroundPath2,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
