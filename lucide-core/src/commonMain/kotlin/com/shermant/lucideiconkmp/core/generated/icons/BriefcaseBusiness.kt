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

val briefcaseBusinessProvider = ParameterizedIconProvider { parameters ->
    buildBriefcaseBusiness(parameters)
}

val BriefcaseBusiness: ImageVector
    get() = briefcaseBusinessProvider.create()

val LucideIcons.BriefcaseBusiness: ImageVector
    get() = briefcaseBusinessProvider.create()

private val briefcaseBusinessPath0 = PathParser().parsePathString("M12 12h.01").toNodes()
private val briefcaseBusinessPath1 = PathParser().parsePathString("M16 6V4a2 2 0 0 0-2-2h-4a2 2 0 0 0-2 2v2").toNodes()
private val briefcaseBusinessPath2 = PathParser().parsePathString("M22 13a18.15 18.15 0 0 1-20 0").toNodes()
private val briefcaseBusinessPath3 = PathParser().parsePathString("M 4 6 H 20 A 2 2 0 0 1 22 8 V 18 A 2 2 0 0 1 20 20 H 4 A 2 2 0 0 1 2 18 V 8 A 2 2 0 0 1 4 6 Z").toNodes()

private fun buildBriefcaseBusiness(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "briefcase-business",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = briefcaseBusinessPath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = briefcaseBusinessPath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = briefcaseBusinessPath2,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = briefcaseBusinessPath3,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
