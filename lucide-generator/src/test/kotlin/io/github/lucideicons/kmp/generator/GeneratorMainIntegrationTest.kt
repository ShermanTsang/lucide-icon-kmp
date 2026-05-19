package io.github.lucideicons.kmp.generator

import java.nio.file.Files
import kotlin.io.path.readText
import kotlin.io.path.writeText
import kotlin.test.Test
import kotlin.test.assertTrue

class GeneratorMainIntegrationTest {
    @Test
    fun generatesMetadataFileWithTagsAndCategories() {
        val inputDirectory = Files.createTempDirectory("lucide-generator-input")
        val outputDirectory = Files.createTempDirectory("lucide-generator-output")

        inputDirectory.resolve("airplay.svg").writeText(
            """
            <svg width="24" height="24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                <path d="M5 17H4a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h16a2 2 0 0 1 2 2v10a2 2 0 0 1-2 2h-1"/>
            </svg>
            """.trimIndent(),
        )
        inputDirectory.resolve("airplay.json").writeText(
            """
            {
              "tags": ["stream", "screen"],
              "categories": ["multimedia", "connectivity"],
              "aliases": [{"name": "air-play"}]
            }
            """.trimIndent(),
        )

        main(
            arrayOf(
                inputDirectory.toString(),
                outputDirectory.toString(),
                "io.github.generated",
            ),
        )

        val metadata = outputDirectory.resolve("LucideGeneratedMetadata.kt").readText()
        val chunk = outputDirectory.resolve("LucideGeneratedMetadataChunk0.kt").readText()
        assertTrue(metadata.contains("get() = LucideGeneratedMetadataChunk0.Airplay"))
        assertTrue(chunk.contains("setOf(\"air-play\", \"screen\", \"stream\")"))
        assertTrue(chunk.contains("setOf(LucideIconCategory.Connectivity, LucideIconCategory.Multimedia)"))
    }
}
