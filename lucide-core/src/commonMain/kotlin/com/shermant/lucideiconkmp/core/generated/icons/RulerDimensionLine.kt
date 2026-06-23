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

val rulerDimensionLineProvider = ParameterizedIconProvider { parameters ->
    buildRulerDimensionLine(parameters)
}

val RulerDimensionLine: ImageVector
    get() = rulerDimensionLineProvider.create()

val LucideIcons.RulerDimensionLine: ImageVector
    get() = rulerDimensionLineProvider.create()

private val rulerDimensionLinePath0 = PathParser().parsePathString("M10 15v-3").toNodes()
private val rulerDimensionLinePath1 = PathParser().parsePathString("M14 15v-3").toNodes()
private val rulerDimensionLinePath2 = PathParser().parsePathString("M18 15v-3").toNodes()
private val rulerDimensionLinePath3 = PathParser().parsePathString("M2 8V4").toNodes()
private val rulerDimensionLinePath4 = PathParser().parsePathString("M22 6H2").toNodes()
private val rulerDimensionLinePath5 = PathParser().parsePathString("M22 8V4").toNodes()
private val rulerDimensionLinePath6 = PathParser().parsePathString("M6 15v-3").toNodes()
private val rulerDimensionLinePath7 = PathParser().parsePathString("M 4 12 H 20 A 2 2 0 0 1 22 14 V 18 A 2 2 0 0 1 20 20 H 4 A 2 2 0 0 1 2 18 V 14 A 2 2 0 0 1 4 12 Z").toNodes()

private fun buildRulerDimensionLine(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "ruler-dimension-line",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = rulerDimensionLinePath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = rulerDimensionLinePath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = rulerDimensionLinePath2,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = rulerDimensionLinePath3,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = rulerDimensionLinePath4,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = rulerDimensionLinePath5,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = rulerDimensionLinePath6,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = rulerDimensionLinePath7,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
