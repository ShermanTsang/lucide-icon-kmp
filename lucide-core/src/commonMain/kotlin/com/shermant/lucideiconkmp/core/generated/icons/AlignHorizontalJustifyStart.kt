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

val alignHorizontalJustifyStartProvider = ParameterizedIconProvider { parameters ->
    buildAlignHorizontalJustifyStart(parameters)
}

val AlignHorizontalJustifyStart: ImageVector
    get() = alignHorizontalJustifyStartProvider.create()

val LucideIcons.AlignHorizontalJustifyStart: ImageVector
    get() = alignHorizontalJustifyStartProvider.create()

private val alignHorizontalJustifyStartPath0 = PathParser().parsePathString("M 8 5 H 10 A 2 2 0 0 1 12 7 V 17 A 2 2 0 0 1 10 19 H 8 A 2 2 0 0 1 6 17 V 7 A 2 2 0 0 1 8 5 Z").toNodes()
private val alignHorizontalJustifyStartPath1 = PathParser().parsePathString("M 18 7 H 20 A 2 2 0 0 1 22 9 V 15 A 2 2 0 0 1 20 17 H 18 A 2 2 0 0 1 16 15 V 9 A 2 2 0 0 1 18 7 Z").toNodes()
private val alignHorizontalJustifyStartPath2 = PathParser().parsePathString("M2 2v20").toNodes()

private fun buildAlignHorizontalJustifyStart(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "align-horizontal-justify-start",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = alignHorizontalJustifyStartPath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = alignHorizontalJustifyStartPath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = alignHorizontalJustifyStartPath2,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
