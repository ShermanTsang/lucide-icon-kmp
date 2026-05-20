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

    @Test
    fun keepsSearchResultsStableForRepeatedQueriesAndBlankQueries() {
        val registry = LucideIcons.registry

        val firstEnglishResults = registry.search("mail")
        val secondEnglishResults = registry.search("mail")
        val firstChineseResults = registry.search("邮件", locale = LucideLocale.Zh)
        val secondChineseResults = registry.search("邮件", locale = LucideLocale.Zh)
        val blankEnglishResults = registry.search("", locale = LucideLocale.En, limit = 20)
        val blankChineseResults = registry.search("", locale = LucideLocale.Zh, limit = 20)

        assertEquals(firstEnglishResults.map { it.key }, secondEnglishResults.map { it.key })
        assertEquals(firstChineseResults.map { it.key }, secondChineseResults.map { it.key })
        assertEquals(20, blankEnglishResults.size)
        assertEquals(20, blankChineseResults.size)
        assertEquals(
            blankEnglishResults.map { it.displayName(LucideLocale.En) }.sorted(),
            blankEnglishResults.map { it.displayName(LucideLocale.En) },
        )
        assertEquals(
            blankChineseResults.map { it.displayName(LucideLocale.Zh) }.sorted(),
            blankChineseResults.map { it.displayName(LucideLocale.Zh) },
        )
    }
}
