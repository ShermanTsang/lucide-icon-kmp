package com.shermant.sample.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.shermant.compose.LucideIcon
import kotlin.math.roundToInt

private const val MinStrokeWidth = 1f
private const val MaxStrokeWidth = 4f
private const val DefaultStrokeWidth = 2.5f
private const val StrokeWidthStep = 0.5f

@Composable
fun IconGalleryScreen(selectedIconName: String) {
    var strokeWidth by remember { mutableStateOf(DefaultStrokeWidth) }
    val strokeWidthLabel = ((strokeWidth * 10).roundToInt() / 10f).toString()

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            LucideIcon(
                name = selectedIconName,
                modifier = Modifier.size(32.dp),
                color = Color(0xFF2563EB),
                strokeWidth = strokeWidth,
            )
            Text(text = "Previewing $selectedIconName")
        }
        Text(text = "Stroke width: $strokeWidthLabel")
        Slider(
            value = strokeWidth,
            onValueChange = { strokeWidth = it },
            valueRange = MinStrokeWidth..MaxStrokeWidth,
            steps = (((MaxStrokeWidth - MinStrokeWidth) / StrokeWidthStep).roundToInt() - 1),
        )
    }
}
