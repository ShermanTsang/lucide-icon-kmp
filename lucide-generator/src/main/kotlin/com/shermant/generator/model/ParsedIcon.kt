package com.shermant.generator.model

data class ParsedVectorPath(
    val pathData: String,
    val fill: PaintToken,
    val stroke: PaintToken,
    val strokeWidth: Float,
    val strokeLineCap: StrokeCapToken,
    val strokeLineJoin: StrokeJoinToken,
)

data class ParsedIcon(
    val name: String,
    val displayName: String,
    val zhDisplayName: String? = null,
    val viewportWidth: Float,
    val viewportHeight: Float,
    val defaultStrokeWidth: Float,
    val paths: List<ParsedVectorPath>,
    val tags: Set<String>,
    val zhTags: Set<String> = emptySet(),
    val aliases: Set<String>,
    val categories: Set<String>,
)

data class RawLucideIcon(
    val name: String,
    val svgContent: String,
    val metadata: RawLucideMetadata,
)

data class RawLucideMetadata(
    val tags: Set<String>,
    val zhDisplayName: String? = null,
    val zhTags: Set<String> = emptySet(),
    val aliases: Set<String>,
    val categories: Set<String>,
)

enum class PaintToken {
    None,
    CurrentColor,
}

enum class StrokeCapToken {
    Butt,
    Round,
    Square,
}

enum class StrokeJoinToken {
    Miter,
    Round,
    Bevel,
}
