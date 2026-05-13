package io.github.lucideicons.kmp.generator

import io.github.lucideicons.kmp.generator.parser.SvgIconParser
import kotlin.test.Test
import kotlin.test.assertEquals

class SvgIconParserTest {
    @Test
    fun parsesBasicSvgPathData() {
        val parser = SvgIconParser()
        val icon = parser.parse(
            name = "activity",
            svgContent = """
                <svg width="24" height="24">
                    <path d="M22 12H18L15 21L9 3L6 12H2"/>
                </svg>
            """.trimIndent(),
        )

        assertEquals("activity", icon.name)
        assertEquals(24f, icon.width)
        assertEquals(1, icon.paths.size)
    }
}
