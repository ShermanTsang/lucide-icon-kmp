package io.github.lucideicons.kmp.core.generated.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import io.github.lucideicons.kmp.core.registry.IconRenderParameters
import io.github.lucideicons.kmp.core.registry.ParameterizedIconProvider

val airplayProvider = ParameterizedIconProvider { parameters ->
    buildAirplay(parameters)
}

private fun buildAirplay(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24f).dp
    val strokeWidth = parameters.strokeWidth ?: 2f

    return ImageVector.Builder(
        name = "airplay",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24f,
        viewportHeight = 24f,
    ).apply {
        path(
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(5f, 17f)
            horizontalLineTo(4f)
            curveTo(2.9f, 17f, 2f, 16.1f, 2f, 15f)
            verticalLineTo(5f)
            curveTo(2f, 3.9f, 2.9f, 3f, 4f, 3f)
            horizontalLineTo(20f)
            curveTo(21.1f, 3f, 22f, 3.9f, 22f, 5f)
            verticalLineTo(15f)
            curveTo(22f, 16.1f, 21.1f, 17f, 20f, 17f)
            horizontalLineTo(19f)
            moveTo(12f, 15f)
            lineTo(17f, 21f)
            horizontalLineTo(7f)
            close()
        }
    }.build()
}
