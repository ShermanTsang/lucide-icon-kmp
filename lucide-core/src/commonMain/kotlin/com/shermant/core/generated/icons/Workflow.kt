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

val workflowProvider = ParameterizedIconProvider { parameters ->
    buildWorkflow(parameters)
}

val Workflow: ImageVector
    get() = workflowProvider.create()

private val workflowPath0 = PathParser().parsePathString("M 5 3 H 9 A 2 2 0 0 1 11 5 V 9 A 2 2 0 0 1 9 11 H 5 A 2 2 0 0 1 3 9 V 5 A 2 2 0 0 1 5 3 Z").toNodes()
private val workflowPath1 = PathParser().parsePathString("M7 11v4a2 2 0 0 0 2 2h4").toNodes()
private val workflowPath2 = PathParser().parsePathString("M 15 13 H 19 A 2 2 0 0 1 21 15 V 19 A 2 2 0 0 1 19 21 H 15 A 2 2 0 0 1 13 19 V 15 A 2 2 0 0 1 15 13 Z").toNodes()

private fun buildWorkflow(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "workflow",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = workflowPath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = workflowPath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = workflowPath2,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
