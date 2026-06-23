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

val paletteProvider = ParameterizedIconProvider { parameters ->
    buildPalette(parameters)
}

val Palette: ImageVector
    get() = paletteProvider.create()

val LucideIcons.Palette: ImageVector
    get() = paletteProvider.create()

private val palettePath0 = PathParser().parsePathString("M12 22a1 1 0 0 1 0-20 10 9 0 0 1 10 9 5 5 0 0 1-5 5h-2.25a1.75 1.75 0 0 0-1.4 2.8l.3.4a1.75 1.75 0 0 1-1.4 2.8z").toNodes()
private val palettePath1 = PathParser().parsePathString("M 14 6.5 A 0.5 0.5 0 1 0 13 6.5 A 0.5 0.5 0 1 0 14 6.5 Z").toNodes()
private val palettePath2 = PathParser().parsePathString("M 18 10.5 A 0.5 0.5 0 1 0 17 10.5 A 0.5 0.5 0 1 0 18 10.5 Z").toNodes()
private val palettePath3 = PathParser().parsePathString("M 7 12.5 A 0.5 0.5 0 1 0 6 12.5 A 0.5 0.5 0 1 0 7 12.5 Z").toNodes()
private val palettePath4 = PathParser().parsePathString("M 9 7.5 A 0.5 0.5 0 1 0 8 7.5 A 0.5 0.5 0 1 0 9 7.5 Z").toNodes()

private fun buildPalette(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "palette",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = palettePath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = palettePath1,
            pathFillType = PathFillType.NonZero,
            fill = SolidColor(Color.Black),
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = palettePath2,
            pathFillType = PathFillType.NonZero,
            fill = SolidColor(Color.Black),
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = palettePath3,
            pathFillType = PathFillType.NonZero,
            fill = SolidColor(Color.Black),
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = palettePath4,
            pathFillType = PathFillType.NonZero,
            fill = SolidColor(Color.Black),
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
