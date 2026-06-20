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

val ungroupProvider = ParameterizedIconProvider { parameters ->
    buildUngroup(parameters)
}

val Ungroup: ImageVector
    get() = ungroupProvider.create()

private val ungroupPath0 = PathParser().parsePathString("M 6 4 H 12 A 1 1 0 0 1 13 5 V 9 A 1 1 0 0 1 12 10 H 6 A 1 1 0 0 1 5 9 V 5 A 1 1 0 0 1 6 4 Z").toNodes()
private val ungroupPath1 = PathParser().parsePathString("M 12 14 H 18 A 1 1 0 0 1 19 15 V 19 A 1 1 0 0 1 18 20 H 12 A 1 1 0 0 1 11 19 V 15 A 1 1 0 0 1 12 14 Z").toNodes()

private fun buildUngroup(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "ungroup",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = ungroupPath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = ungroupPath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
