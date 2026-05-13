package io.github.lucideicons.kmp.core

import io.github.lucideicons.kmp.core.model.LucideIconCategory
import io.github.lucideicons.kmp.core.registry.LucideIcons
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class IconSearchEngineTest {
    @Test
    fun supportsPrefixAndTagMatching() {
        val registry = LucideIcons.registry

        val prefixResults = registry.search("arr")
        val tagResults = registry.search("screen")

        assertTrue(prefixResults.any { it.key.value == "arrow-right" })
        assertTrue(tagResults.any { it.key.value == "airplay" })
    }

    @Test
    fun filtersByCategory() {
        val registry = LucideIcons.registry

        val results = registry.byCategory(LucideIconCategory.Devices)

        assertEquals(listOf("airplay"), results.map { it.key.value })
    }
}
