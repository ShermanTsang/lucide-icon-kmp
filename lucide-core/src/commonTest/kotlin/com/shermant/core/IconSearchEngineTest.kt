package com.shermant.core

import com.shermant.core.model.LucideIconCategory
import com.shermant.core.model.LucideLocale
import com.shermant.core.registry.LucideIcons
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
    fun supportsChineseSearchWithZhLocale() {
        val registry = LucideIcons.registry

        val activityResults = registry.search("活动", locale = LucideLocale.Zh)
        val mailResults = registry.search("邮件搜索", locale = LucideLocale.Zh)

        assertTrue(activityResults.any { it.key.value == "activity" })
        assertTrue(mailResults.any { it.key.value == "mail-search" })
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
        assertEquals("活动", metadata?.displayName(LucideLocale.Zh))
        assertTrue(metadata?.tags?.contains("pulse") == true)
        assertTrue(metadata?.categories?.contains(LucideIconCategory.Multimedia) == true)
    }
}
