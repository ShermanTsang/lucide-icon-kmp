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

val alignEndVerticalProvider = ParameterizedIconProvider { parameters ->
    buildAlignEndVertical(parameters)
}

val AlignEndVertical: ImageVector
    get() = alignEndVerticalProvider.create()

val LucideIcons.AlignEndVertical: ImageVector
    get() = alignEndVerticalProvider.create()

private val alignEndVerticalPath0 = PathParser().parsePathString("M 4 4 H 16 A 2 2 0 0 1 18 6 V 8 A 2 2 0 0 1 16 10 H 4 A 2 2 0 0 1 2 8 V 6 A 2 2 0 0 1 4 4 Z").toNodes()
private val alignEndVerticalPath1 = PathParser().parsePathString("M 11 14 H 16 A 2 2 0 0 1 18 16 V 18 A 2 2 0 0 1 16 20 H 11 A 2 2 0 0 1 9 18 V 16 A 2 2 0 0 1 11 14 Z").toNodes()
private val alignEndVerticalPath2 = PathParser().parsePathString("M22 22V2").toNodes()

private fun buildAlignEndVertical(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "align-end-vertical",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = alignEndVerticalPath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = alignEndVerticalPath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = alignEndVerticalPath2,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
