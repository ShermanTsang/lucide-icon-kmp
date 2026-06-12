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

val boomBoxProvider = ParameterizedIconProvider { parameters ->
    buildBoomBox(parameters)
}

val BoomBox: ImageVector
    get() = boomBoxProvider.create()

private val boomBoxPath0 = PathParser().parsePathString("M4 9V5a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v4").toNodes()
private val boomBoxPath1 = PathParser().parsePathString("M8 8v1").toNodes()
private val boomBoxPath2 = PathParser().parsePathString("M12 8v1").toNodes()
private val boomBoxPath3 = PathParser().parsePathString("M16 8v1").toNodes()
private val boomBoxPath4 = PathParser().parsePathString("M 4 9 H 20 A 2 2 0 0 1 22 11 V 19 A 2 2 0 0 1 20 21 H 4 A 2 2 0 0 1 2 19 V 11 A 2 2 0 0 1 4 9 Z").toNodes()
private val boomBoxPath5 = PathParser().parsePathString("M 10 15 A 2 2 0 1 0 6 15 A 2 2 0 1 0 10 15 Z").toNodes()
private val boomBoxPath6 = PathParser().parsePathString("M 18 15 A 2 2 0 1 0 14 15 A 2 2 0 1 0 18 15 Z").toNodes()

private fun buildBoomBox(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "boom-box",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = boomBoxPath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = boomBoxPath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = boomBoxPath2,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = boomBoxPath3,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = boomBoxPath4,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = boomBoxPath5,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = boomBoxPath6,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
