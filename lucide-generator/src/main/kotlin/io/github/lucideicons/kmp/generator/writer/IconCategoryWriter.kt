package io.github.lucideicons.kmp.generator.writer

import io.github.lucideicons.kmp.generator.model.ParsedIcon
import java.nio.file.Path
import kotlin.io.path.createDirectories
import kotlin.io.path.writeText

class IconCategoryWriter {
    fun write(icons: List<ParsedIcon>, outputDirectory: Path, packageName: String): Path {
        outputDirectory.createDirectories()
        val target = outputDirectory.resolve("GeneratedCategories.kt")
        target.writeText(buildFile(icons, packageName))
        return target
    }

    private fun buildFile(icons: List<ParsedIcon>, packageName: String): String = buildString {
        appendLine("package $packageName")
        appendLine()
        appendLine("object GeneratedCategories {")
        appendLine("    val values: Map<String, String> = mapOf(")
        icons.forEachIndexed { index, icon ->
            val suffix = if (index == icons.lastIndex) "" else ","
            appendLine("        \"${icon.name}\" to \"general\"$suffix")
        }
        appendLine("    )")
        appendLine("}")
    }
}
