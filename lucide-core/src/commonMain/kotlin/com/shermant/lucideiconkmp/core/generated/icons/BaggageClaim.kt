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

val baggageClaimProvider = ParameterizedIconProvider { parameters ->
    buildBaggageClaim(parameters)
}

val BaggageClaim: ImageVector
    get() = baggageClaimProvider.create()

private val baggageClaimPath0 = PathParser().parsePathString("M22 18H6a2 2 0 0 1-2-2V7a2 2 0 0 0-2-2").toNodes()
private val baggageClaimPath1 = PathParser().parsePathString("M17 14V4a2 2 0 0 0-2-2h-1a2 2 0 0 0-2 2v10").toNodes()
private val baggageClaimPath2 = PathParser().parsePathString("M 9 6 H 20 A 1 1 0 0 1 21 7 V 13 A 1 1 0 0 1 20 14 H 9 A 1 1 0 0 1 8 13 V 7 A 1 1 0 0 1 9 6 Z").toNodes()
private val baggageClaimPath3 = PathParser().parsePathString("M 20 20 A 2 2 0 1 0 16 20 A 2 2 0 1 0 20 20 Z").toNodes()
private val baggageClaimPath4 = PathParser().parsePathString("M 11 20 A 2 2 0 1 0 7 20 A 2 2 0 1 0 11 20 Z").toNodes()

private fun buildBaggageClaim(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "baggage-claim",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = baggageClaimPath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = baggageClaimPath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = baggageClaimPath2,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = baggageClaimPath3,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = baggageClaimPath4,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
