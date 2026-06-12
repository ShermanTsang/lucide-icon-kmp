package com.shermant.sample.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.shermant.compose.picker.LucideIconPicker
import com.shermant.compose.picker.LucideIconPickerDefaults
import com.shermant.compose.picker.rememberLucideIconPickerState

@Composable
fun IconPickerDemoScreen(onIconSelected: (String) -> Unit) {
    val state = rememberLucideIconPickerState()
    val baseStyle = LucideIconPickerDefaults.style()
    val style = baseStyle.copy(
        containerPadding = PaddingValues(20.dp),
        containerBackgroundColor = Color(0xFFF9FAFB),
        searchBar = baseStyle.searchBar.copy(
            shape = RoundedCornerShape(16.dp),
            backgroundColor = Color.Transparent,
            borderColor = Color(0xFFD1D5DB),
        ),
        categories = baseStyle.categories.copy(
            unselectedBackgroundColor = Color.Transparent,
            selectedBackgroundColor = Color.Transparent,
            selectedBorderColor = Color(0xFF2563EB),
            selectedContentColor = Color(0xFF2563EB),
        ),
        grid = baseStyle.grid.copy(
            horizontalSpacing = 16.dp,
            verticalSpacing = 16.dp,
        ),
    )
    LucideIconPicker(
        state = state,
        modifier = Modifier.height(320.dp),
        columns = 3,
        pageSize = 12,
        iconSize = 24.dp,
        iconColor = Color(0xFF111827),
        style = style,
        onIconSelected = { metadata -> onIconSelected(metadata.key.value) },
    )
}
