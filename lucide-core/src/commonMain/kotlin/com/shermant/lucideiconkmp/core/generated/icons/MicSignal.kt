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

val micSignalProvider = ParameterizedIconProvider { parameters ->
    buildMicSignal(parameters)
}

val MicSignal: ImageVector
    get() = micSignalProvider.create()

val LucideIcons.MicSignal: ImageVector
    get() = micSignalProvider.create()

private val micSignalPath0 = PathParser().parsePathString("M12 17v4").toNodes()
private val micSignalPath1 = PathParser().parsePathString("M18 11a6 6 0 00-3-5.197").toNodes()
private val micSignalPath2 = PathParser().parsePathString("M2 11a10 10 0 015-8.662").toNodes()
private val micSignalPath3 = PathParser().parsePathString("M22 11a10 10 0 00-5-8.662").toNodes()
private val micSignalPath4 = PathParser().parsePathString("M6 11a6 6 0 013-5.197").toNodes()
private val micSignalPath5 = PathParser().parsePathString("M9 21h6").toNodes()
private val micSignalPath6 = PathParser().parsePathString("M 12 9 H 12 A 2 2 0 0 1 14 11 V 15 A 2 2 0 0 1 12 17 H 12 A 2 2 0 0 1 10 15 V 11 A 2 2 0 0 1 12 9 Z").toNodes()

private fun buildMicSignal(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "mic-signal",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = micSignalPath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = micSignalPath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = micSignalPath2,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = micSignalPath3,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = micSignalPath4,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = micSignalPath5,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = micSignalPath6,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
