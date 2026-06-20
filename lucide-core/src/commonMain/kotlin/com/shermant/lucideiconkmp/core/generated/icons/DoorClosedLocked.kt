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

val doorClosedLockedProvider = ParameterizedIconProvider { parameters ->
    buildDoorClosedLocked(parameters)
}

val DoorClosedLocked: ImageVector
    get() = doorClosedLockedProvider.create()

private val doorClosedLockedPath0 = PathParser().parsePathString("M10 12h.01").toNodes()
private val doorClosedLockedPath1 = PathParser().parsePathString("M18 9V6a2 2 0 0 0-2-2H8a2 2 0 0 0-2 2v14").toNodes()
private val doorClosedLockedPath2 = PathParser().parsePathString("M2 20h8").toNodes()
private val doorClosedLockedPath3 = PathParser().parsePathString("M20 17v-2a2 2 0 1 0-4 0v2").toNodes()
private val doorClosedLockedPath4 = PathParser().parsePathString("M 15 17 H 21 A 1 1 0 0 1 22 18 V 21 A 1 1 0 0 1 21 22 H 15 A 1 1 0 0 1 14 21 V 18 A 1 1 0 0 1 15 17 Z").toNodes()

private fun buildDoorClosedLocked(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "door-closed-locked",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = doorClosedLockedPath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = doorClosedLockedPath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = doorClosedLockedPath2,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = doorClosedLockedPath3,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = doorClosedLockedPath4,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
