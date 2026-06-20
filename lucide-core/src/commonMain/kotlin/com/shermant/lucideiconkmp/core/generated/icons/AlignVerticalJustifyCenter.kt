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

val alignVerticalJustifyCenterProvider = ParameterizedIconProvider { parameters ->
    buildAlignVerticalJustifyCenter(parameters)
}

val AlignVerticalJustifyCenter: ImageVector
    get() = alignVerticalJustifyCenterProvider.create()

private val alignVerticalJustifyCenterPath0 = PathParser().parsePathString("M 7 16 H 17 A 2 2 0 0 1 19 18 V 20 A 2 2 0 0 1 17 22 H 7 A 2 2 0 0 1 5 20 V 18 A 2 2 0 0 1 7 16 Z").toNodes()
private val alignVerticalJustifyCenterPath1 = PathParser().parsePathString("M 9 2 H 15 A 2 2 0 0 1 17 4 V 6 A 2 2 0 0 1 15 8 H 9 A 2 2 0 0 1 7 6 V 4 A 2 2 0 0 1 9 2 Z").toNodes()
private val alignVerticalJustifyCenterPath2 = PathParser().parsePathString("M2 12h20").toNodes()

private fun buildAlignVerticalJustifyCenter(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "align-vertical-justify-center",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = alignVerticalJustifyCenterPath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = alignVerticalJustifyCenterPath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = alignVerticalJustifyCenterPath2,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
