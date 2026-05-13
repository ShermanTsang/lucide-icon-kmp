package io.github.lucideicons.kmp.generator.parser

import io.github.lucideicons.kmp.generator.model.ParsedIcon
import io.github.lucideicons.kmp.generator.model.ParsedSvgPath

class SvgIconParser {
    fun parse(name: String, svgContent: String): ParsedIcon {
        val width = extractDimension(svgContent, "width") ?: 24f
        val height = extractDimension(svgContent, "height") ?: 24f
        val paths = PATH_REGEX.findAll(svgContent)
            .mapNotNull { match ->
                val pathData = match.groups[1]?.value ?: return@mapNotNull null
                ParsedSvgPath(d = pathData)
            }
            .toList()

        require(paths.isNotEmpty()) { "SVG '$name' does not contain any <path> elements." }

        return ParsedIcon(
            name = name,
            displayName = name.split('-', '_').joinToString(" ") { it.replaceFirstChar(Char::uppercase) },
            width = width,
            height = height,
            paths = paths,
        )
    }

    private fun extractDimension(svgContent: String, attributeName: String): Float? {
        val pattern = Regex("""$attributeName=\"([0-9.]+)\"""")
        return pattern.find(svgContent)?.groupValues?.get(1)?.toFloatOrNull()
    }

    private companion object {
        val PATH_REGEX = Regex("""<path[^>]*d=\"([^\"]+)\"[^>]*/?>""")
    }
}
