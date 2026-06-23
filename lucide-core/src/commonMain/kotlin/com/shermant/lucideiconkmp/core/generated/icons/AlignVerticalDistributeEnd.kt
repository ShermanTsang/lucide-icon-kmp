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

val alignVerticalDistributeEndProvider = ParameterizedIconProvider { parameters ->
    buildAlignVerticalDistributeEnd(parameters)
}

val AlignVerticalDistributeEnd: ImageVector
    get() = alignVerticalDistributeEndProvider.create()

val LucideIcons.AlignVerticalDistributeEnd: ImageVector
    get() = alignVerticalDistributeEndProvider.create()

private val alignVerticalDistributeEndPath0 = PathParser().parsePathString("M 7 14 H 17 A 2 2 0 0 1 19 16 V 18 A 2 2 0 0 1 17 20 H 7 A 2 2 0 0 1 5 18 V 16 A 2 2 0 0 1 7 14 Z").toNodes()
private val alignVerticalDistributeEndPath1 = PathParser().parsePathString("M 9 4 H 15 A 2 2 0 0 1 17 6 V 8 A 2 2 0 0 1 15 10 H 9 A 2 2 0 0 1 7 8 V 6 A 2 2 0 0 1 9 4 Z").toNodes()
private val alignVerticalDistributeEndPath2 = PathParser().parsePathString("M2 20h20").toNodes()
private val alignVerticalDistributeEndPath3 = PathParser().parsePathString("M2 10h20").toNodes()

private fun buildAlignVerticalDistributeEnd(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "align-vertical-distribute-end",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = alignVerticalDistributeEndPath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = alignVerticalDistributeEndPath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = alignVerticalDistributeEndPath2,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = alignVerticalDistributeEndPath3,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
