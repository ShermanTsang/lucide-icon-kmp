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
    initialPage: Int = 0,
) {
    var query: String by mutableStateOf(initialQuery)
    var selectedCategory: LucideIconCategory? by mutableStateOf(initialCategory)
    var currentPage: Int by mutableStateOf(initialPage.coerceAtLeast(0))

    fun updateQuery(query: String) {
        if (this.query == query) {
            return
        }
        this.query = query
        resetPage()
    }

    fun selectCategory(category: LucideIconCategory?) {
        if (selectedCategory == category) {
            return
        }
        selectedCategory = category
        resetPage()
    }

    fun goToPage(page: Int) {
        currentPage = page.coerceAtLeast(0)
    }

    fun resetPage() {
        currentPage = 0
    }
}

@Composable
fun rememberLucideIconPickerState(
    initialQuery: String = "",
    initialCategory: LucideIconCategory? = null,
    initialPage: Int = 0,
): LucideIconPickerState = remember {
    LucideIconPickerState(
        initialQuery = initialQuery,
        initialCategory = initialCategory,
        initialPage = initialPage,
    )
}
