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

val gitPullRequestCreateArrowProvider = ParameterizedIconProvider { parameters ->
    buildGitPullRequestCreateArrow(parameters)
}

val GitPullRequestCreateArrow: ImageVector
    get() = gitPullRequestCreateArrowProvider.create()

private val gitPullRequestCreateArrowPath0 = PathParser().parsePathString("M 8 6 A 3 3 0 1 0 2 6 A 3 3 0 1 0 8 6 Z").toNodes()
private val gitPullRequestCreateArrowPath1 = PathParser().parsePathString("M5 9v12").toNodes()
private val gitPullRequestCreateArrowPath2 = PathParser().parsePathString("m15 9-3-3 3-3").toNodes()
private val gitPullRequestCreateArrowPath3 = PathParser().parsePathString("M12 6h5a2 2 0 0 1 2 2v3").toNodes()
private val gitPullRequestCreateArrowPath4 = PathParser().parsePathString("M19 15v6").toNodes()
private val gitPullRequestCreateArrowPath5 = PathParser().parsePathString("M22 18h-6").toNodes()

private fun buildGitPullRequestCreateArrow(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "git-pull-request-create-arrow",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = gitPullRequestCreateArrowPath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = gitPullRequestCreateArrowPath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = gitPullRequestCreateArrowPath2,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = gitPullRequestCreateArrowPath3,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = gitPullRequestCreateArrowPath4,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = gitPullRequestCreateArrowPath5,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
