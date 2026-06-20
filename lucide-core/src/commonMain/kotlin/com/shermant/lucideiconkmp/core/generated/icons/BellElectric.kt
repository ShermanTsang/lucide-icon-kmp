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
import com.shermant.lucideiconkmp.core.registry.ParameterizedIconProvider
import com.shermant.lucideiconkmp.core.registry.create

val bellElectricProvider = ParameterizedIconProvider { parameters ->
    buildBellElectric(parameters)
}

val BellElectric: ImageVector
    get() = bellElectricProvider.create()

private val bellElectricPath0 = PathParser().parsePathString("M18.518 17.347A7 7 0 0 1 14 19").toNodes()
private val bellElectricPath1 = PathParser().parsePathString("M18.8 4A11 11 0 0 1 20 9").toNodes()
private val bellElectricPath2 = PathParser().parsePathString("M9 9h.01").toNodes()
private val bellElectricPath3 = PathParser().parsePathString("M 22 16 A 2 2 0 1 0 18 16 A 2 2 0 1 0 22 16 Z").toNodes()
private val bellElectricPath4 = PathParser().parsePathString("M 16 9 A 7 7 0 1 0 2 9 A 7 7 0 1 0 16 9 Z").toNodes()
private val bellElectricPath5 = PathParser().parsePathString("M 6 16 H 12 A 2 2 0 0 1 14 18 V 20 A 2 2 0 0 1 12 22 H 6 A 2 2 0 0 1 4 20 V 18 A 2 2 0 0 1 6 16 Z").toNodes()

private fun buildBellElectric(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "bell-electric",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = bellElectricPath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = bellElectricPath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = bellElectricPath2,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = bellElectricPath3,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = bellElectricPath4,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = bellElectricPath5,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
