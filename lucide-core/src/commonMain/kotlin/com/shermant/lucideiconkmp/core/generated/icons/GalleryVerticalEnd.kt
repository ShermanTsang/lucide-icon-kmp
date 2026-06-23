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

val galleryVerticalEndProvider = ParameterizedIconProvider { parameters ->
    buildGalleryVerticalEnd(parameters)
}

val GalleryVerticalEnd: ImageVector
    get() = galleryVerticalEndProvider.create()

val LucideIcons.GalleryVerticalEnd: ImageVector
    get() = galleryVerticalEndProvider.create()

private val galleryVerticalEndPath0 = PathParser().parsePathString("M7 2h10").toNodes()
private val galleryVerticalEndPath1 = PathParser().parsePathString("M5 6h14").toNodes()
private val galleryVerticalEndPath2 = PathParser().parsePathString("M 5 10 H 19 A 2 2 0 0 1 21 12 V 20 A 2 2 0 0 1 19 22 H 5 A 2 2 0 0 1 3 20 V 12 A 2 2 0 0 1 5 10 Z").toNodes()

private fun buildGalleryVerticalEnd(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "gallery-vertical-end",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = galleryVerticalEndPath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = galleryVerticalEndPath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = galleryVerticalEndPath2,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
