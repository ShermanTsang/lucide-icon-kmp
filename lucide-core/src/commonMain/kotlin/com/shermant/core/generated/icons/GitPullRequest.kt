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

val gitPullRequestProvider = ParameterizedIconProvider { parameters ->
    buildGitPullRequest(parameters)
}

val GitPullRequest: ImageVector
    get() = gitPullRequestProvider.create()

private val gitPullRequestPath0 = PathParser().parsePathString("M 21 18 A 3 3 0 1 0 15 18 A 3 3 0 1 0 21 18 Z").toNodes()
private val gitPullRequestPath1 = PathParser().parsePathString("M 9 6 A 3 3 0 1 0 3 6 A 3 3 0 1 0 9 6 Z").toNodes()
private val gitPullRequestPath2 = PathParser().parsePathString("M13 6h3a2 2 0 0 1 2 2v7").toNodes()
private val gitPullRequestPath3 = PathParser().parsePathString("M 6 9 L 6 21").toNodes()

private fun buildGitPullRequest(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "git-pull-request",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = gitPullRequestPath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = gitPullRequestPath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = gitPullRequestPath2,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = gitPullRequestPath3,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
