package io.github.lucideicons.kmp.core.search

data class IconQuery(
    val rawValue: String,
    val limit: Int = 100,
) {
    val normalizedValue: String = normalize(rawValue)

    companion object {
        fun normalize(value: String): String = value
            .trim()
            .lowercase()
            .replace("-", "")
            .replace("_", "")
            .replace(" ", "")
    }
}
