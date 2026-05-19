package com.shermant.compose.picker

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.Composable
import com.shermant.core.model.LucideIconCategory

@Stable
class LucideIconPickerState(
    initialQuery: String = "",
    initialCategory: LucideIconCategory? = null,
) {
    var query: String by mutableStateOf(initialQuery)
    var selectedCategory: LucideIconCategory? by mutableStateOf(initialCategory)
}

@Composable
fun rememberLucideIconPickerState(
    initialQuery: String = "",
    initialCategory: LucideIconCategory? = null,
): LucideIconPickerState = remember {
    LucideIconPickerState(
        initialQuery = initialQuery,
        initialCategory = initialCategory,
    )
}
