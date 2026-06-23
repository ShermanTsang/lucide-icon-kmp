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
import com.shermant.lucideiconkmp.core.registry.LucideIcons
import com.shermant.lucideiconkmp.core.registry.ParameterizedIconProvider
import com.shermant.lucideiconkmp.core.registry.create

val briefcaseConveyorBeltProvider = ParameterizedIconProvider { parameters ->
    buildBriefcaseConveyorBelt(parameters)
}

val BriefcaseConveyorBelt: ImageVector
    get() = briefcaseConveyorBeltProvider.create()

val LucideIcons.BriefcaseConveyorBelt: ImageVector
    get() = briefcaseConveyorBeltProvider.create()

private val briefcaseConveyorBeltPath0 = PathParser().parsePathString("M10 20v2").toNodes()
private val briefcaseConveyorBeltPath1 = PathParser().parsePathString("M14 20v2").toNodes()
private val briefcaseConveyorBeltPath2 = PathParser().parsePathString("M18 20v2").toNodes()
private val briefcaseConveyorBeltPath3 = PathParser().parsePathString("M21 20H3").toNodes()
private val briefcaseConveyorBeltPath4 = PathParser().parsePathString("M6 20v2").toNodes()
private val briefcaseConveyorBeltPath5 = PathParser().parsePathString("M8 16V4a2 2 0 0 1 2-2h4a2 2 0 0 1 2 2v12").toNodes()
private val briefcaseConveyorBeltPath6 = PathParser().parsePathString("M 6 6 H 18 A 2 2 0 0 1 20 8 V 14 A 2 2 0 0 1 18 16 H 6 A 2 2 0 0 1 4 14 V 8 A 2 2 0 0 1 6 6 Z").toNodes()

private fun buildBriefcaseConveyorBelt(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "briefcase-conveyor-belt",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = briefcaseConveyorBeltPath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = briefcaseConveyorBeltPath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = briefcaseConveyorBeltPath2,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = briefcaseConveyorBeltPath3,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = briefcaseConveyorBeltPath4,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = briefcaseConveyorBeltPath5,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = briefcaseConveyorBeltPath6,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
