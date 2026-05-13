package io.github.lucideicons.kmp.sample.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.lucideicons.kmp.compose.LucideIcon

@Composable
fun IconGalleryScreen(selectedIconName: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        LucideIcon(
            name = selectedIconName,
            modifier = Modifier.size(32.dp),
            color = Color(0xFF2563EB),
            strokeWidth = 2.5f,
        )
        Text(text = "Previewing $selectedIconName")
    }
}
