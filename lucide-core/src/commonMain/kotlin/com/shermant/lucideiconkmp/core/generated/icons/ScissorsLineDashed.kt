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

val scissorsLineDashedProvider = ParameterizedIconProvider { parameters ->
    buildScissorsLineDashed(parameters)
}

val ScissorsLineDashed: ImageVector
    get() = scissorsLineDashedProvider.create()

val LucideIcons.ScissorsLineDashed: ImageVector
    get() = scissorsLineDashedProvider.create()

private val scissorsLineDashedPath0 = PathParser().parsePathString("M5.42 9.42 8 12").toNodes()
private val scissorsLineDashedPath1 = PathParser().parsePathString("M 6 8 A 2 2 0 1 0 2 8 A 2 2 0 1 0 6 8 Z").toNodes()
private val scissorsLineDashedPath2 = PathParser().parsePathString("m14 6-8.58 8.58").toNodes()
private val scissorsLineDashedPath3 = PathParser().parsePathString("M 6 16 A 2 2 0 1 0 2 16 A 2 2 0 1 0 6 16 Z").toNodes()
private val scissorsLineDashedPath4 = PathParser().parsePathString("M10.8 14.8 14 18").toNodes()
private val scissorsLineDashedPath5 = PathParser().parsePathString("M16 12h-2").toNodes()
private val scissorsLineDashedPath6 = PathParser().parsePathString("M22 12h-2").toNodes()

private fun buildScissorsLineDashed(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "scissors-line-dashed",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = scissorsLineDashedPath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = scissorsLineDashedPath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = scissorsLineDashedPath2,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = scissorsLineDashedPath3,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = scissorsLineDashedPath4,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = scissorsLineDashedPath5,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = scissorsLineDashedPath6,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
