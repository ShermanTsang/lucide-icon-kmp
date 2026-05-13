package io.github.lucideicons.kmp.generator

import io.github.lucideicons.kmp.generator.config.GeneratorConfig
import io.github.lucideicons.kmp.generator.parser.SvgIconParser
import io.github.lucideicons.kmp.generator.source.LucideSvgSource
import io.github.lucideicons.kmp.generator.writer.IconCategoryWriter
import io.github.lucideicons.kmp.generator.writer.IconFileWriter
import io.github.lucideicons.kmp.generator.writer.IconRegistryWriter
import java.nio.file.Path

fun main(args: Array<String>) {
    require(args.size >= 3) {
        "Usage: <inputDirectory> <outputDirectory> <packageName>"
    }

    val config = GeneratorConfig(
        inputDirectory = Path.of(args[0]),
        outputDirectory = Path.of(args[1]),
        packageName = args[2],
    )

    val parser = SvgIconParser()
    val source = LucideSvgSource()
    val icons = source.load(config.inputDirectory).map { (name, content) ->
        parser.parse(name, content)
    }

    val iconOutputDirectory = config.outputDirectory.resolve("icons")
    val fileWriter = IconFileWriter()
    icons.forEach { icon ->
        fileWriter.write(icon, iconOutputDirectory, config.packageName + ".icons")
    }

    IconRegistryWriter().write(icons, config.outputDirectory, config.packageName)
    IconCategoryWriter().write(icons, config.outputDirectory, config.packageName)
}
