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

val gitPullRequestArrowProvider = ParameterizedIconProvider { parameters ->
    buildGitPullRequestArrow(parameters)
}

val GitPullRequestArrow: ImageVector
    get() = gitPullRequestArrowProvider.create()

private val gitPullRequestArrowPath0 = PathParser().parsePathString("M 8 6 A 3 3 0 1 0 2 6 A 3 3 0 1 0 8 6 Z").toNodes()
private val gitPullRequestArrowPath1 = PathParser().parsePathString("M5 9v12").toNodes()
private val gitPullRequestArrowPath2 = PathParser().parsePathString("M 22 18 A 3 3 0 1 0 16 18 A 3 3 0 1 0 22 18 Z").toNodes()
private val gitPullRequestArrowPath3 = PathParser().parsePathString("m15 9-3-3 3-3").toNodes()
private val gitPullRequestArrowPath4 = PathParser().parsePathString("M12 6h5a2 2 0 0 1 2 2v7").toNodes()

private fun buildGitPullRequestArrow(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "git-pull-request-arrow",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = gitPullRequestArrowPath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = gitPullRequestArrowPath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = gitPullRequestArrowPath2,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = gitPullRequestArrowPath3,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = gitPullRequestArrowPath4,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
