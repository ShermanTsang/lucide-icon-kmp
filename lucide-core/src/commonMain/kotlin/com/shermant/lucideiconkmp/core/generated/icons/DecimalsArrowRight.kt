package com.shermant.lucideiconkmp.core.generated.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.unit.dp
import com.shermant.lucideiconkmp.core.registry.IconRenderParameters
import com.shermant.lucideiconkmp.core.registry.LucideIcons
import com.shermant.lucideiconkmp.core.registry.ParameterizedIconProvider
import com.shermant.lucideiconkmp.core.registry.create

val decimalsArrowRightProvider = ParameterizedIconProvider { parameters ->
    buildDecimalsArrowRight(parameters)
}

val DecimalsArrowRight: ImageVector
    get() = decimalsArrowRightProvider.create()

val LucideIcons.DecimalsArrowRight: ImageVector
    get() = decimalsArrowRightProvider.create()

private val decimalsArrowRightPath0 = PathParser().parsePathString("M10 18h10").toNodes()
private val decimalsArrowRightPath1 = PathParser().parsePathString("m17 21 3-3-3-3").toNodes()
private val decimalsArrowRightPath2 = PathParser().parsePathString("M3 11h.01").toNodes()
private val decimalsArrowRightPath3 = PathParser().parsePathString("M 17.5 3 H 17.5 A 2.5 2.5 0 0 1 20 5.5 V 8.5 A 2.5 2.5 0 0 1 17.5 11 H 17.5 A 2.5 2.5 0 0 1 15 8.5 V 5.5 A 2.5 2.5 0 0 1 17.5 3 Z").toNodes()
private val decimalsArrowRightPath4 = PathParser().parsePathString("M 8.5 3 H 8.5 A 2.5 2.5 0 0 1 11 5.5 V 8.5 A 2.5 2.5 0 0 1 8.5 11 H 8.5 A 2.5 2.5 0 0 1 6 8.5 V 5.5 A 2.5 2.5 0 0 1 8.5 3 Z").toNodes()

private fun buildDecimalsArrowRight(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "decimals-arrow-right",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = decimalsArrowRightPath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = decimalsArrowRightPath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = decimalsArrowRightPath2,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = decimalsArrowRightPath3,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = decimalsArrowRightPath4,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
