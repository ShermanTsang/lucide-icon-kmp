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

val circleDotDashedProvider = ParameterizedIconProvider { parameters ->
    buildCircleDotDashed(parameters)
}

private val circleDotDashedPath0 = PathParser().parsePathString("M10.1 2.18a9.93 9.93 0 0 1 3.8 0").toNodes()
private val circleDotDashedPath1 = PathParser().parsePathString("M17.6 3.71a9.95 9.95 0 0 1 2.69 2.7").toNodes()
private val circleDotDashedPath2 = PathParser().parsePathString("M21.82 10.1a9.93 9.93 0 0 1 0 3.8").toNodes()
private val circleDotDashedPath3 = PathParser().parsePathString("M20.29 17.6a9.95 9.95 0 0 1-2.7 2.69").toNodes()
private val circleDotDashedPath4 = PathParser().parsePathString("M13.9 21.82a9.94 9.94 0 0 1-3.8 0").toNodes()
private val circleDotDashedPath5 = PathParser().parsePathString("M6.4 20.29a9.95 9.95 0 0 1-2.69-2.7").toNodes()
private val circleDotDashedPath6 = PathParser().parsePathString("M2.18 13.9a9.93 9.93 0 0 1 0-3.8").toNodes()
private val circleDotDashedPath7 = PathParser().parsePathString("M3.71 6.4a9.95 9.95 0 0 1 2.7-2.69").toNodes()
private val circleDotDashedPath8 = PathParser().parsePathString("M 13 12 A 1 1 0 1 0 11 12 A 1 1 0 1 0 13 12 Z").toNodes()

private fun buildCircleDotDashed(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "circle-dot-dashed",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = circleDotDashedPath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = circleDotDashedPath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = circleDotDashedPath2,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = circleDotDashedPath3,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = circleDotDashedPath4,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = circleDotDashedPath5,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = circleDotDashedPath6,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = circleDotDashedPath7,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = circleDotDashedPath8,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
