package io.github.lucideicons.kmp.generator

import io.github.lucideicons.kmp.generator.source.LucideSvgSource
import java.nio.file.Files
import kotlin.io.path.writeText
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class LucideSvgSourceTest {
    @Test
    fun loadsTagsAliasesAndCategoriesFromSidecarJson() {
        val directory = Files.createTempDirectory("lucide-source-test")
        directory.resolve("airplay.svg").writeText(
            """
            <svg width="24" height="24" viewBox="0 0 24 24"></svg>
            """.trimIndent(),
        )
        directory.resolve("airplay.json").writeText(
            """
            {
              "tags": ["stream", "cast"],
              "categories": ["multimedia", "connectivity"],
              "aliases": [{"name": "air-play"}]
            }
            """.trimIndent(),
        )

        val icon = LucideSvgSource().load(directory).single()

        assertEquals(setOf("stream", "cast"), icon.metadata.tags)
        assertEquals(setOf("multimedia", "connectivity"), icon.metadata.categories)
        assertEquals(setOf("air-play"), icon.metadata.aliases)
    }

    @Test
    fun supportsMissingAliasesSection() {
        val directory = Files.createTempDirectory("lucide-source-test-no-alias")
        directory.resolve("activity.svg").writeText(
            """
            <svg width="24" height="24" viewBox="0 0 24 24"></svg>
            """.trimIndent(),
        )
        directory.resolve("activity.json").writeText(
            """
            {
              "tags": ["pulse"],
              "categories": ["charts"]
            }
            """.trimIndent(),
        )

        val icon = LucideSvgSource().load(directory).single()

        assertTrue(icon.metadata.aliases.isEmpty())
        assertEquals(setOf("pulse"), icon.metadata.tags)
        assertEquals(setOf("charts"), icon.metadata.categories)
    }
}
