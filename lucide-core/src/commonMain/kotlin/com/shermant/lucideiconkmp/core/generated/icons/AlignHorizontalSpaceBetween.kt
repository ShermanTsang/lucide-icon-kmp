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

val alignHorizontalSpaceBetweenProvider = ParameterizedIconProvider { parameters ->
    buildAlignHorizontalSpaceBetween(parameters)
}

val AlignHorizontalSpaceBetween: ImageVector
    get() = alignHorizontalSpaceBetweenProvider.create()

private val alignHorizontalSpaceBetweenPath0 = PathParser().parsePathString("M 5 5 H 7 A 2 2 0 0 1 9 7 V 17 A 2 2 0 0 1 7 19 H 5 A 2 2 0 0 1 3 17 V 7 A 2 2 0 0 1 5 5 Z").toNodes()
private val alignHorizontalSpaceBetweenPath1 = PathParser().parsePathString("M 17 7 H 19 A 2 2 0 0 1 21 9 V 15 A 2 2 0 0 1 19 17 H 17 A 2 2 0 0 1 15 15 V 9 A 2 2 0 0 1 17 7 Z").toNodes()
private val alignHorizontalSpaceBetweenPath2 = PathParser().parsePathString("M3 2v20").toNodes()
private val alignHorizontalSpaceBetweenPath3 = PathParser().parsePathString("M21 2v20").toNodes()

private fun buildAlignHorizontalSpaceBetween(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "align-horizontal-space-between",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = alignHorizontalSpaceBetweenPath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = alignHorizontalSpaceBetweenPath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = alignHorizontalSpaceBetweenPath2,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = alignHorizontalSpaceBetweenPath3,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
