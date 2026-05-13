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

val arrowRightProvider = ParameterizedIconProvider { parameters ->
    buildArrowRight(parameters)
}

private fun buildArrowRight(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24f).dp
    val strokeWidth = parameters.strokeWidth ?: 2f

    return ImageVector.Builder(
        name = "arrow-right",
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
            moveTo(5f, 12f)
            horizontalLineTo(19f)
            moveTo(12f, 5f)
            lineTo(19f, 12f)
            lineTo(12f, 19f)
        }
    }.build()
}
