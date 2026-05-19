package io.github.lucideicons.kmp.core.generated.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.unit.dp
import io.github.lucideicons.kmp.core.registry.IconRenderParameters
import io.github.lucideicons.kmp.core.registry.ParameterizedIconProvider

val alignHorizontalDistributeCenterProvider = ParameterizedIconProvider { parameters ->
    buildAlignHorizontalDistributeCenter(parameters)
}

private val alignHorizontalDistributeCenterPath0 = PathParser().parsePathString("M 6 5 H 8 A 2 2 0 0 1 10 7 V 17 A 2 2 0 0 1 8 19 H 6 A 2 2 0 0 1 4 17 V 7 A 2 2 0 0 1 6 5 Z").toNodes()
private val alignHorizontalDistributeCenterPath1 = PathParser().parsePathString("M 16 7 H 18 A 2 2 0 0 1 20 9 V 15 A 2 2 0 0 1 18 17 H 16 A 2 2 0 0 1 14 15 V 9 A 2 2 0 0 1 16 7 Z").toNodes()
private val alignHorizontalDistributeCenterPath2 = PathParser().parsePathString("M17 22v-5").toNodes()
private val alignHorizontalDistributeCenterPath3 = PathParser().parsePathString("M17 7V2").toNodes()
private val alignHorizontalDistributeCenterPath4 = PathParser().parsePathString("M7 22v-3").toNodes()
private val alignHorizontalDistributeCenterPath5 = PathParser().parsePathString("M7 5V2").toNodes()

private fun buildAlignHorizontalDistributeCenter(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "align-horizontal-distribute-center",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = alignHorizontalDistributeCenterPath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = alignHorizontalDistributeCenterPath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = alignHorizontalDistributeCenterPath2,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = alignHorizontalDistributeCenterPath3,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = alignHorizontalDistributeCenterPath4,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = alignHorizontalDistributeCenterPath5,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
