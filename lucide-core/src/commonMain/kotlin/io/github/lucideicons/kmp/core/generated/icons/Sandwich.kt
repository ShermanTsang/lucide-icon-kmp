package io.github.lucideicons.kmp.core.generated.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.unit.dp
import io.github.lucideicons.kmp.core.registry.IconRenderParameters
import io.github.lucideicons.kmp.core.registry.ParameterizedIconProvider

val sandwichProvider = ParameterizedIconProvider { parameters ->
    buildSandwich(parameters)
}

private val sandwichPath0 = PathParser().parsePathString("m2.37 11.223 8.372-6.777a2 2 0 0 1 2.516 0l8.371 6.777").toNodes()
private val sandwichPath1 = PathParser().parsePathString("M21 15a1 1 0 0 1 1 1v2a1 1 0 0 1-1 1h-5.25").toNodes()
private val sandwichPath2 = PathParser().parsePathString("M3 15a1 1 0 0 0-1 1v2a1 1 0 0 0 1 1h9").toNodes()
private val sandwichPath3 = PathParser().parsePathString("m6.67 15 6.13 4.6a2 2 0 0 0 2.8-.4l3.15-4.2").toNodes()
private val sandwichPath4 = PathParser().parsePathString("M 3 11 H 21 A 1 1 0 0 1 22 12 V 14 A 1 1 0 0 1 21 15 H 3 A 1 1 0 0 1 2 14 V 12 A 1 1 0 0 1 3 11 Z").toNodes()

private fun buildSandwich(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "sandwich",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = sandwichPath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = sandwichPath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = sandwichPath2,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = sandwichPath3,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = sandwichPath4,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
