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

val chartCandlestickProvider = ParameterizedIconProvider { parameters ->
    buildChartCandlestick(parameters)
}

val ChartCandlestick: ImageVector
    get() = chartCandlestickProvider.create()

private val chartCandlestickPath0 = PathParser().parsePathString("M9 5v4").toNodes()
private val chartCandlestickPath1 = PathParser().parsePathString("M 8 9 H 10 A 1 1 0 0 1 11 10 V 14 A 1 1 0 0 1 10 15 H 8 A 1 1 0 0 1 7 14 V 10 A 1 1 0 0 1 8 9 Z").toNodes()
private val chartCandlestickPath2 = PathParser().parsePathString("M9 15v2").toNodes()
private val chartCandlestickPath3 = PathParser().parsePathString("M17 3v2").toNodes()
private val chartCandlestickPath4 = PathParser().parsePathString("M 16 5 H 18 A 1 1 0 0 1 19 6 V 12 A 1 1 0 0 1 18 13 H 16 A 1 1 0 0 1 15 12 V 6 A 1 1 0 0 1 16 5 Z").toNodes()
private val chartCandlestickPath5 = PathParser().parsePathString("M17 13v3").toNodes()
private val chartCandlestickPath6 = PathParser().parsePathString("M3 3v16a2 2 0 0 0 2 2h16").toNodes()

private fun buildChartCandlestick(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "chart-candlestick",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = chartCandlestickPath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = chartCandlestickPath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = chartCandlestickPath2,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = chartCandlestickPath3,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = chartCandlestickPath4,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = chartCandlestickPath5,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = chartCandlestickPath6,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
