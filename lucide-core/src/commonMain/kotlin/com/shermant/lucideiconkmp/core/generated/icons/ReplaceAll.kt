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

val replaceAllProvider = ParameterizedIconProvider { parameters ->
    buildReplaceAll(parameters)
}

val ReplaceAll: ImageVector
    get() = replaceAllProvider.create()

private val replaceAllPath0 = PathParser().parsePathString("M14 14a1 1 0 0 1 1 1v5a1 1 0 0 1-1 1").toNodes()
private val replaceAllPath1 = PathParser().parsePathString("M14 4a1 1 0 0 1 1-1").toNodes()
private val replaceAllPath2 = PathParser().parsePathString("M15 10a1 1 0 0 1-1-1").toNodes()
private val replaceAllPath3 = PathParser().parsePathString("M19 14a1 1 0 0 1 1 1v5a1 1 0 0 1-1 1").toNodes()
private val replaceAllPath4 = PathParser().parsePathString("M21 4a1 1 0 0 0-1-1").toNodes()
private val replaceAllPath5 = PathParser().parsePathString("M21 9a1 1 0 0 1-1 1").toNodes()
private val replaceAllPath6 = PathParser().parsePathString("m3 7 3 3 3-3").toNodes()
private val replaceAllPath7 = PathParser().parsePathString("M6 10V5a2 2 0 0 1 2-2h2").toNodes()
private val replaceAllPath8 = PathParser().parsePathString("M 4 14 H 9 A 1 1 0 0 1 10 15 V 20 A 1 1 0 0 1 9 21 H 4 A 1 1 0 0 1 3 20 V 15 A 1 1 0 0 1 4 14 Z").toNodes()

private fun buildReplaceAll(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "replace-all",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = replaceAllPath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = replaceAllPath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = replaceAllPath2,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = replaceAllPath3,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = replaceAllPath4,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = replaceAllPath5,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = replaceAllPath6,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = replaceAllPath7,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = replaceAllPath8,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
