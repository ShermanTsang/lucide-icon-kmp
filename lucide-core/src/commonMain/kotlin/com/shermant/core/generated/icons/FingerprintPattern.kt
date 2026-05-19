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

val fingerprintPatternProvider = ParameterizedIconProvider { parameters ->
    buildFingerprintPattern(parameters)
}

private val fingerprintPatternPath0 = PathParser().parsePathString("M12 10a2 2 0 0 0-2 2c0 1.02-.1 2.51-.26 4").toNodes()
private val fingerprintPatternPath1 = PathParser().parsePathString("M14 13.12c0 2.38 0 6.38-1 8.88").toNodes()
private val fingerprintPatternPath2 = PathParser().parsePathString("M17.29 21.02c.12-.6.43-2.3.5-3.02").toNodes()
private val fingerprintPatternPath3 = PathParser().parsePathString("M2 12a10 10 0 0 1 18-6").toNodes()
private val fingerprintPatternPath4 = PathParser().parsePathString("M2 16h.01").toNodes()
private val fingerprintPatternPath5 = PathParser().parsePathString("M21.8 16c.2-2 .131-5.354 0-6").toNodes()
private val fingerprintPatternPath6 = PathParser().parsePathString("M5 19.5C5.5 18 6 15 6 12a6 6 0 0 1 .34-2").toNodes()
private val fingerprintPatternPath7 = PathParser().parsePathString("M8.65 22c.21-.66.45-1.32.57-2").toNodes()
private val fingerprintPatternPath8 = PathParser().parsePathString("M9 6.8a6 6 0 0 1 9 5.2v2").toNodes()

private fun buildFingerprintPattern(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "fingerprint-pattern",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = fingerprintPatternPath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = fingerprintPatternPath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = fingerprintPatternPath2,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = fingerprintPatternPath3,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = fingerprintPatternPath4,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = fingerprintPatternPath5,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = fingerprintPatternPath6,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = fingerprintPatternPath7,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = fingerprintPatternPath8,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
