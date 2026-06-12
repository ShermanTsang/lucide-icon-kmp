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

val alignHorizontalJustifyCenterProvider = ParameterizedIconProvider { parameters ->
    buildAlignHorizontalJustifyCenter(parameters)
}

val AlignHorizontalJustifyCenter: ImageVector
    get() = alignHorizontalJustifyCenterProvider.create()

private val alignHorizontalJustifyCenterPath0 = PathParser().parsePathString("M 4 5 H 6 A 2 2 0 0 1 8 7 V 17 A 2 2 0 0 1 6 19 H 4 A 2 2 0 0 1 2 17 V 7 A 2 2 0 0 1 4 5 Z").toNodes()
private val alignHorizontalJustifyCenterPath1 = PathParser().parsePathString("M 18 7 H 20 A 2 2 0 0 1 22 9 V 15 A 2 2 0 0 1 20 17 H 18 A 2 2 0 0 1 16 15 V 9 A 2 2 0 0 1 18 7 Z").toNodes()
private val alignHorizontalJustifyCenterPath2 = PathParser().parsePathString("M12 2v20").toNodes()

private fun buildAlignHorizontalJustifyCenter(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "align-horizontal-justify-center",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = alignHorizontalJustifyCenterPath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = alignHorizontalJustifyCenterPath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = alignHorizontalJustifyCenterPath2,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
