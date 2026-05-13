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

val activityProvider = ParameterizedIconProvider { parameters ->
    buildActivity(parameters)
}

private fun buildActivity(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24f).dp
    val strokeWidth = parameters.strokeWidth ?: 2f

    return ImageVector.Builder(
        name = "activity",
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
            moveTo(22f, 12f)
            horizontalLineTo(18f)
            lineTo(15f, 21f)
            lineTo(9f, 3f)
            lineTo(6f, 12f)
            horizontalLineTo(2f)
        }
    }.build()
}
