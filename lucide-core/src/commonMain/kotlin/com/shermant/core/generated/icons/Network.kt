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
import com.shermant.core.registry.create

val networkProvider = ParameterizedIconProvider { parameters ->
    buildNetwork(parameters)
}

val Network: ImageVector
    get() = networkProvider.create()

private val networkPath0 = PathParser().parsePathString("M 17 16 H 21 A 1 1 0 0 1 22 17 V 21 A 1 1 0 0 1 21 22 H 17 A 1 1 0 0 1 16 21 V 17 A 1 1 0 0 1 17 16 Z").toNodes()
private val networkPath1 = PathParser().parsePathString("M 3 16 H 7 A 1 1 0 0 1 8 17 V 21 A 1 1 0 0 1 7 22 H 3 A 1 1 0 0 1 2 21 V 17 A 1 1 0 0 1 3 16 Z").toNodes()
private val networkPath2 = PathParser().parsePathString("M 10 2 H 14 A 1 1 0 0 1 15 3 V 7 A 1 1 0 0 1 14 8 H 10 A 1 1 0 0 1 9 7 V 3 A 1 1 0 0 1 10 2 Z").toNodes()
private val networkPath3 = PathParser().parsePathString("M5 16v-3a1 1 0 0 1 1-1h12a1 1 0 0 1 1 1v3").toNodes()
private val networkPath4 = PathParser().parsePathString("M12 12V8").toNodes()

private fun buildNetwork(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "network",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = networkPath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = networkPath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = networkPath2,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = networkPath3,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = networkPath4,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
