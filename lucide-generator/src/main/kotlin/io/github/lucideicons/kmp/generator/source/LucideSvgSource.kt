package io.github.lucideicons.kmp.generator.source

import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.extension
import kotlin.io.path.nameWithoutExtension

class LucideSvgSource {
    fun load(directory: Path): List<Pair<String, String>> {
        require(Files.exists(directory)) { "Input directory does not exist: $directory" }

        return Files.list(directory).use { stream ->
            stream
                .filter { Files.isRegularFile(it) && it.extension.equals("svg", ignoreCase = true) }
                .sorted()
                .map { path -> path.nameWithoutExtension to Files.readString(path) }
                .toList()
        }
    }
}
