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

val layoutDashboardProvider = ParameterizedIconProvider { parameters ->
    buildLayoutDashboard(parameters)
}

val LayoutDashboard: ImageVector
    get() = layoutDashboardProvider.create()

private val layoutDashboardPath0 = PathParser().parsePathString("M 4 3 H 9 A 1 1 0 0 1 10 4 V 11 A 1 1 0 0 1 9 12 H 4 A 1 1 0 0 1 3 11 V 4 A 1 1 0 0 1 4 3 Z").toNodes()
private val layoutDashboardPath1 = PathParser().parsePathString("M 15 3 H 20 A 1 1 0 0 1 21 4 V 7 A 1 1 0 0 1 20 8 H 15 A 1 1 0 0 1 14 7 V 4 A 1 1 0 0 1 15 3 Z").toNodes()
private val layoutDashboardPath2 = PathParser().parsePathString("M 15 12 H 20 A 1 1 0 0 1 21 13 V 20 A 1 1 0 0 1 20 21 H 15 A 1 1 0 0 1 14 20 V 13 A 1 1 0 0 1 15 12 Z").toNodes()
private val layoutDashboardPath3 = PathParser().parsePathString("M 4 16 H 9 A 1 1 0 0 1 10 17 V 20 A 1 1 0 0 1 9 21 H 4 A 1 1 0 0 1 3 20 V 17 A 1 1 0 0 1 4 16 Z").toNodes()

private fun buildLayoutDashboard(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "layout-dashboard",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = layoutDashboardPath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = layoutDashboardPath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = layoutDashboardPath2,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = layoutDashboardPath3,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
