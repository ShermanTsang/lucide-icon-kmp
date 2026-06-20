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

val lockKeyholeProvider = ParameterizedIconProvider { parameters ->
    buildLockKeyhole(parameters)
}

val LockKeyhole: ImageVector
    get() = lockKeyholeProvider.create()

private val lockKeyholePath0 = PathParser().parsePathString("M 13 16 A 1 1 0 1 0 11 16 A 1 1 0 1 0 13 16 Z").toNodes()
private val lockKeyholePath1 = PathParser().parsePathString("M 5 10 H 19 A 2 2 0 0 1 21 12 V 20 A 2 2 0 0 1 19 22 H 5 A 2 2 0 0 1 3 20 V 12 A 2 2 0 0 1 5 10 Z").toNodes()
private val lockKeyholePath2 = PathParser().parsePathString("M7 10V7a5 5 0 0 1 10 0v3").toNodes()

private fun buildLockKeyhole(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "lock-keyhole",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = lockKeyholePath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = lockKeyholePath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = lockKeyholePath2,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
