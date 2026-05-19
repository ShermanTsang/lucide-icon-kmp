package com.shermant.core.search

import com.shermant.core.model.LucideIconMetadata

fun interface IconSearchStrategy {
    fun search(entries: List<LucideIconMetadata>, query: IconQuery): List<LucideIconMetadata>
}
