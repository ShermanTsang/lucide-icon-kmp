package io.github.lucideicons.kmp.sample.screens

import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.lucideicons.kmp.compose.picker.LucideIconPicker
import io.github.lucideicons.kmp.compose.picker.rememberLucideIconPickerState

@Composable
fun IconPickerDemoScreen(onIconSelected: (String) -> Unit) {
    val state = rememberLucideIconPickerState()
    LucideIconPicker(
        state = state,
        modifier = Modifier.height(320.dp),
        columns = 3,
        iconSize = 24.dp,
        iconColor = Color(0xFF111827),
        onIconSelected = { metadata -> onIconSelected(metadata.key.value) },
    )
}
