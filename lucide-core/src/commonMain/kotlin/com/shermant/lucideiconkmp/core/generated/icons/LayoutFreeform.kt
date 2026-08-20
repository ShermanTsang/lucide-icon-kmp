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

val layoutFreeformProvider = ParameterizedIconProvider { parameters ->
    buildLayoutFreeform(parameters)
}

val LayoutFreeform: ImageVector
    get() = layoutFreeformProvider.create()

val LucideIcons.LayoutFreeform: ImageVector
    get() = layoutFreeformProvider.create()

private val layoutFreeformPath0 = PathParser().parsePathString("M 4 3 H 9 A 1 1 0 0 1 10 4 V 9 A 1 1 0 0 1 9 10 H 4 A 1 1 0 0 1 3 9 V 4 A 1 1 0 0 1 4 3 Z").toNodes()
private val layoutFreeformPath1 = PathParser().parsePathString("M 15 4 H 20 A 1 1 0 0 1 21 5 V 10 A 1 1 0 0 1 20 11 H 15 A 1 1 0 0 1 14 10 V 5 A 1 1 0 0 1 15 4 Z").toNodes()
private val layoutFreeformPath2 = PathParser().parsePathString("M 5 14 H 10 A 1 1 0 0 1 11 15 V 20 A 1 1 0 0 1 10 21 H 5 A 1 1 0 0 1 4 20 V 15 A 1 1 0 0 1 5 14 Z").toNodes()

private fun buildLayoutFreeform(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "layout-freeform",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = layoutFreeformPath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = layoutFreeformPath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = layoutFreeformPath2,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
