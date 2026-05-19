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

val smartphoneNfcProvider = ParameterizedIconProvider { parameters ->
    buildSmartphoneNfc(parameters)
}

private val smartphoneNfcPath0 = PathParser().parsePathString("M 3 6 H 8 A 1 1 0 0 1 9 7 V 17 A 1 1 0 0 1 8 18 H 3 A 1 1 0 0 1 2 17 V 7 A 1 1 0 0 1 3 6 Z").toNodes()
private val smartphoneNfcPath1 = PathParser().parsePathString("M13 8.32a7.43 7.43 0 0 1 0 7.36").toNodes()
private val smartphoneNfcPath2 = PathParser().parsePathString("M16.46 6.21a11.76 11.76 0 0 1 0 11.58").toNodes()
private val smartphoneNfcPath3 = PathParser().parsePathString("M19.91 4.1a15.91 15.91 0 0 1 .01 15.8").toNodes()

private fun buildSmartphoneNfc(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "smartphone-nfc",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = smartphoneNfcPath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = smartphoneNfcPath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = smartphoneNfcPath2,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = smartphoneNfcPath3,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
