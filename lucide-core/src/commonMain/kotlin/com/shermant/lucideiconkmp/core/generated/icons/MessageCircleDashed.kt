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

val messageCircleDashedProvider = ParameterizedIconProvider { parameters ->
    buildMessageCircleDashed(parameters)
}

val MessageCircleDashed: ImageVector
    get() = messageCircleDashedProvider.create()

private val messageCircleDashedPath0 = PathParser().parsePathString("M10.1 2.182a10 10 0 0 1 3.8 0").toNodes()
private val messageCircleDashedPath1 = PathParser().parsePathString("M13.9 21.818a10 10 0 0 1-3.8 0").toNodes()
private val messageCircleDashedPath2 = PathParser().parsePathString("M17.609 3.72a10 10 0 0 1 2.69 2.7").toNodes()
private val messageCircleDashedPath3 = PathParser().parsePathString("M2.182 13.9a10 10 0 0 1 0-3.8").toNodes()
private val messageCircleDashedPath4 = PathParser().parsePathString("M20.28 17.61a10 10 0 0 1-2.7 2.69").toNodes()
private val messageCircleDashedPath5 = PathParser().parsePathString("M21.818 10.1a10 10 0 0 1 0 3.8").toNodes()
private val messageCircleDashedPath6 = PathParser().parsePathString("M3.721 6.391a10 10 0 0 1 2.7-2.69").toNodes()
private val messageCircleDashedPath7 = PathParser().parsePathString("m6.163 21.117-2.906.85a1 1 0 0 1-1.236-1.169l.965-2.98").toNodes()

private fun buildMessageCircleDashed(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "message-circle-dashed",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = messageCircleDashedPath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = messageCircleDashedPath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = messageCircleDashedPath2,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = messageCircleDashedPath3,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = messageCircleDashedPath4,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = messageCircleDashedPath5,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = messageCircleDashedPath6,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = messageCircleDashedPath7,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
