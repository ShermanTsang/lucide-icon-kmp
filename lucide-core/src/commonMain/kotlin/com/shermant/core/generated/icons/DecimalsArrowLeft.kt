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

val decimalsArrowLeftProvider = ParameterizedIconProvider { parameters ->
    buildDecimalsArrowLeft(parameters)
}

val DecimalsArrowLeft: ImageVector
    get() = decimalsArrowLeftProvider.create()

private val decimalsArrowLeftPath0 = PathParser().parsePathString("m13 21-3-3 3-3").toNodes()
private val decimalsArrowLeftPath1 = PathParser().parsePathString("M20 18H10").toNodes()
private val decimalsArrowLeftPath2 = PathParser().parsePathString("M3 11h.01").toNodes()
private val decimalsArrowLeftPath3 = PathParser().parsePathString("M 8.5 3 H 8.5 A 2.5 2.5 0 0 1 11 5.5 V 8.5 A 2.5 2.5 0 0 1 8.5 11 H 8.5 A 2.5 2.5 0 0 1 6 8.5 V 5.5 A 2.5 2.5 0 0 1 8.5 3 Z").toNodes()

private fun buildDecimalsArrowLeft(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "decimals-arrow-left",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = decimalsArrowLeftPath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = decimalsArrowLeftPath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = decimalsArrowLeftPath2,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = decimalsArrowLeftPath3,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
