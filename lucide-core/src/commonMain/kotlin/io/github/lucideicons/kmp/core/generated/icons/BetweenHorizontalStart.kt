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

val betweenHorizontalStartProvider = ParameterizedIconProvider { parameters ->
    buildBetweenHorizontalStart(parameters)
}

private val betweenHorizontalStartPath0 = PathParser().parsePathString("M 9 3 H 20 A 1 1 0 0 1 21 4 V 9 A 1 1 0 0 1 20 10 H 9 A 1 1 0 0 1 8 9 V 4 A 1 1 0 0 1 9 3 Z").toNodes()
private val betweenHorizontalStartPath1 = PathParser().parsePathString("m2 9 3 3-3 3").toNodes()
private val betweenHorizontalStartPath2 = PathParser().parsePathString("M 9 14 H 20 A 1 1 0 0 1 21 15 V 20 A 1 1 0 0 1 20 21 H 9 A 1 1 0 0 1 8 20 V 15 A 1 1 0 0 1 9 14 Z").toNodes()

private fun buildBetweenHorizontalStart(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "between-horizontal-start",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = betweenHorizontalStartPath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = betweenHorizontalStartPath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = betweenHorizontalStartPath2,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
