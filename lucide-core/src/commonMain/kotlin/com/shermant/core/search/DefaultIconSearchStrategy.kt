package com.shermant.core.search

import com.shermant.core.model.LucideIconMetadata

class DefaultIconSearchStrategy : IconSearchStrategy {
    override fun search(entries: List<LucideIconMetadata>, query: IconQuery): List<LucideIconMetadata> {
        if (query.normalizedValue.isBlank()) {
            return entries.sortedBy { it.displayName }.take(query.limit)
        }

        return entries
            .mapNotNull { metadata ->
                val normalizedName = IconQuery.normalize(metadata.key.value)
                val normalizedDisplayName = IconQuery.normalize(metadata.displayName)
                val normalizedTags = metadata.tags.map(IconQuery::normalize)
                val score = when {
                    normalizedName == query.normalizedValue || normalizedDisplayName == query.normalizedValue -> 0
                    normalizedName.startsWith(query.normalizedValue) || normalizedDisplayName.startsWith(query.normalizedValue) -> 1
                    normalizedName.contains(query.normalizedValue) || normalizedDisplayName.contains(query.normalizedValue) -> 2
                    normalizedTags.any { it.contains(query.normalizedValue) } -> 3
                    else -> null
                }
                score?.let { it to metadata }
            }
            .sortedWith(compareBy<Pair<Int, LucideIconMetadata>> { it.first }.thenBy { it.second.displayName })
            .map { it.second }
            .take(query.limit)
    }
}
