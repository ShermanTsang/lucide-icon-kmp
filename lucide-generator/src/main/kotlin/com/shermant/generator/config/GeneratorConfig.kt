package com.shermant.generator.config

import java.nio.file.Path

data class GeneratorConfig(
    val inputDirectory: Path,
    val outputDirectory: Path,
    val packageName: String,
)
