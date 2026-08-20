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
import com.shermant.lucideiconkmp.core.registry.LucideIcons
import com.shermant.lucideiconkmp.core.registry.ParameterizedIconProvider
import com.shermant.lucideiconkmp.core.registry.create

val calendarHeartProvider = ParameterizedIconProvider { parameters ->
    buildCalendarHeart(parameters)
}

val CalendarHeart: ImageVector
    get() = calendarHeartProvider.create()

val LucideIcons.CalendarHeart: ImageVector
    get() = calendarHeartProvider.create()

private val calendarHeartPath0 = PathParser().parsePathString("M12.127 21H5a2 2 0 01-2-2V5a2 2 0 012-2h14a2 2 0 012 2v5.125").toNodes()
private val calendarHeartPath1 = PathParser().parsePathString("M14.62 17.8A2.25 2.25 0 1118 14.836a2.25 2.25 0 113.38 2.966l-2.626 2.856a.998.998 0 01-1.507 0z").toNodes()
private val calendarHeartPath2 = PathParser().parsePathString("M16 2v3").toNodes()
private val calendarHeartPath3 = PathParser().parsePathString("M3 9h18").toNodes()
private val calendarHeartPath4 = PathParser().parsePathString("M8 2v3").toNodes()

private fun buildCalendarHeart(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {
    val iconSize = (parameters.size ?: 24.0f).dp
    val strokeWidth = parameters.strokeWidth ?: 2.0f

    return ImageVector.Builder(
        name = "calendar-heart",
        defaultWidth = iconSize,
        defaultHeight = iconSize,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f,
    ).apply {
        addPath(
            pathData = calendarHeartPath0,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = calendarHeartPath1,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = calendarHeartPath2,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = calendarHeartPath3,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
        addPath(
            pathData = calendarHeartPath4,
            pathFillType = PathFillType.NonZero,
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = strokeWidth,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }.build()
}
