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

val stretchHorizontalProvider = ParameterizedIconProvider { parameters ->
    buildStretchHorizontal(parameters)
}

val StretchHorizontal: ImageVector
    get() = stretchHorizontalProvider.create()

private val stretchHorizontalPath0 = PathParser().parsePathString("M 4 4 H 20 A 2 2 0 0 1 22 6 V 8 A 2 2 0 0 1 20 10 H 4 A 2 2 0 0 1 2 8 V 6 A 2 2 0 0 1 4 4 Z").toNodes()
private val stretchHorizontalPath1 = PathParser().parsePathString("M 4 14 H 20 A 2 2 0 0 1 22 16 V 18 A 2 2 0 0 1 20 20 H 4 A 2 2 0 0 1 2 18 V 16 A 2 2 0 0 1 4 14 Z").toNodes()

private fun buildStretchHorizontal(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "stretch-horizontal",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = stretchHorizontalPath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = stretchHorizontalPath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
