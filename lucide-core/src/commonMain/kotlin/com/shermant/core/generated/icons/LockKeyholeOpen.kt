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

val lockKeyholeOpenProvider = ParameterizedIconProvider { parameters ->
    buildLockKeyholeOpen(parameters)
}

private val lockKeyholeOpenPath0 = PathParser().parsePathString("M 13 16 A 1 1 0 1 0 11 16 A 1 1 0 1 0 13 16 Z").toNodes()
private val lockKeyholeOpenPath1 = PathParser().parsePathString("M 5 10 H 19 A 2 2 0 0 1 21 12 V 20 A 2 2 0 0 1 19 22 H 5 A 2 2 0 0 1 3 20 V 12 A 2 2 0 0 1 5 10 Z").toNodes()
private val lockKeyholeOpenPath2 = PathParser().parsePathString("M7 10V7a5 5 0 0 1 9.33-2.5").toNodes()

private fun buildLockKeyholeOpen(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "lock-keyhole-open",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = lockKeyholeOpenPath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = lockKeyholeOpenPath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = lockKeyholeOpenPath2,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
