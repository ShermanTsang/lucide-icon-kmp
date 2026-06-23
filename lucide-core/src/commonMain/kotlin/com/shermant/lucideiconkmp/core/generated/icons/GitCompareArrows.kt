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

val gitCompareArrowsProvider = ParameterizedIconProvider { parameters ->
    buildGitCompareArrows(parameters)
}

val GitCompareArrows: ImageVector
    get() = gitCompareArrowsProvider.create()

val LucideIcons.GitCompareArrows: ImageVector
    get() = gitCompareArrowsProvider.create()

private val gitCompareArrowsPath0 = PathParser().parsePathString("M 8 6 A 3 3 0 1 0 2 6 A 3 3 0 1 0 8 6 Z").toNodes()
private val gitCompareArrowsPath1 = PathParser().parsePathString("M12 6h5a2 2 0 0 1 2 2v7").toNodes()
private val gitCompareArrowsPath2 = PathParser().parsePathString("m15 9-3-3 3-3").toNodes()
private val gitCompareArrowsPath3 = PathParser().parsePathString("M 22 18 A 3 3 0 1 0 16 18 A 3 3 0 1 0 22 18 Z").toNodes()
private val gitCompareArrowsPath4 = PathParser().parsePathString("M12 18H7a2 2 0 0 1-2-2V9").toNodes()
private val gitCompareArrowsPath5 = PathParser().parsePathString("m9 15 3 3-3 3").toNodes()

private fun buildGitCompareArrows(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "git-compare-arrows",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = gitCompareArrowsPath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = gitCompareArrowsPath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = gitCompareArrowsPath2,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = gitCompareArrowsPath3,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = gitCompareArrowsPath4,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = gitCompareArrowsPath5,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
