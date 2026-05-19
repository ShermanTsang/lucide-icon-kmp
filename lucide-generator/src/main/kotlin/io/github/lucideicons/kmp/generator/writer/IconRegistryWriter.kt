package io.github.lucideicons.kmp.generator.writer

import io.github.lucideicons.kmp.generator.toPascalCaseIdentifier
import io.github.lucideicons.kmp.generator.model.ParsedIcon
import java.nio.file.Path
import kotlin.io.path.createDirectories
import kotlin.io.path.writeText

class IconRegistryWriter {
    fun write(icons: List<ParsedIcon>, outputDirectory: Path, packageName: String): Path {
        outputDirectory.createDirectories()
        val target = outputDirectory.resolve("LucideGeneratedRegistry.kt")
        target.writeText(buildFile(icons, packageName))
        return target
    }

    private fun buildFile(icons: List<ParsedIcon>, packageName: String): String = buildString {
        appendLine("package $packageName")
        appendLine()
        icons.forEach { icon ->
            appendLine("import $packageName.icons.${icon.name.toPascalCaseIdentifier().replaceFirstChar(Char::lowercaseChar)}Provider")
        }
        appendLine("import io.github.lucideicons.kmp.core.registry.LucideIconCreator")
        appendLine("import io.github.lucideicons.kmp.core.registry.MutableIconRegistry")
        appendLine("import io.github.lucideicons.kmp.core.registry.create")
        appendLine()
        appendLine("internal fun registerGeneratedIcons(registry: MutableIconRegistry) {")
        icons.forEach { icon ->
            val identifier = icon.name.toPascalCaseIdentifier()
            val providerName = icon.name.toPascalCaseIdentifier().replaceFirstChar(Char::lowercaseChar)
            appendLine("    registry.register(")
            appendLine("        metadata = LucideGeneratedMetadata.$identifier,")
            appendLine("        creator = LucideIconCreator { ${providerName}Provider.create() },")
            appendLine("        parameterizedProvider = ${providerName}Provider,")
            appendLine("    )")
        }
        appendLine("}")
    }
}
