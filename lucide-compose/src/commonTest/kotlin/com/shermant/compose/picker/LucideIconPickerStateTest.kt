package com.shermant.compose.picker

import com.shermant.core.model.LucideIconCategory
import com.shermant.core.model.LucideLocale
import kotlin.test.Test
import kotlin.test.assertEquals

class LucideIconPickerStateTest {
    @Test
    fun storesQueryAndCategory() {
        val state = LucideIconPickerState(
            initialQuery = "arrow",
            initialCategory = LucideIconCategory.Arrows,
        )

        assertEquals("arrow", state.query)
        assertEquals(LucideIconCategory.Arrows, state.selectedCategory)
    }

    @Test
    fun providesLocalizedDefaultLabels() {
        val englishStyle = LucideIconPickerDefaults.style(locale = LucideLocale.En)
        val chineseStyle = LucideIconPickerDefaults.style(locale = LucideLocale.Zh)

        assertEquals("Search icons", englishStyle.searchBar.labelText)
        assertEquals("All", englishStyle.categories.allLabelText)
        assertEquals("搜索图标", chineseStyle.searchBar.labelText)
        assertEquals("全部", chineseStyle.categories.allLabelText)
    }
}
