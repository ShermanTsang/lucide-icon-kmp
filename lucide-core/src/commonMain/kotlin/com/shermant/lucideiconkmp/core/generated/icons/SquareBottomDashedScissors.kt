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

val squareBottomDashedScissorsProvider = ParameterizedIconProvider { parameters ->
    buildSquareBottomDashedScissors(parameters)
}

val SquareBottomDashedScissors: ImageVector
    get() = squareBottomDashedScissorsProvider.create()

val LucideIcons.SquareBottomDashedScissors: ImageVector
    get() = squareBottomDashedScissorsProvider.create()

private val squareBottomDashedScissorsPath0 = PathParser().parsePathString("M14 21h1").toNodes()
private val squareBottomDashedScissorsPath1 = PathParser().parsePathString("m17 17-2.18-2.18").toNodes()
private val squareBottomDashedScissorsPath2 = PathParser().parsePathString("M5 21a2 2 0 01-2-2V5a2 2 0 012-2h14a2 2 0 012 2v14a2 2 0 01-2 2").toNodes()
private val squareBottomDashedScissorsPath3 = PathParser().parsePathString("M9 21h1").toNodes()
private val squareBottomDashedScissorsPath4 = PathParser().parsePathString("M9.56 14.44 17 7").toNodes()
private val squareBottomDashedScissorsPath5 = PathParser().parsePathString("M9.56 9.56 12 12").toNodes()
private val squareBottomDashedScissorsPath6 = PathParser().parsePathString("M 10 15.5 A 1.5 1.5 0 1 0 7 15.5 A 1.5 1.5 0 1 0 10 15.5 Z").toNodes()
private val squareBottomDashedScissorsPath7 = PathParser().parsePathString("M 10 8.5 A 1.5 1.5 0 1 0 7 8.5 A 1.5 1.5 0 1 0 10 8.5 Z").toNodes()

private fun buildSquareBottomDashedScissors(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "square-bottom-dashed-scissors",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = squareBottomDashedScissorsPath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = squareBottomDashedScissorsPath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = squareBottomDashedScissorsPath2,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = squareBottomDashedScissorsPath3,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = squareBottomDashedScissorsPath4,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = squareBottomDashedScissorsPath5,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = squareBottomDashedScissorsPath6,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = squareBottomDashedScissorsPath7,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
