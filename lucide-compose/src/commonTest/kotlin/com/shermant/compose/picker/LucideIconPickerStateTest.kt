package com.shermant.compose.picker

import com.shermant.core.model.LucideIconCategory
import com.shermant.core.model.LucideLocale
import kotlin.test.Test
import kotlin.test.assertEquals

class LucideIconPickerStateTest {
    @Test
    fun storesQueryCategoryAndPage() {
        val state = LucideIconPickerState(
            initialQuery = "arrow",
            initialCategory = LucideIconCategory.Arrows,
            initialPage = 2,
        )

        assertEquals("arrow", state.query)
        assertEquals(LucideIconCategory.Arrows, state.selectedCategory)
        assertEquals(2, state.currentPage)
    }

    @Test
    fun updateQueryResetsPage() {
        val state = LucideIconPickerState(initialQuery = "arrow", initialPage = 3)

        state.updateQuery("bell")

        assertEquals("bell", state.query)
        assertEquals(0, state.currentPage)
    }

    @Test
    fun selectCategoryResetsPage() {
        val state = LucideIconPickerState(initialPage = 4)

        state.selectCategory(LucideIconCategory.Arrows)

        assertEquals(LucideIconCategory.Arrows, state.selectedCategory)
        assertEquals(0, state.currentPage)
    }

    @Test
    fun goToPageStoresNonNegativePage() {
        val state = LucideIconPickerState()

        state.goToPage(5)
        assertEquals(5, state.currentPage)

        state.goToPage(-1)
        assertEquals(0, state.currentPage)
    }

    @Test
    fun providesLocalizedDefaultLabels() {
        val englishStyle = LucideIconPickerDefaults.style(locale = LucideLocale.En)
        val chineseStyle = LucideIconPickerDefaults.style(locale = LucideLocale.Zh)

        assertEquals("Search icons", englishStyle.searchBar.labelText)
        assertEquals("All", englishStyle.categories.allLabelText)
        assertEquals("Previous", englishStyle.pagination.previousLabel)
        assertEquals("Next", englishStyle.pagination.nextLabel)
        assertEquals("搜索图标", chineseStyle.searchBar.labelText)
        assertEquals("全部", chineseStyle.categories.allLabelText)
        assertEquals("上一页", chineseStyle.pagination.previousLabel)
        assertEquals("下一页", chineseStyle.pagination.nextLabel)
    }
}
