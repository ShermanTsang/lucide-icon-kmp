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

val pauseProvider = ParameterizedIconProvider { parameters ->
    buildPause(parameters)
}

private val pausePath0 = PathParser().parsePathString("M 15 3 H 18 A 1 1 0 0 1 19 4 V 20 A 1 1 0 0 1 18 21 H 15 A 1 1 0 0 1 14 20 V 4 A 1 1 0 0 1 15 3 Z").toNodes()
private val pausePath1 = PathParser().parsePathString("M 6 3 H 9 A 1 1 0 0 1 10 4 V 20 A 1 1 0 0 1 9 21 H 6 A 1 1 0 0 1 5 20 V 4 A 1 1 0 0 1 6 3 Z").toNodes()

private fun buildPause(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "pause",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = pausePath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = pausePath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
