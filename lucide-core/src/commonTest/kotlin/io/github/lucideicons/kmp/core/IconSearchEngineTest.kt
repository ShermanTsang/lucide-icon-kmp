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

        val results = registry.byCategory(LucideIconCategory.Connectivity)

        assertTrue(results.any { it.key.value == "airplay" })
        assertTrue(results.size > 5)
    }

    @Test
    fun exposesRichMetadataForSearchAndPicker() {
        val registry = LucideIcons.registry
        val metadata = registry.metadata("activity")

        assertEquals("Activity", metadata?.displayName)
        assertTrue(metadata?.tags?.contains("pulse") == true)
        assertTrue(metadata?.categories?.contains(LucideIconCategory.Multimedia) == true)
    }
}
