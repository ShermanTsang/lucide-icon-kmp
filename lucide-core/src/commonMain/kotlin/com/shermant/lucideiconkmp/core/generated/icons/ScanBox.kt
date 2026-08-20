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

val scanBoxProvider = ParameterizedIconProvider { parameters ->
    buildScanBox(parameters)
}

val ScanBox: ImageVector
    get() = scanBoxProvider.create()

val LucideIcons.ScanBox: ImageVector
    get() = scanBoxProvider.create()

private val scanBoxPath0 = PathParser().parsePathString("M12 12v5.5").toNodes()
private val scanBoxPath1 = PathParser().parsePathString("M17 3h2a2 2 0 012 2v2").toNodes()
private val scanBoxPath2 = PathParser().parsePathString("M21 17v2a2 2 0 01-2 2h-2").toNodes()
private val scanBoxPath3 = PathParser().parsePathString("M3 7V5a2 2 0 012-2h2").toNodes()
private val scanBoxPath4 = PathParser().parsePathString("M7 21H5a2 2 0 01-2-2v-2").toNodes()
private val scanBoxPath5 = PathParser().parsePathString("M7.264 9.252 12 12l4.737-2.748").toNodes()
private val scanBoxPath6 = PathParser().parsePathString("M7.995 8.514A2 2 0 007 10.244v3.516a2 2 0 00.996 1.73l3 1.74a2 2 0 002.008 0l3-1.74A2 2 0 0017 13.76v-3.517a2 2 0 00-.995-1.73l-3-1.742a2 2 0 00-1.892-.064z").toNodes()

private fun buildScanBox(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "scan-box",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = scanBoxPath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = scanBoxPath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = scanBoxPath2,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = scanBoxPath3,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = scanBoxPath4,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = scanBoxPath5,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = scanBoxPath6,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
