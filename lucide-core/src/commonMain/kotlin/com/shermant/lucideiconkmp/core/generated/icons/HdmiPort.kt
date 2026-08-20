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

val hdmiPortProvider = ParameterizedIconProvider { parameters ->
    buildHdmiPort(parameters)
}

val HdmiPort: ImageVector
    get() = hdmiPortProvider.create()

val LucideIcons.HdmiPort: ImageVector
    get() = hdmiPortProvider.create()

private val hdmiPortPath0 = PathParser().parsePathString("M22 9a1 1 0 00-1-1H3a1 1 0 00-1 1v4a1 1 0 001 1h.5a2 2 0 011.6.8l.3.4A2 2 0 007 16h10a2 2 0 001.6-.8l.3-.4a2 2 0 011.6-.8h.5a1 1 0 001-1z").toNodes()
private val hdmiPortPath1 = PathParser().parsePathString("M8 12h8").toNodes()

private fun buildHdmiPort(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "hdmi-port",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = hdmiPortPath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = hdmiPortPath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
