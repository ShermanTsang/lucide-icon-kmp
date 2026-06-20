package com.shermant.lucideiconkmp.core.search

import com.shermant.lucideiconkmp.core.model.LucideIconMetadata

fun interface IconSearchStrategy {
    fun search(entries: List<LucideIconMetadata>, query: IconQuery): List<LucideIconMetadata>
}
