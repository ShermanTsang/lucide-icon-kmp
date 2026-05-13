package io.github.lucideicons.kmp.generator.writer

import io.github.lucideicons.kmp.generator.model.ParsedIcon
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.createDirectories
import kotlin.io.path.writeText

class IconFileWriter {
    fun write(icon: ParsedIcon, outputDirectory: Path, packageName: String): Path {
        outputDirectory.createDirectories()
        val fileName = icon.displayName.replace(" ", "") + ".kt"
        val target = outputDirectory.resolve(fileName)
        target.writeText(buildFile(icon, packageName))
        return target
    }

    private fun buildFile(icon: ParsedIcon, packageName: String): String {
        return buildString {
            appendLine("package $packageName")
            appendLine()
            appendLine("// Generated from Lucide SVG: ${icon.name}")
            appendLine("object ${icon.displayName.replace(" ", "")}Icon {")
            appendLine("    const val NAME: String = \"${icon.name}\"")
            appendLine("    const val WIDTH: Float = ${icon.width}f")
            appendLine("    const val HEIGHT: Float = ${icon.height}f")
            appendLine("    val PATHS: List<String> = listOf(")
            icon.paths.forEachIndexed { index, path ->
                val suffix = if (index == icon.paths.lastIndex) "" else ","
                appendLine("        \"${path.d}\"$suffix")
            }
            appendLine("    )")
            appendLine("}")
        }
    }
}
