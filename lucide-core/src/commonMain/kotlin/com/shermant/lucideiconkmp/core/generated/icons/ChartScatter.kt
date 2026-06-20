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
import com.shermant.lucideiconkmp.core.registry.ParameterizedIconProvider
import com.shermant.lucideiconkmp.core.registry.create

val chartScatterProvider = ParameterizedIconProvider { parameters ->
    buildChartScatter(parameters)
}

val ChartScatter: ImageVector
    get() = chartScatterProvider.create()

private val chartScatterPath0 = PathParser().parsePathString("M 8 7.5 A 0.5 0.5 0 1 0 7 7.5 A 0.5 0.5 0 1 0 8 7.5 Z").toNodes()
private val chartScatterPath1 = PathParser().parsePathString("M 19 5.5 A 0.5 0.5 0 1 0 18 5.5 A 0.5 0.5 0 1 0 19 5.5 Z").toNodes()
private val chartScatterPath2 = PathParser().parsePathString("M 12 11.5 A 0.5 0.5 0 1 0 11 11.5 A 0.5 0.5 0 1 0 12 11.5 Z").toNodes()
private val chartScatterPath3 = PathParser().parsePathString("M 8 16.5 A 0.5 0.5 0 1 0 7 16.5 A 0.5 0.5 0 1 0 8 16.5 Z").toNodes()
private val chartScatterPath4 = PathParser().parsePathString("M 18 14.5 A 0.5 0.5 0 1 0 17 14.5 A 0.5 0.5 0 1 0 18 14.5 Z").toNodes()
private val chartScatterPath5 = PathParser().parsePathString("M3 3v16a2 2 0 0 0 2 2h16").toNodes()

private fun buildChartScatter(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "chart-scatter",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = chartScatterPath0,
            pathFillType = PathFillType.NonZero,
            fill = SolidColor(Color.Black),
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = chartScatterPath1,
            pathFillType = PathFillType.NonZero,
            fill = SolidColor(Color.Black),
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = chartScatterPath2,
            pathFillType = PathFillType.NonZero,
            fill = SolidColor(Color.Black),
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = chartScatterPath3,
            pathFillType = PathFillType.NonZero,
            fill = SolidColor(Color.Black),
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = chartScatterPath4,
            pathFillType = PathFillType.NonZero,
            fill = SolidColor(Color.Black),
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = chartScatterPath5,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
