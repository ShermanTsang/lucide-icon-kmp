package com.shermant.core.search

import com.shermant.core.model.LucideIconMetadata

class DefaultIconSearchStrategy : IconSearchStrategy {
    override fun search(entries: List<LucideIconMetadata>, query: IconQuery): List<LucideIconMetadata> {
        if (query.normalizedValue.isBlank()) {
            return entries
                .sortedBy { it.displayName(query.locale) }
                .take(query.limit)
        }

        return entries
            .mapNotNull { metadata ->
                val localizedNames = buildList {
                    add(metadata.key.value)
                    add(metadata.displayName)
                    if (query.locale == com.shermant.core.model.LucideLocale.Zh) {
                        metadata.zhDisplayName?.let(::add)
                    }
                }
                val fallbackNames = listOfNotNull(metadata.zhDisplayName)
                val normalizedNameTerms = (localizedNames + fallbackNames)
                    .distinct()
                    .map(IconQuery::normalize)
                val normalizedTagTerms = (metadata.tags + metadata.zhTags)
                    .map(IconQuery::normalize)
                val score = when {
                    normalizedNameTerms.any { it == query.normalizedValue } -> 0
                    normalizedNameTerms.any { it.startsWith(query.normalizedValue) } -> 1
                    normalizedNameTerms.any { it.contains(query.normalizedValue) } -> 2
                    normalizedTagTerms.any { it.contains(query.normalizedValue) } -> 3
                    else -> null
                }
                score?.let { it to metadata }
            }
            .sortedWith(
                compareBy<Pair<Int, LucideIconMetadata>> { it.first }
                    .thenBy { it.second.displayName(query.locale) },
            )
            .map { it.second }
            .take(query.limit)
    }
}
