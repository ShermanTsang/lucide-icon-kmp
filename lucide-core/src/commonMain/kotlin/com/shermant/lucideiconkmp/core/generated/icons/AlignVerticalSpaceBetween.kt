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

val alignVerticalSpaceBetweenProvider = ParameterizedIconProvider { parameters ->
    buildAlignVerticalSpaceBetween(parameters)
}

val AlignVerticalSpaceBetween: ImageVector
    get() = alignVerticalSpaceBetweenProvider.create()

val LucideIcons.AlignVerticalSpaceBetween: ImageVector
    get() = alignVerticalSpaceBetweenProvider.create()

private val alignVerticalSpaceBetweenPath0 = PathParser().parsePathString("M 7 15 H 17 A 2 2 0 0 1 19 17 V 19 A 2 2 0 0 1 17 21 H 7 A 2 2 0 0 1 5 19 V 17 A 2 2 0 0 1 7 15 Z").toNodes()
private val alignVerticalSpaceBetweenPath1 = PathParser().parsePathString("M 9 3 H 15 A 2 2 0 0 1 17 5 V 7 A 2 2 0 0 1 15 9 H 9 A 2 2 0 0 1 7 7 V 5 A 2 2 0 0 1 9 3 Z").toNodes()
private val alignVerticalSpaceBetweenPath2 = PathParser().parsePathString("M2 21h20").toNodes()
private val alignVerticalSpaceBetweenPath3 = PathParser().parsePathString("M2 3h20").toNodes()

private fun buildAlignVerticalSpaceBetween(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "align-vertical-space-between",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = alignVerticalSpaceBetweenPath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = alignVerticalSpaceBetweenPath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = alignVerticalSpaceBetweenPath2,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = alignVerticalSpaceBetweenPath3,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
