package com.shermant.lucideiconkmp.core.search

import com.shermant.lucideiconkmp.core.model.LucideIconMetadata

class DefaultIconSearchStrategy : IconSearchStrategy {
    override fun search(entries: List<LucideIconMetadata>, query: IconQuery): List<LucideIconMetadata> {
        if (query.normalizedValue.isBlank()) {
            return entries
                .sortedBy { it.displayName(query.locale) }
                .take(query.limit)
        }

        val rankedEntries = mutableListOf<Pair<Int, LucideIconMetadata>>()
        for (metadata in entries) {
            val score = metadata.matchScore(query.normalizedValue)
            if (score != null) {
                rankedEntries += score to metadata
            }
        }

        rankedEntries.sortWith(
            compareBy<Pair<Int, LucideIconMetadata>> { it.first }
                .thenBy { it.second.displayName(query.locale) },
        )

        return rankedEntries
            .asSequence()
            .take(query.limit)
            .map { it.second }
            .toList()
    }

    private fun LucideIconMetadata.matchScore(normalizedQuery: String): Int? {
        val normalizedKey = IconQuery.normalize(key.value)
        val normalizedDisplayName = IconQuery.normalize(displayName)
        val normalizedZhDisplayName = zhDisplayName?.let(IconQuery::normalize)

        evaluateNameMatch(normalizedKey, normalizedQuery)?.let { return it }
        if (normalizedDisplayName != normalizedKey) {
            evaluateNameMatch(normalizedDisplayName, normalizedQuery)?.let { return it }
        }
        if (
            normalizedZhDisplayName != null &&
            normalizedZhDisplayName != normalizedKey &&
            normalizedZhDisplayName != normalizedDisplayName
        ) {
            evaluateNameMatch(normalizedZhDisplayName, normalizedQuery)?.let { return it }
        }

        if (tags.any { IconQuery.normalize(it).contains(normalizedQuery) }) {
            return 3
        }
        if (zhTags.any { IconQuery.normalize(it).contains(normalizedQuery) }) {
            return 3
        }

        return null
    }

    private fun evaluateNameMatch(normalizedName: String, normalizedQuery: String): Int? = when {
        normalizedName == normalizedQuery -> 0
        normalizedName.startsWith(normalizedQuery) -> 1
        normalizedName.contains(normalizedQuery) -> 2
        else -> null
    }
}
