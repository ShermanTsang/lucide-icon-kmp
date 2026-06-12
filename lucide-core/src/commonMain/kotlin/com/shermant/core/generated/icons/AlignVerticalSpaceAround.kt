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

val alignVerticalSpaceAroundProvider = ParameterizedIconProvider { parameters ->
    buildAlignVerticalSpaceAround(parameters)
}

val AlignVerticalSpaceAround: ImageVector
    get() = alignVerticalSpaceAroundProvider.create()

private val alignVerticalSpaceAroundPath0 = PathParser().parsePathString("M 9 9 H 15 A 2 2 0 0 1 17 11 V 13 A 2 2 0 0 1 15 15 H 9 A 2 2 0 0 1 7 13 V 11 A 2 2 0 0 1 9 9 Z").toNodes()
private val alignVerticalSpaceAroundPath1 = PathParser().parsePathString("M22 20H2").toNodes()
private val alignVerticalSpaceAroundPath2 = PathParser().parsePathString("M22 4H2").toNodes()

private fun buildAlignVerticalSpaceAround(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "align-vertical-space-around",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = alignVerticalSpaceAroundPath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = alignVerticalSpaceAroundPath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = alignVerticalSpaceAroundPath2,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
