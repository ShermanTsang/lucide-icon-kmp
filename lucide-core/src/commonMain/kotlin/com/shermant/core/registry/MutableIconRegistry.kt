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

    override fun keys(): List<LucideIconKey> = entries.values.map { it.metadata.key }

    override fun metadata(name: String): LucideIconMetadata? = entries[name]?.metadata

    override fun metadata(key: LucideIconKey): LucideIconMetadata? = metadata(key.value)

    override fun search(query: String, limit: Int): List<LucideIconMetadata> =
        search(query = query, locale = LucideLocale.En, limit = limit)

    override fun search(query: String, locale: LucideLocale, limit: Int): List<LucideIconMetadata> =
        searchStrategy.search(
            entries = entries.values.map { it.metadata },
            query = IconQuery(rawValue = query, locale = locale, limit = limit),
        )

    override fun byCategory(category: LucideIconCategory): List<LucideIconMetadata> =
        entries.values.map { it.metadata }.filter { category in it.categories }
}
