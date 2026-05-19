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

val sendToBackProvider = ParameterizedIconProvider { parameters ->
    buildSendToBack(parameters)
}

private val sendToBackPath0 = PathParser().parsePathString("M 16 14 H 20 A 2 2 0 0 1 22 16 V 20 A 2 2 0 0 1 20 22 H 16 A 2 2 0 0 1 14 20 V 16 A 2 2 0 0 1 16 14 Z").toNodes()
private val sendToBackPath1 = PathParser().parsePathString("M 4 2 H 8 A 2 2 0 0 1 10 4 V 8 A 2 2 0 0 1 8 10 H 4 A 2 2 0 0 1 2 8 V 4 A 2 2 0 0 1 4 2 Z").toNodes()
private val sendToBackPath2 = PathParser().parsePathString("M7 14v1a2 2 0 0 0 2 2h1").toNodes()
private val sendToBackPath3 = PathParser().parsePathString("M14 7h1a2 2 0 0 1 2 2v1").toNodes()

private fun buildSendToBack(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "send-to-back",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = sendToBackPath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = sendToBackPath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = sendToBackPath2,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = sendToBackPath3,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
