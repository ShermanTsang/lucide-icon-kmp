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

val grapeProvider = ParameterizedIconProvider { parameters ->
    buildGrape(parameters)
}

val Grape: ImageVector
    get() = grapeProvider.create()

val LucideIcons.Grape: ImageVector
    get() = grapeProvider.create()

private val grapePath0 = PathParser().parsePathString("M22 5V2l-5.89 5.89").toNodes()
private val grapePath1 = PathParser().parsePathString("M 19.6 15.89 A 3 3 0 1 0 13.6 15.89 A 3 3 0 1 0 19.6 15.89 Z").toNodes()
private val grapePath2 = PathParser().parsePathString("M 11.11 7.4 A 3 3 0 1 0 5.1099997 7.4 A 3 3 0 1 0 11.11 7.4 Z").toNodes()
private val grapePath3 = PathParser().parsePathString("M 15.35 11.65 A 3 3 0 1 0 9.35 11.65 A 3 3 0 1 0 15.35 11.65 Z").toNodes()
private val grapePath4 = PathParser().parsePathString("M 16.91 5.85 A 3 3 0 1 0 10.91 5.85 A 3 3 0 1 0 16.91 5.85 Z").toNodes()
private val grapePath5 = PathParser().parsePathString("M 21.15 10.09 A 3 3 0 1 0 15.15 10.09 A 3 3 0 1 0 21.15 10.09 Z").toNodes()
private val grapePath6 = PathParser().parsePathString("M 9.559999 13.2 A 3 3 0 1 0 3.56 13.2 A 3 3 0 1 0 9.559999 13.2 Z").toNodes()
private val grapePath7 = PathParser().parsePathString("M 13.8 17.44 A 3 3 0 1 0 7.8 17.44 A 3 3 0 1 0 13.8 17.44 Z").toNodes()
private val grapePath8 = PathParser().parsePathString("M 8 19 A 3 3 0 1 0 2 19 A 3 3 0 1 0 8 19 Z").toNodes()

private fun buildGrape(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "grape",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = grapePath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = grapePath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = grapePath2,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = grapePath3,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = grapePath4,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = grapePath5,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = grapePath6,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = grapePath7,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = grapePath8,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
