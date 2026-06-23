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

val monitorSpeakerProvider = ParameterizedIconProvider { parameters ->
    buildMonitorSpeaker(parameters)
}

val MonitorSpeaker: ImageVector
    get() = monitorSpeakerProvider.create()

val LucideIcons.MonitorSpeaker: ImageVector
    get() = monitorSpeakerProvider.create()

private val monitorSpeakerPath0 = PathParser().parsePathString("M5.5 20H8").toNodes()
private val monitorSpeakerPath1 = PathParser().parsePathString("M17 9h.01").toNodes()
private val monitorSpeakerPath2 = PathParser().parsePathString("M 14 4 H 20 A 2 2 0 0 1 22 6 V 18 A 2 2 0 0 1 20 20 H 14 A 2 2 0 0 1 12 18 V 6 A 2 2 0 0 1 14 4 Z").toNodes()
private val monitorSpeakerPath3 = PathParser().parsePathString("M8 6H4a2 2 0 0 0-2 2v6a2 2 0 0 0 2 2h4").toNodes()
private val monitorSpeakerPath4 = PathParser().parsePathString("M 18 15 A 1 1 0 1 0 16 15 A 1 1 0 1 0 18 15 Z").toNodes()

private fun buildMonitorSpeaker(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "monitor-speaker",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = monitorSpeakerPath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = monitorSpeakerPath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = monitorSpeakerPath2,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = monitorSpeakerPath3,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = monitorSpeakerPath4,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
