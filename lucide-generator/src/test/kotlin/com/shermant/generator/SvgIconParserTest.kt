package com.shermant.generator

import com.shermant.generator.model.RawLucideIcon
import com.shermant.generator.model.RawLucideMetadata
import com.shermant.generator.parser.SvgIconParser
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SvgIconParserTest {
    @Test
    fun parsesBasicSvgPathData() {
        val parser = SvgIconParser()
        val icon = parser.parse(
            RawLucideIcon(
                name = "activity",
                svgContent = """
                    <svg width="24" height="24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                        <path d="M22 12H18L15 21L9 3L6 12H2"/>
                    </svg>
                """.trimIndent(),
                metadata = RawLucideMetadata(
                    tags = setOf("pulse"),
                    aliases = emptySet(),
                    categories = setOf("charts"),
                ),
            ),
        )

        assertEquals("activity", icon.name)
        assertEquals(24f, icon.viewportWidth)
        assertEquals(1, icon.paths.size)
        assertEquals(setOf("pulse"), icon.tags)
        assertEquals(setOf("charts"), icon.categories)
    }

    @Test
    fun convertsCircleAndRectElementsToPathData() {
        val parser = SvgIconParser()
        val icon = parser.parse(
            RawLucideIcon(
                name = "shapes",
                svgContent = """
                    <svg width="24" height="24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                        <circle cx="12" cy="12" r="4"/>
                        <rect x="2" y="2" width="20" height="20" rx="2"/>
                    </svg>
                """.trimIndent(),
                metadata = RawLucideMetadata(
                    tags = setOf("shapes"),
                    aliases = emptySet(),
                    categories = setOf("shapes"),
                ),
            ),
        )

        assertEquals(2, icon.paths.size)
        assertTrue(icon.paths.first().pathData.contains("A 4 4"))
        assertTrue(icon.paths.last().pathData.contains("A 2 2"))
    }
}
