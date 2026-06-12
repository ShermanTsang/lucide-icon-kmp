package com.shermant.generator

import com.shermant.generator.model.PaintToken
import com.shermant.generator.model.ParsedIcon
import com.shermant.generator.model.ParsedVectorPath
import com.shermant.generator.model.StrokeCapToken
import com.shermant.generator.model.StrokeJoinToken
import com.shermant.generator.writer.IconFileWriter
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
                viewportWidth = 24f,
                viewportHeight = 24f,
                defaultStrokeWidth = 2f,
                paths = listOf(
                    ParsedVectorPath(
                        pathData = "M22 12H18L15 21L9 3L6 12H2",
                        fill = PaintToken.None,
                        stroke = PaintToken.CurrentColor,
                        strokeWidth = 2f,
                        strokeLineCap = StrokeCapToken.Round,
                        strokeLineJoin = StrokeJoinToken.Round,
                    ),
                ),
                tags = setOf("pulse"),
                aliases = emptySet(),
                categories = setOf("charts"),
            ),
            outputDirectory = directory,
            packageName = "io.github.generated",
        )

        val content = Files.readString(target)
        assertTrue(content.contains("val activityProvider"))
        assertTrue(content.contains("import com.shermant.core.registry.create"))
        assertTrue(content.contains("val Activity: ImageVector"))
        assertTrue(content.contains("get() = activityProvider.create()"))
        assertTrue(content.contains("M22 12H18L15 21L9 3L6 12H2"))
        assertTrue(content.contains("PathParser().parsePathString"))
    }
}
