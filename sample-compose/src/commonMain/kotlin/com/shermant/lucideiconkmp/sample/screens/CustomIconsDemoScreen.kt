package com.shermant.lucideiconkmp.sample.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.shermant.lucideiconkmp.compose.LucideIcon
import com.shermant.lucideiconkmp.core.model.LucideIconCategory
import com.shermant.lucideiconkmp.core.registry.LucideIcons
import com.shermant.lucideiconkmp.core.registry.registerCustomIcon

@Composable
fun CustomIconsDemoScreen() {
    LaunchedEffect(Unit) {
        if (!LucideIcons.registry.contains("brand-logo")) {
            LucideIcons.registry.registerCustomIcon(
                name = "brand-logo",
                categories = setOf(LucideIconCategory.Custom),
                tags = setOf("brand", "demo"),
                imageVector = buildBrandLogo(),
            )
        }
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        LucideIcon(name = "brand-logo", size = 24.dp, color = Color(0xFFDC2626))
        Text(text = "Custom icon registration")
    }
}

private fun buildBrandLogo(): ImageVector = ImageVector.Builder(
    name = "brand-logo",
    defaultWidth = 24.dp,
    defaultHeight = 24.dp,
    viewportWidth = 24f,
    viewportHeight = 24f,
).apply {
    path(
        fill = null,
        stroke = SolidColor(Color.Black),
        strokeLineWidth = 2f,
        strokeLineCap = StrokeCap.Round,
        strokeLineJoin = StrokeJoin.Round,
        pathFillType = PathFillType.NonZero,
    ) {
        moveTo(4f, 12f)
        lineTo(12f, 4f)
        lineTo(20f, 12f)
        lineTo(12f, 20f)
        close()
    }
}.build()
