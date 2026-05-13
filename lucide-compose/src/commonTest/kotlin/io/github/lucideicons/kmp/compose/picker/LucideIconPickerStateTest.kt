package io.github.lucideicons.kmp.compose.picker

import io.github.lucideicons.kmp.core.model.LucideIconCategory
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
