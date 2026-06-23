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

val chartColumnStackedProvider = ParameterizedIconProvider { parameters ->
    buildChartColumnStacked(parameters)
}

val ChartColumnStacked: ImageVector
    get() = chartColumnStackedProvider.create()

val LucideIcons.ChartColumnStacked: ImageVector
    get() = chartColumnStackedProvider.create()

private val chartColumnStackedPath0 = PathParser().parsePathString("M11 13H7").toNodes()
private val chartColumnStackedPath1 = PathParser().parsePathString("M19 9h-4").toNodes()
private val chartColumnStackedPath2 = PathParser().parsePathString("M3 3v16a2 2 0 0 0 2 2h16").toNodes()
private val chartColumnStackedPath3 = PathParser().parsePathString("M 16 5 H 18 A 1 1 0 0 1 19 6 V 16 A 1 1 0 0 1 18 17 H 16 A 1 1 0 0 1 15 16 V 6 A 1 1 0 0 1 16 5 Z").toNodes()
private val chartColumnStackedPath4 = PathParser().parsePathString("M 8 8 H 10 A 1 1 0 0 1 11 9 V 16 A 1 1 0 0 1 10 17 H 8 A 1 1 0 0 1 7 16 V 9 A 1 1 0 0 1 8 8 Z").toNodes()

private fun buildChartColumnStacked(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "chart-column-stacked",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = chartColumnStackedPath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = chartColumnStackedPath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = chartColumnStackedPath2,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = chartColumnStackedPath3,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = chartColumnStackedPath4,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
