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

val chartColumnBigProvider = ParameterizedIconProvider { parameters ->
    buildChartColumnBig(parameters)
}

val ChartColumnBig: ImageVector
    get() = chartColumnBigProvider.create()

private val chartColumnBigPath0 = PathParser().parsePathString("M3 3v16a2 2 0 0 0 2 2h16").toNodes()
private val chartColumnBigPath1 = PathParser().parsePathString("M 16 5 H 18 A 1 1 0 0 1 19 6 V 16 A 1 1 0 0 1 18 17 H 16 A 1 1 0 0 1 15 16 V 6 A 1 1 0 0 1 16 5 Z").toNodes()
private val chartColumnBigPath2 = PathParser().parsePathString("M 8 8 H 10 A 1 1 0 0 1 11 9 V 16 A 1 1 0 0 1 10 17 H 8 A 1 1 0 0 1 7 16 V 9 A 1 1 0 0 1 8 8 Z").toNodes()

private fun buildChartColumnBig(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "chart-column-big",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = chartColumnBigPath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = chartColumnBigPath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = chartColumnBigPath2,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
