package io.github.lucideicons.kmp.core.registry

import androidx.compose.ui.graphics.vector.ImageVector
import io.github.lucideicons.kmp.core.model.LucideIconCategory
import io.github.lucideicons.kmp.core.model.LucideIconKey
import io.github.lucideicons.kmp.core.model.LucideIconMetadata

interface IconRegistry {
    fun register(
        metadata: LucideIconMetadata,
        creator: LucideIconCreator,
        parameterizedProvider: ParameterizedIconProvider? = null,
        replace: Boolean = false,
    )

    fun register(key: String, creator: LucideIconCreator)

    fun get(key: LucideIconKey): ImageVector?

    fun get(name: String): ImageVector?

    fun resolve(key: LucideIconKey, parameters: IconRenderParameters): ImageVector?

    fun resolve(name: String, parameters: IconRenderParameters): ImageVector?

    fun require(key: LucideIconKey): ImageVector

    fun contains(name: String): Boolean

    fun keys(): List<LucideIconKey>

    fun metadata(name: String): LucideIconMetadata?

    fun metadata(key: LucideIconKey): LucideIconMetadata?

    fun search(query: String, limit: Int = 100): List<LucideIconMetadata>

    fun byCategory(category: LucideIconCategory): List<LucideIconMetadata>
}
