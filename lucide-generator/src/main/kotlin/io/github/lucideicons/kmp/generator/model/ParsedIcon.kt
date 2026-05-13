package io.github.lucideicons.kmp.generator.model

data class ParsedSvgPath(
    val d: String,
)

data class ParsedIcon(
    val name: String,
    val displayName: String,
    val width: Float,
    val height: Float,
    val paths: List<ParsedSvgPath>,
)
