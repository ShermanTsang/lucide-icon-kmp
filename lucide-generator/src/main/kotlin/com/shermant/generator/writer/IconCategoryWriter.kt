package com.shermant.generator.writer

import com.shermant.generator.toPascalCaseIdentifier
import com.shermant.generator.model.ParsedIcon
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.createDirectories
import kotlin.io.path.writeText

class IconCategoryWriter {
    fun write(icons: List<ParsedIcon>, outputDirectory: Path, packageName: String): Path {
        outputDirectory.createDirectories()
        deleteOldChunks(outputDirectory)
        val chunks = icons.chunked(CHUNK_SIZE)
        chunks.forEachIndexed { index, chunk ->
            outputDirectory.resolve("LucideGeneratedMetadataChunk$index.kt")
                .writeText(buildChunkFile(chunk, packageName, index))
        }
        val target = outputDirectory.resolve("LucideGeneratedMetadata.kt")
        target.writeText(buildMainFile(icons, chunks.size, packageName))
        return target
    }

    private fun buildMainFile(icons: List<ParsedIcon>, chunkCount: Int, packageName: String): String = buildString {
        appendLine("package $packageName")
        appendLine()
        appendLine("import com.shermant.core.model.LucideIconMetadata")
        appendLine()
        appendLine("object LucideGeneratedMetadata {")
        icons.forEach { icon ->
            val identifier = icon.name.toPascalCaseIdentifier()
            appendLine("    val $identifier: LucideIconMetadata")
            appendLine("        get() = LucideGeneratedMetadataChunk${icons.indexOf(icon) / CHUNK_SIZE}.$identifier")
        }
        appendLine()
        appendLine("    val all: List<LucideIconMetadata> by lazy {")
        appendLine("        buildList {")
        repeat(chunkCount) { index ->
            appendLine("            addAll(LucideGeneratedMetadataChunk$index.values)")
        }
        appendLine("        }")
        appendLine("    }")
        appendLine("}")
    }

    private fun buildChunkFile(icons: List<ParsedIcon>, packageName: String, chunkIndex: Int): String = buildString {
        appendLine("package $packageName")
        appendLine()
        appendLine("import com.shermant.core.model.IconSourceSet")
        appendLine("import com.shermant.core.model.LucideIconCategory")
        appendLine("import com.shermant.core.model.LucideIconKey")
        appendLine("import com.shermant.core.model.LucideIconMetadata")
        appendLine()
        appendLine("internal object LucideGeneratedMetadataChunk$chunkIndex {")
        icons.forEach { icon ->
            val identifier = icon.name.toPascalCaseIdentifier()
            appendLine("    val $identifier = LucideIconMetadata(")
            appendLine("        key = LucideIconKey(${icon.name.asKotlinStringLiteral()}),")
            appendLine("        displayName = ${icon.displayName.asKotlinStringLiteral()},")
            appendLine("        tags = ${icon.tagsExpression()},")
            icon.zhDisplayName?.let {
                appendLine("        zhDisplayName = ${it.asKotlinStringLiteral()},")
            }
            if (icon.zhTags.isNotEmpty()) {
                appendLine("        zhTags = ${icon.zhTagsExpression()},")
            }
            appendLine("        categories = ${icon.categoriesExpression()},")
            appendLine("        defaultStrokeWidth = ${icon.defaultStrokeWidth}f,")
            appendLine("        sourceSet = IconSourceSet.BuiltIn,")
            appendLine("    )")
            appendLine()
        }
        appendLine("    val values: List<LucideIconMetadata> = listOf(")
        icons.forEachIndexed { index, icon ->
            val suffix = if (index == icons.lastIndex) "" else ","
            appendLine("        ${icon.name.toPascalCaseIdentifier()}$suffix")
        }
        appendLine("    )")
        appendLine("}")
    }

    private fun deleteOldChunks(outputDirectory: Path) {
        Files.list(outputDirectory).use { stream ->
            stream
                .filter { path -> path.fileName.toString().matches(Regex("""LucideGeneratedMetadataChunk\d+\.kt""")) }
                .forEach { path -> Files.deleteIfExists(path) }
        }
    }

    private fun ParsedIcon.tagsExpression(): String {
        val combinedTags = (tags + aliases).sorted()
        return if (combinedTags.isEmpty()) {
            "emptySet()"
        } else {
            "setOf(${combinedTags.joinToString(", ") { it.asKotlinStringLiteral() }})"
        }
    }

    private fun ParsedIcon.zhTagsExpression(): String =
        "setOf(${zhTags.sorted().joinToString(", ") { it.asKotlinStringLiteral() }})"

    private fun ParsedIcon.categoriesExpression(): String {
        val sortedCategories = categories.sorted()
        return if (sortedCategories.isEmpty()) {
            "emptySet()"
        } else {
            "setOf(${sortedCategories.joinToString(", ") { "LucideIconCategory.${it.toPascalCaseIdentifier()}" }})"
        }
    }

    private fun String.asKotlinStringLiteral(): String = buildString {
        append('"')
        for (character in this@asKotlinStringLiteral) {
            when (character) {
                '\\' -> append("\\\\")
                '"' -> append("\\\"")
                '\n' -> append("\\n")
                '\r' -> append("\\r")
                '\t' -> append("\\t")
                else -> append(character)
            }
        }
        append('"')
    }

    private companion object {
        const val CHUNK_SIZE = 100
    }
}
