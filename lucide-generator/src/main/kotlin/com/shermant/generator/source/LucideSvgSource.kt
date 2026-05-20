package com.shermant.generator.source

import com.shermant.generator.model.RawLucideIcon
import com.shermant.generator.model.RawLucideMetadata
import java.nio.file.Files
import java.nio.file.Path
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlin.io.path.extension
import kotlin.io.path.isDirectory
import kotlin.io.path.nameWithoutExtension

class LucideSvgSource {
    fun load(directory: Path): List<RawLucideIcon> {
        require(Files.exists(directory)) { "Input directory does not exist: $directory" }
        require(directory.isDirectory()) { "Input directory is not a directory: $directory" }
        val localizedZhMetadata = readLocalizedZhMetadata(directory.parent.resolve("localized-icons.zh-CN.json"))

        val svgFiles = Files.list(directory).use { stream ->
            stream
                .filter { Files.isRegularFile(it) && it.extension.equals("svg", ignoreCase = true) }
                .sorted()
                .toList()
        }

        require(svgFiles.isNotEmpty()) { "No SVG files found in $directory" }

        return svgFiles.map { svgFile ->
            val iconName = svgFile.nameWithoutExtension
            val metadataFile = directory.resolve("$iconName.json")
            require(Files.exists(metadataFile)) { "Missing metadata JSON for icon '$iconName': $metadataFile" }

            RawLucideIcon(
                name = iconName,
                svgContent = Files.readString(svgFile),
                metadata = readMetadata(
                    file = metadataFile,
                    localizedZhMetadata = localizedZhMetadata[iconName],
                ),
            )
        }
    }

    private fun readMetadata(file: Path, localizedZhMetadata: LocalizedIconText?): RawLucideMetadata {
        val root = json.parseToJsonElement(Files.readString(file)).jsonObject
        return RawLucideMetadata(
            tags = root.stringArray("tags").toSortedSet(),
            zhDisplayName = localizedZhMetadata?.displayName,
            zhTags = localizedZhMetadata?.tags?.toSortedSet() ?: emptySet(),
            aliases = root.aliasNames().toSortedSet(),
            categories = root.stringArray("categories").toSortedSet(),
        )
    }

    private fun readLocalizedZhMetadata(file: Path): Map<String, LocalizedIconText> {
        if (!Files.exists(file)) {
            return emptyMap()
        }

        val root = json.parseToJsonElement(Files.readString(file)).jsonObject
        return root.mapValues { (_, value) ->
            val item = value.jsonObject
            LocalizedIconText(
                displayName = item["displayName"]?.jsonPrimitive?.contentOrNull,
                tags = item.stringArray("tags"),
            )
        }
    }

    private fun kotlinx.serialization.json.JsonObject.stringArray(fieldName: String): Set<String> =
        this[fieldName]
            ?.jsonArray
            ?.map { item -> item.jsonPrimitive.content }
            ?.toSet()
            ?: emptySet()

    private fun kotlinx.serialization.json.JsonObject.aliasNames(): Set<String> =
        this["aliases"]
            ?.jsonArray
            ?.mapNotNull { item ->
                item.jsonObject["name"]?.jsonPrimitive?.content
            }
            ?.toSet()
            ?: emptySet()

    private data class LocalizedIconText(
        val displayName: String? = null,
        val tags: Set<String> = emptySet(),
    )

    private companion object {
        val json = Json {
            ignoreUnknownKeys = true
        }
    }
}
