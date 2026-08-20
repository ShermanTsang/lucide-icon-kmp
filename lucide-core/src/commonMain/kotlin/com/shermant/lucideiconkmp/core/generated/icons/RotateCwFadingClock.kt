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

val rotateCwFadingClockProvider = ParameterizedIconProvider { parameters ->
    buildRotateCwFadingClock(parameters)
}

val RotateCwFadingClock: ImageVector
    get() = rotateCwFadingClockProvider.create()

val LucideIcons.RotateCwFadingClock: ImageVector
    get() = rotateCwFadingClockProvider.create()

private val rotateCwFadingClockPath0 = PathParser().parsePathString("M12 3a9.75 9.75 0 0 1 6.74 2.74").toNodes()
private val rotateCwFadingClockPath1 = PathParser().parsePathString("M18.74 5.74 21 8").toNodes()
private val rotateCwFadingClockPath2 = PathParser().parsePathString("M21 8V3").toNodes()
private val rotateCwFadingClockPath3 = PathParser().parsePathString("M7.5 19.794c-6-3.464-6-12.124 0-15.588").toNodes()
private val rotateCwFadingClockPath4 = PathParser().parsePathString("M7.5 4.206A9 9 0 0 1 12 3").toNodes()
private val rotateCwFadingClockPath5 = PathParser().parsePathString("M12 7v5l4 2").toNodes()
private val rotateCwFadingClockPath6 = PathParser().parsePathString("M14 20.775A9 9 0 0 1 12 21").toNodes()
private val rotateCwFadingClockPath7 = PathParser().parsePathString("M19 17.656a9 9 0 0 1-1.5 1.456").toNodes()
private val rotateCwFadingClockPath8 = PathParser().parsePathString("M21 12a9 9 0 0 1-.228 2").toNodes()
private val rotateCwFadingClockPath9 = PathParser().parsePathString("M21 8h-5").toNodes()

private fun buildRotateCwFadingClock(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "rotate-cw-fading-clock",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = rotateCwFadingClockPath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = rotateCwFadingClockPath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = rotateCwFadingClockPath2,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = rotateCwFadingClockPath3,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = rotateCwFadingClockPath4,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = rotateCwFadingClockPath5,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = rotateCwFadingClockPath6,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = rotateCwFadingClockPath7,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = rotateCwFadingClockPath8,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = rotateCwFadingClockPath9,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
