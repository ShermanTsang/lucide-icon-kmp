package com.shermant.core

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.shermant.core.model.LucideIconKey
import com.shermant.core.model.LucideIconMetadata
import com.shermant.core.model.LucideIconCategory
import com.shermant.core.model.LucideLocale
import com.shermant.core.registry.BuiltInIconRegistrar
import com.shermant.core.registry.DefaultIconRegistry
import com.shermant.core.registry.IconRenderParameters
import com.shermant.core.registry.LucideIconCreator
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertSame
import kotlin.test.assertTrue

class IconRegistryTest {
    @Test
    fun lazilyCreatesAndCachesIcons() {
        val registry = DefaultIconRegistry()
        var creations = 0

        registry.register(
            metadata = LucideIconMetadata(LucideIconKey("demo")),
            creator = LucideIconCreator {
                creations += 1
                ImageVector.Builder("demo", 24f.dp, 24f.dp, 24f, 24f).build()
            },
        )

        assertEquals(0, creations)
        val first = registry.get("demo")
        val second = registry.get("demo")

        assertEquals(1, creations)
        assertSame(first, second)
    }

    @Test
    fun returnsMetadataWithoutCreatingImageVector() {
        val registry = DefaultIconRegistry()
        var creations = 0
        registry.register(
            metadata = LucideIconMetadata(
                LucideIconKey("demo"),
                displayName = "Demo",
                zhDisplayName = "示例",
            ),
            creator = LucideIconCreator {
                creations += 1
                ImageVector.Builder("demo", 24f.dp, 24f.dp, 24f, 24f).build()
            },
        )

        val metadata = registry.metadata("demo")

        assertEquals("Demo", metadata?.displayName)
        assertEquals("示例", metadata?.displayName(LucideLocale.Zh))
        assertEquals(0, creations)
    }

    @Test
    fun resolvesGeneratedIcons() {
        val registry = DefaultIconRegistry().apply {
            BuiltInIconRegistrar.registerInto(this)
        }

        assertTrue(registry.contains("activity"))
        assertTrue(registry.contains("airplay"))
        assertTrue(registry.contains("camera"))
        assertTrue(registry.keys().size > 1000)
        assertNotNull(registry.get("activity"))
        assertNotNull(registry.resolve("activity", IconRenderParameters(strokeWidth = 3f)))
        assertNotNull(registry.resolve("airplay", IconRenderParameters(strokeWidth = 1.5f)))
        assertTrue(registry.byCategory(LucideIconCategory.Multimedia).isNotEmpty())
    }
}
