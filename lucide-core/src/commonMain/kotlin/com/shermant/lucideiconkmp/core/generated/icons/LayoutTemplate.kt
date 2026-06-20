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
import com.shermant.lucideiconkmp.core.registry.ParameterizedIconProvider
import com.shermant.lucideiconkmp.core.registry.create

val layoutTemplateProvider = ParameterizedIconProvider { parameters ->
    buildLayoutTemplate(parameters)
}

val LayoutTemplate: ImageVector
    get() = layoutTemplateProvider.create()

private val layoutTemplatePath0 = PathParser().parsePathString("M 4 3 H 20 A 1 1 0 0 1 21 4 V 9 A 1 1 0 0 1 20 10 H 4 A 1 1 0 0 1 3 9 V 4 A 1 1 0 0 1 4 3 Z").toNodes()
private val layoutTemplatePath1 = PathParser().parsePathString("M 4 14 H 11 A 1 1 0 0 1 12 15 V 20 A 1 1 0 0 1 11 21 H 4 A 1 1 0 0 1 3 20 V 15 A 1 1 0 0 1 4 14 Z").toNodes()
private val layoutTemplatePath2 = PathParser().parsePathString("M 17 14 H 20 A 1 1 0 0 1 21 15 V 20 A 1 1 0 0 1 20 21 H 17 A 1 1 0 0 1 16 20 V 15 A 1 1 0 0 1 17 14 Z").toNodes()

private fun buildLayoutTemplate(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "layout-template",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = layoutTemplatePath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = layoutTemplatePath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = layoutTemplatePath2,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
