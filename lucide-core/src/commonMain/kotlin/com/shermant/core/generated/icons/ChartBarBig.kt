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

val chartBarBigProvider = ParameterizedIconProvider { parameters ->
    buildChartBarBig(parameters)
}

val ChartBarBig: ImageVector
    get() = chartBarBigProvider.create()

private val chartBarBigPath0 = PathParser().parsePathString("M3 3v16a2 2 0 0 0 2 2h16").toNodes()
private val chartBarBigPath1 = PathParser().parsePathString("M 8 13 H 15 A 1 1 0 0 1 16 14 V 16 A 1 1 0 0 1 15 17 H 8 A 1 1 0 0 1 7 16 V 14 A 1 1 0 0 1 8 13 Z").toNodes()
private val chartBarBigPath2 = PathParser().parsePathString("M 8 5 H 18 A 1 1 0 0 1 19 6 V 8 A 1 1 0 0 1 18 9 H 8 A 1 1 0 0 1 7 8 V 6 A 1 1 0 0 1 8 5 Z").toNodes()

private fun buildChartBarBig(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "chart-bar-big",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = chartBarBigPath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = chartBarBigPath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = chartBarBigPath2,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
