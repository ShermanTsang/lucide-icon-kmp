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

val pictureInPictureProvider = ParameterizedIconProvider { parameters ->
    buildPictureInPicture(parameters)
}

val PictureInPicture: ImageVector
    get() = pictureInPictureProvider.create()

private val pictureInPicturePath0 = PathParser().parsePathString("M2 10h6V4").toNodes()
private val pictureInPicturePath1 = PathParser().parsePathString("m2 4 6 6").toNodes()
private val pictureInPicturePath2 = PathParser().parsePathString("M21 10V7a2 2 0 0 0-2-2h-7").toNodes()
private val pictureInPicturePath3 = PathParser().parsePathString("M3 14v2a2 2 0 0 0 2 2h3").toNodes()
private val pictureInPicturePath4 = PathParser().parsePathString("M 13 14 H 21 A 1 1 0 0 1 22 15 V 20 A 1 1 0 0 1 21 21 H 13 A 1 1 0 0 1 12 20 V 15 A 1 1 0 0 1 13 14 Z").toNodes()

private fun buildPictureInPicture(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "picture-in-picture",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = pictureInPicturePath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = pictureInPicturePath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = pictureInPicturePath2,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = pictureInPicturePath3,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = pictureInPicturePath4,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
