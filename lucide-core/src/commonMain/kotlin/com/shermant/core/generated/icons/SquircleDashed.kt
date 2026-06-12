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

val squircleDashedProvider = ParameterizedIconProvider { parameters ->
    buildSquircleDashed(parameters)
}

val SquircleDashed: ImageVector
    get() = squircleDashedProvider.create()

private val squircleDashedPath0 = PathParser().parsePathString("M13.77 3.043a34 34 0 0 0-3.54 0").toNodes()
private val squircleDashedPath1 = PathParser().parsePathString("M13.771 20.956a33 33 0 0 1-3.541.001").toNodes()
private val squircleDashedPath2 = PathParser().parsePathString("M20.18 17.74c-.51 1.15-1.29 1.93-2.439 2.44").toNodes()
private val squircleDashedPath3 = PathParser().parsePathString("M20.18 6.259c-.51-1.148-1.291-1.929-2.44-2.438").toNodes()
private val squircleDashedPath4 = PathParser().parsePathString("M20.957 10.23a33 33 0 0 1 0 3.54").toNodes()
private val squircleDashedPath5 = PathParser().parsePathString("M3.043 10.23a34 34 0 0 0 .001 3.541").toNodes()
private val squircleDashedPath6 = PathParser().parsePathString("M6.26 20.179c-1.15-.508-1.93-1.29-2.44-2.438").toNodes()
private val squircleDashedPath7 = PathParser().parsePathString("M6.26 3.82c-1.149.51-1.93 1.291-2.44 2.44").toNodes()

private fun buildSquircleDashed(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "squircle-dashed",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = squircleDashedPath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = squircleDashedPath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = squircleDashedPath2,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = squircleDashedPath3,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = squircleDashedPath4,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = squircleDashedPath5,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = squircleDashedPath6,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = squircleDashedPath7,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
