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

val alignEndHorizontalProvider = ParameterizedIconProvider { parameters ->
    buildAlignEndHorizontal(parameters)
}

val AlignEndHorizontal: ImageVector
    get() = alignEndHorizontalProvider.create()

private val alignEndHorizontalPath0 = PathParser().parsePathString("M 6 2 H 8 A 2 2 0 0 1 10 4 V 16 A 2 2 0 0 1 8 18 H 6 A 2 2 0 0 1 4 16 V 4 A 2 2 0 0 1 6 2 Z").toNodes()
private val alignEndHorizontalPath1 = PathParser().parsePathString("M 16 9 H 18 A 2 2 0 0 1 20 11 V 16 A 2 2 0 0 1 18 18 H 16 A 2 2 0 0 1 14 16 V 11 A 2 2 0 0 1 16 9 Z").toNodes()
private val alignEndHorizontalPath2 = PathParser().parsePathString("M22 22H2").toNodes()

private fun buildAlignEndHorizontal(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "align-end-horizontal",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = alignEndHorizontalPath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = alignEndHorizontalPath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = alignEndHorizontalPath2,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
