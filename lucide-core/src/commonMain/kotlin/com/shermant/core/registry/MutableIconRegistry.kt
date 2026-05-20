package com.shermant.core.registry

import androidx.compose.ui.graphics.vector.ImageVector
import com.shermant.core.model.LucideIconCategory
import com.shermant.core.model.LucideIconKey
import com.shermant.core.model.LucideLocale
import com.shermant.core.model.LucideIconMetadata
import com.shermant.core.search.DefaultIconSearchStrategy
import com.shermant.core.search.IconQuery
import com.shermant.core.search.IconSearchStrategy

interface MutableIconRegistry : IconRegistry

class DefaultIconRegistry(
    private val searchStrategy: IconSearchStrategy = DefaultIconSearchStrategy(),
) : MutableIconRegistry {
    private val iconFactory = IconFactory()
    private val entries = linkedMapOf<String, IconEntry>()
    private var metadataSnapshot: List<LucideIconMetadata>? = null
    private var keySnapshot: List<LucideIconKey>? = null
    private var categoryIndex: Map<LucideIconCategory, List<LucideIconMetadata>>? = null
    private val blankSearchCache = mutableMapOf<LucideLocale, List<LucideIconMetadata>>()
    private val searchCache = mutableMapOf<SearchCacheKey, List<LucideIconMetadata>>()

    override fun register(
        metadata: LucideIconMetadata,
        creator: LucideIconCreator,
        parameterizedProvider: ParameterizedIconProvider?,
        replace: Boolean,
    ) {
        val name = metadata.key.value
        if (!replace && entries.containsKey(name)) {
            error("Icon '$name' is already registered.")
        }
        entries[name] = IconEntry(
            metadata = metadata,
            creator = creator,
            parameterizedProvider = parameterizedProvider,
        )
        iconFactory.invalidate(name)
        invalidateDerivedCaches()
    }

    override fun register(key: String, creator: LucideIconCreator) {
        register(
            metadata = LucideIconMetadata(key = LucideIconKey(key)),
            creator = creator,
        )
    }

    override fun get(key: LucideIconKey): ImageVector? = get(key.value)

    override fun get(name: String): ImageVector? = resolve(name, IconRenderParameters())

    override fun resolve(key: LucideIconKey, parameters: IconRenderParameters): ImageVector? =
        resolve(key.value, parameters)

    override fun resolve(name: String, parameters: IconRenderParameters): ImageVector? {
        val entry = entries[name] ?: return null
        return iconFactory.create(name = name, entry = entry, parameters = parameters)
    }

    override fun require(key: LucideIconKey): ImageVector =
        get(key) ?: error("Unknown icon '${key.value}'.")

    override fun contains(name: String): Boolean = entries.containsKey(name)

    override fun keys(): List<LucideIconKey> = keySnapshot ?: buildKeySnapshot().also { keySnapshot = it }

    override fun metadata(name: String): LucideIconMetadata? = entries[name]?.metadata

    override fun metadata(key: LucideIconKey): LucideIconMetadata? = metadata(key.value)

    override fun search(query: String, limit: Int): List<LucideIconMetadata> =
        search(query = query, locale = LucideLocale.En, limit = limit)

    override fun search(query: String, locale: LucideLocale, limit: Int): List<LucideIconMetadata> =
        searchInternal(
            query = IconQuery(rawValue = query, locale = locale, limit = limit),
        )

    override fun byCategory(category: LucideIconCategory): List<LucideIconMetadata> =
        categoryIndex?.get(category) ?: buildCategoryIndex().also { categoryIndex = it }[category].orEmpty()

    private fun searchInternal(query: IconQuery): List<LucideIconMetadata> {
        if (query.normalizedValue.isBlank()) {
            val cachedResults = blankSearchCache.getOrPut(query.locale) {
                metadataSnapshot()
                    .sortedBy { it.displayName(query.locale) }
            }
            return cachedResults.limitTo(query.limit)
        }

        val cacheKey = SearchCacheKey(
            normalizedQuery = query.normalizedValue,
            locale = query.locale,
            limit = query.limit,
        )
        return searchCache.getOrPut(cacheKey) {
            searchStrategy.search(
                entries = metadataSnapshot(),
                query = query,
            )
        }
    }

    private fun metadataSnapshot(): List<LucideIconMetadata> =
        metadataSnapshot ?: entries.values.map { it.metadata }.also { metadataSnapshot = it }

    private fun buildKeySnapshot(): List<LucideIconKey> =
        metadataSnapshot().map { it.key }

    private fun buildCategoryIndex(): Map<LucideIconCategory, List<LucideIconMetadata>> {
        val indexedMetadata = mutableMapOf<LucideIconCategory, MutableList<LucideIconMetadata>>()
        for (metadata in metadataSnapshot()) {
            for (category in metadata.categories) {
                indexedMetadata.getOrPut(category) { mutableListOf() }.add(metadata)
            }
        }
        return LucideIconCategory.entries.associateWith { category ->
            indexedMetadata[category].orEmpty()
        }
    }

    private fun invalidateDerivedCaches() {
        metadataSnapshot = null
        keySnapshot = null
        categoryIndex = null
        blankSearchCache.clear()
        searchCache.clear()
    }

    private fun List<LucideIconMetadata>.limitTo(limit: Int): List<LucideIconMetadata> =
        if (size <= limit) this else take(limit)

    private data class SearchCacheKey(
        val normalizedQuery: String,
        val locale: LucideLocale,
        val limit: Int,
    )
}
