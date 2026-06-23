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

val alignStartHorizontalProvider = ParameterizedIconProvider { parameters ->
    buildAlignStartHorizontal(parameters)
}

val AlignStartHorizontal: ImageVector
    get() = alignStartHorizontalProvider.create()

val LucideIcons.AlignStartHorizontal: ImageVector
    get() = alignStartHorizontalProvider.create()

private val alignStartHorizontalPath0 = PathParser().parsePathString("M 6 6 H 8 A 2 2 0 0 1 10 8 V 20 A 2 2 0 0 1 8 22 H 6 A 2 2 0 0 1 4 20 V 8 A 2 2 0 0 1 6 6 Z").toNodes()
private val alignStartHorizontalPath1 = PathParser().parsePathString("M 16 6 H 18 A 2 2 0 0 1 20 8 V 13 A 2 2 0 0 1 18 15 H 16 A 2 2 0 0 1 14 13 V 8 A 2 2 0 0 1 16 6 Z").toNodes()
private val alignStartHorizontalPath2 = PathParser().parsePathString("M22 2H2").toNodes()

private fun buildAlignStartHorizontal(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "align-start-horizontal",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = alignStartHorizontalPath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = alignStartHorizontalPath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = alignStartHorizontalPath2,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
