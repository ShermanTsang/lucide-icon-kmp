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

val groupProvider = ParameterizedIconProvider { parameters ->
    buildGroup(parameters)
}

val Group: ImageVector
    get() = groupProvider.create()

private val groupPath0 = PathParser().parsePathString("M3 7V5c0-1.1.9-2 2-2h2").toNodes()
private val groupPath1 = PathParser().parsePathString("M17 3h2c1.1 0 2 .9 2 2v2").toNodes()
private val groupPath2 = PathParser().parsePathString("M21 17v2c0 1.1-.9 2-2 2h-2").toNodes()
private val groupPath3 = PathParser().parsePathString("M7 21H5c-1.1 0-2-.9-2-2v-2").toNodes()
private val groupPath4 = PathParser().parsePathString("M 8 7 H 13 A 1 1 0 0 1 14 8 V 11 A 1 1 0 0 1 13 12 H 8 A 1 1 0 0 1 7 11 V 8 A 1 1 0 0 1 8 7 Z").toNodes()
private val groupPath5 = PathParser().parsePathString("M 11 12 H 16 A 1 1 0 0 1 17 13 V 16 A 1 1 0 0 1 16 17 H 11 A 1 1 0 0 1 10 16 V 13 A 1 1 0 0 1 11 12 Z").toNodes()

private fun buildGroup(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "group",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = groupPath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = groupPath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = groupPath2,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = groupPath3,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = groupPath4,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = groupPath5,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
