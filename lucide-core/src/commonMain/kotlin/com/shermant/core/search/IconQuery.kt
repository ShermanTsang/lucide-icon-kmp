package com.shermant.core.search

import com.shermant.core.model.LucideLocale

data class IconQuery(
    val rawValue: String,
    val locale: LucideLocale = LucideLocale.En,
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
