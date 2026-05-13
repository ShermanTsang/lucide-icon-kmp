package io.github.lucideicons.kmp.core.search

import io.github.lucideicons.kmp.core.model.LucideIconMetadata

fun interface IconSearchStrategy {
    fun search(entries: List<LucideIconMetadata>, query: IconQuery): List<LucideIconMetadata>
}
