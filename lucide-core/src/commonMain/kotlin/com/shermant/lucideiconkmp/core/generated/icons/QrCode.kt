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

val qrCodeProvider = ParameterizedIconProvider { parameters ->
    buildQrCode(parameters)
}

val QrCode: ImageVector
    get() = qrCodeProvider.create()

private val qrCodePath0 = PathParser().parsePathString("M 4 3 H 7 A 1 1 0 0 1 8 4 V 7 A 1 1 0 0 1 7 8 H 4 A 1 1 0 0 1 3 7 V 4 A 1 1 0 0 1 4 3 Z").toNodes()
private val qrCodePath1 = PathParser().parsePathString("M 17 3 H 20 A 1 1 0 0 1 21 4 V 7 A 1 1 0 0 1 20 8 H 17 A 1 1 0 0 1 16 7 V 4 A 1 1 0 0 1 17 3 Z").toNodes()
private val qrCodePath2 = PathParser().parsePathString("M 4 16 H 7 A 1 1 0 0 1 8 17 V 20 A 1 1 0 0 1 7 21 H 4 A 1 1 0 0 1 3 20 V 17 A 1 1 0 0 1 4 16 Z").toNodes()
private val qrCodePath3 = PathParser().parsePathString("M21 16h-3a2 2 0 0 0-2 2v3").toNodes()
private val qrCodePath4 = PathParser().parsePathString("M21 21v.01").toNodes()
private val qrCodePath5 = PathParser().parsePathString("M12 7v3a2 2 0 0 1-2 2H7").toNodes()
private val qrCodePath6 = PathParser().parsePathString("M3 12h.01").toNodes()
private val qrCodePath7 = PathParser().parsePathString("M12 3h.01").toNodes()
private val qrCodePath8 = PathParser().parsePathString("M12 16v.01").toNodes()
private val qrCodePath9 = PathParser().parsePathString("M16 12h1").toNodes()
private val qrCodePath10 = PathParser().parsePathString("M21 12v.01").toNodes()
private val qrCodePath11 = PathParser().parsePathString("M12 21v-1").toNodes()

private fun buildQrCode(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "qr-code",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = qrCodePath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = qrCodePath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = qrCodePath2,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = qrCodePath3,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = qrCodePath4,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = qrCodePath5,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = qrCodePath6,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = qrCodePath7,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = qrCodePath8,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = qrCodePath9,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = qrCodePath10,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = qrCodePath11,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
