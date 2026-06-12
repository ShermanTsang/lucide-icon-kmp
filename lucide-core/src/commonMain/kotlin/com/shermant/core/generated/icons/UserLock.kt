package com.shermant.core.generated.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.unit.dp
import com.shermant.core.registry.IconRenderParameters
import com.shermant.core.registry.ParameterizedIconProvider
import com.shermant.core.registry.create

val userLockProvider = ParameterizedIconProvider { parameters ->
    buildUserLock(parameters)
}

val UserLock: ImageVector
    get() = userLockProvider.create()

private val userLockPath0 = PathParser().parsePathString("M19 16v-2a2 2 0 0 0-4 0v2").toNodes()
private val userLockPath1 = PathParser().parsePathString("M9.5 15H7a4 4 0 0 0-4 4v2").toNodes()
private val userLockPath2 = PathParser().parsePathString("M 14 7 A 4 4 0 1 0 6 7 A 4 4 0 1 0 14 7 Z").toNodes()
private val userLockPath3 = PathParser().parsePathString("M 13.899 16 H 20.101 A 0.899 0.899 0 0 1 21 16.899 V 20.101 A 0.899 0.899 0 0 1 20.101 21 H 13.899 A 0.899 0.899 0 0 1 13 20.101 V 16.899 A 0.899 0.899 0 0 1 13.899 16 Z").toNodes()

private fun buildUserLock(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "user-lock",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = userLockPath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = userLockPath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = userLockPath2,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = userLockPath3,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
