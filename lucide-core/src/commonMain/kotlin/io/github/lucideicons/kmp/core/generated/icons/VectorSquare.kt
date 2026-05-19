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

val vectorSquareProvider = ParameterizedIconProvider { parameters ->
    buildVectorSquare(parameters)
}

private val vectorSquarePath0 = PathParser().parsePathString("M19.5 7a24 24 0 0 1 0 10").toNodes()
private val vectorSquarePath1 = PathParser().parsePathString("M4.5 7a24 24 0 0 0 0 10").toNodes()
private val vectorSquarePath2 = PathParser().parsePathString("M7 19.5a24 24 0 0 0 10 0").toNodes()
private val vectorSquarePath3 = PathParser().parsePathString("M7 4.5a24 24 0 0 1 10 0").toNodes()
private val vectorSquarePath4 = PathParser().parsePathString("M 18 17 H 21 A 1 1 0 0 1 22 18 V 21 A 1 1 0 0 1 21 22 H 18 A 1 1 0 0 1 17 21 V 18 A 1 1 0 0 1 18 17 Z").toNodes()
private val vectorSquarePath5 = PathParser().parsePathString("M 18 2 H 21 A 1 1 0 0 1 22 3 V 6 A 1 1 0 0 1 21 7 H 18 A 1 1 0 0 1 17 6 V 3 A 1 1 0 0 1 18 2 Z").toNodes()
private val vectorSquarePath6 = PathParser().parsePathString("M 3 17 H 6 A 1 1 0 0 1 7 18 V 21 A 1 1 0 0 1 6 22 H 3 A 1 1 0 0 1 2 21 V 18 A 1 1 0 0 1 3 17 Z").toNodes()
private val vectorSquarePath7 = PathParser().parsePathString("M 3 2 H 6 A 1 1 0 0 1 7 3 V 6 A 1 1 0 0 1 6 7 H 3 A 1 1 0 0 1 2 6 V 3 A 1 1 0 0 1 3 2 Z").toNodes()

private fun buildVectorSquare(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "vector-square",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = vectorSquarePath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = vectorSquarePath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = vectorSquarePath2,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = vectorSquarePath3,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = vectorSquarePath4,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = vectorSquarePath5,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = vectorSquarePath6,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = vectorSquarePath7,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
