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

val ticketsPlaneProvider = ParameterizedIconProvider { parameters ->
    buildTicketsPlane(parameters)
}

val TicketsPlane: ImageVector
    get() = ticketsPlaneProvider.create()

private val ticketsPlanePath0 = PathParser().parsePathString("M10.5 17h1.227a2 2 0 0 0 1.345-.52L18 12").toNodes()
private val ticketsPlanePath1 = PathParser().parsePathString("m12 13.5 3.794.506").toNodes()
private val ticketsPlanePath2 = PathParser().parsePathString("m3.173 8.18 11-5a2 2 0 0 1 2.647.993L18.56 8").toNodes()
private val ticketsPlanePath3 = PathParser().parsePathString("M6 10V8").toNodes()
private val ticketsPlanePath4 = PathParser().parsePathString("M6 14v1").toNodes()
private val ticketsPlanePath5 = PathParser().parsePathString("M6 19v2").toNodes()
private val ticketsPlanePath6 = PathParser().parsePathString("M 4 8 H 20 A 2 2 0 0 1 22 10 V 19 A 2 2 0 0 1 20 21 H 4 A 2 2 0 0 1 2 19 V 10 A 2 2 0 0 1 4 8 Z").toNodes()

private fun buildTicketsPlane(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "tickets-plane",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = ticketsPlanePath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = ticketsPlanePath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = ticketsPlanePath2,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = ticketsPlanePath3,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = ticketsPlanePath4,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = ticketsPlanePath5,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = ticketsPlanePath6,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
