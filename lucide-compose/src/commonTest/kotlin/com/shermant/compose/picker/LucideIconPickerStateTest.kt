package com.shermant.compose.picker

import com.shermant.core.model.LucideIconCategory
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
}
