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

val scanSquareProvider = ParameterizedIconProvider { parameters ->
    buildScanSquare(parameters)
}

val ScanSquare: ImageVector
    get() = scanSquareProvider.create()

val LucideIcons.ScanSquare: ImageVector
    get() = scanSquareProvider.create()

private val scanSquarePath0 = PathParser().parsePathString("M3 7V5a2 2 0 0 1 2-2h2").toNodes()
private val scanSquarePath1 = PathParser().parsePathString("M17 3h2a2 2 0 0 1 2 2v2").toNodes()
private val scanSquarePath2 = PathParser().parsePathString("M21 17v2a2 2 0 0 1-2 2h-2").toNodes()
private val scanSquarePath3 = PathParser().parsePathString("M7 21H5a2 2 0 0 1-2-2v-2").toNodes()
private val scanSquarePath4 = PathParser().parsePathString("M 9 8 H 15 A 1 1 0 0 1 16 9 V 15 A 1 1 0 0 1 15 16 H 9 A 1 1 0 0 1 8 15 V 9 A 1 1 0 0 1 9 8 Z").toNodes()

private fun buildScanSquare(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "scan-square",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = scanSquarePath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = scanSquarePath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = scanSquarePath2,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = scanSquarePath3,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = scanSquarePath4,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
