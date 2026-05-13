package io.github.lucideicons.kmp.generator

import io.github.lucideicons.kmp.generator.model.ParsedIcon
import io.github.lucideicons.kmp.generator.model.ParsedSvgPath
import io.github.lucideicons.kmp.generator.writer.IconFileWriter
import java.nio.file.Files
import kotlin.test.Test
import kotlin.test.assertTrue

class IconFileWriterTest {
    @Test
    fun writesDeterministicKotlinOutput() {
        val directory = Files.createTempDirectory("lucide-generator-test")
        val writer = IconFileWriter()

        val target = writer.write(
            icon = ParsedIcon(
                name = "activity",
                displayName = "Activity",
                width = 24f,
                height = 24f,
                paths = listOf(ParsedSvgPath("M22 12H18L15 21L9 3L6 12H2")),
            ),
            outputDirectory = directory,
            packageName = "io.github.generated",
        )

        val content = Files.readString(target)
        assertTrue(content.contains("object ActivityIcon"))
        assertTrue(content.contains("M22 12H18L15 21L9 3L6 12H2"))
    }
}
