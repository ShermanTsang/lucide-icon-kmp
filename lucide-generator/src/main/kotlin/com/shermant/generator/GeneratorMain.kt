package com.shermant.generator

import com.shermant.lucideiconkmp.generator.config.GeneratorConfig
import com.shermant.lucideiconkmp.generator.parser.SvgIconParser
import com.shermant.lucideiconkmp.generator.source.LucideSvgSource
import com.shermant.lucideiconkmp.generator.writer.IconCategoryWriter
import com.shermant.lucideiconkmp.generator.writer.IconFileWriter
import com.shermant.lucideiconkmp.generator.writer.IconRegistryWriter
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
    val icons = source.load(config.inputDirectory).map(parser::parse)

    val iconOutputDirectory = config.outputDirectory.resolve("icons")
    val fileWriter = IconFileWriter()
    icons.forEach { icon ->
        fileWriter.write(icon, iconOutputDirectory, config.packageName + ".icons")
    }

    IconRegistryWriter().write(icons, config.outputDirectory, config.packageName)
    IconCategoryWriter().write(icons, config.outputDirectory, config.packageName)
}
