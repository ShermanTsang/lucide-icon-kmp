package com.shermant.lucideiconkmp.core

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.shermant.lucideiconkmp.core.generated.LucideGeneratedMetadata
import com.shermant.lucideiconkmp.core.model.LucideIconKey
import com.shermant.lucideiconkmp.core.model.LucideIconMetadata
import com.shermant.lucideiconkmp.core.model.LucideIconCategory
import com.shermant.lucideiconkmp.core.model.LucideLocale
import com.shermant.lucideiconkmp.core.registry.BuiltInIconRegistrar
import com.shermant.lucideiconkmp.core.registry.DefaultIconRegistry
import com.shermant.lucideiconkmp.core.registry.IconRenderParameters
import com.shermant.lucideiconkmp.core.registry.LucideIconCreator
import com.shermant.lucideiconkmp.core.registry.ParameterizedIconProvider
import com.shermant.lucideiconkmp.core.search.DefaultIconSearchStrategy
import com.shermant.lucideiconkmp.core.search.IconSearchStrategy
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertSame
import kotlin.test.assertTrue

class IconRegistryTest {
    private companion object {
        const val expectedGeneratedIconCount = 1711
    }

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

        assertEquals(expectedGeneratedIconCount, LucideGeneratedMetadata.all.size)
        assertEquals(expectedGeneratedIconCount, registry.keys().size)
        assertTrue(registry.contains("activity"))
        assertTrue(registry.contains("airplay"))
        assertTrue(registry.contains("blender"))
        assertTrue(registry.contains("broccoli"))
        assertTrue(registry.contains("camera"))
        assertNotNull(registry.get("activity"))
        assertNotNull(registry.resolve("activity", IconRenderParameters(strokeWidth = 3f)))
        assertNotNull(registry.resolve("airplay", IconRenderParameters(strokeWidth = 1.5f)))
        assertNotNull(registry.resolve("blender", IconRenderParameters(strokeWidth = 2.25f)))
        assertNotNull(registry.resolve("sticky-notes", IconRenderParameters(strokeWidth = 1.75f)))
        assertTrue(registry.byCategory(LucideIconCategory.Multimedia).isNotEmpty())
    }

    @Test
    fun cachesParameterizedIconsByRenderParameters() {
        val registry = DefaultIconRegistry()
        var parameterizedCreations = 0
        registry.register(
            metadata = LucideIconMetadata(LucideIconKey("demo")),
            creator = LucideIconCreator {
                ImageVector.Builder("demo-default", 24f.dp, 24f.dp, 24f, 24f).build()
            },
            parameterizedProvider = ParameterizedIconProvider {
                parameterizedCreations += 1
                ImageVector.Builder("demo-parameterized-$parameterizedCreations", 24f.dp, 24f.dp, 24f, 24f).build()
            },
        )

        val first = registry.resolve("demo", IconRenderParameters(size = 20f, strokeWidth = 2f))
        val second = registry.resolve("demo", IconRenderParameters(size = 20f, strokeWidth = 2f))
        val third = registry.resolve("demo", IconRenderParameters(size = 24f, strokeWidth = 2f))

        assertSame(first, second)
        assertTrue(first !== third)
        assertEquals(2, parameterizedCreations)
    }

    @Test
    fun reusesParameterizedIconsAcrossRepeatedResolves() {
        val registry = DefaultIconRegistry()
        var parameterizedCreations = 0
        registry.register(
            metadata = LucideIconMetadata(LucideIconKey("demo")),
            creator = LucideIconCreator {
                ImageVector.Builder("demo-default", 24f.dp, 24f.dp, 24f, 24f).build()
            },
            parameterizedProvider = ParameterizedIconProvider {
                parameterizedCreations += 1
                ImageVector.Builder("demo-parameterized-$parameterizedCreations", 24f.dp, 24f.dp, 24f, 24f).build()
            },
        )

        repeat(50) {
            registry.resolve("demo", IconRenderParameters(size = 20f, strokeWidth = 2f))
        }

        assertEquals(1, parameterizedCreations)
    }

    @Test
    fun replacingRegisteredIconInvalidatesResolvedIconCaches() {
        val registry = DefaultIconRegistry()
        var firstDefaultCreations = 0
        var secondDefaultCreations = 0
        var firstParameterizedCreations = 0
        var secondParameterizedCreations = 0

        registry.register(
            metadata = LucideIconMetadata(LucideIconKey("demo")),
            creator = LucideIconCreator {
                firstDefaultCreations += 1
                ImageVector.Builder("demo-first-default", 24f.dp, 24f.dp, 24f, 24f).build()
            },
            parameterizedProvider = ParameterizedIconProvider {
                firstParameterizedCreations += 1
                ImageVector.Builder("demo-first-parameterized", 24f.dp, 24f.dp, 24f, 24f).build()
            },
        )

        val firstDefault = registry.get("demo")
        val firstParameterized = registry.resolve("demo", IconRenderParameters(size = 20f, strokeWidth = 2f))

        registry.register(
            metadata = LucideIconMetadata(LucideIconKey("demo")),
            creator = LucideIconCreator {
                secondDefaultCreations += 1
                ImageVector.Builder("demo-second-default", 24f.dp, 24f.dp, 24f, 24f).build()
            },
            parameterizedProvider = ParameterizedIconProvider {
                secondParameterizedCreations += 1
                ImageVector.Builder("demo-second-parameterized", 24f.dp, 24f.dp, 24f, 24f).build()
            },
            replace = true,
        )

        val secondDefault = registry.get("demo")
        val secondParameterized = registry.resolve("demo", IconRenderParameters(size = 20f, strokeWidth = 2f))

        assertTrue(firstDefault !== secondDefault)
        assertTrue(firstParameterized !== secondParameterized)
        assertEquals(1, firstDefaultCreations)
        assertEquals(1, secondDefaultCreations)
        assertEquals(1, firstParameterizedCreations)
        assertEquals(1, secondParameterizedCreations)
    }

    @Test
    fun cachesSearchResultsAndInvalidatesOnRegister() {
        val delegateStrategy = DefaultIconSearchStrategy()
        var searchCalls = 0
        val registry = DefaultIconRegistry(
            searchStrategy = IconSearchStrategy { entries, query ->
                searchCalls += 1
                delegateStrategy.search(entries, query)
            },
        )
        registry.register(
            metadata = LucideIconMetadata(LucideIconKey("alpha"), displayName = "Alpha"),
            creator = LucideIconCreator {
                ImageVector.Builder("alpha", 24f.dp, 24f.dp, 24f, 24f).build()
            },
        )
        registry.register(
            metadata = LucideIconMetadata(LucideIconKey("beta"), displayName = "Beta"),
            creator = LucideIconCreator {
                ImageVector.Builder("beta", 24f.dp, 24f.dp, 24f, 24f).build()
            },
        )

        val firstResults = registry.search("alp")
        val secondResults = registry.search("alp")
        registry.search("")
        registry.search("")

        assertEquals(1, searchCalls)
        assertEquals(firstResults.map { it.key }, secondResults.map { it.key })

        registry.register(
            metadata = LucideIconMetadata(LucideIconKey("alpine"), displayName = "Alpine"),
            creator = LucideIconCreator {
                ImageVector.Builder("alpine", 24f.dp, 24f.dp, 24f, 24f).build()
            },
        )

        val refreshedResults = registry.search("alp")

        assertEquals(2, searchCalls)
        assertTrue(refreshedResults.any { it.key.value == "alpine" })
    }

    @Test
    fun cachesBlankQueriesByLocaleWithoutInvokingSearchStrategy() {
        var searchCalls = 0
        val registry = DefaultIconRegistry(
            searchStrategy = IconSearchStrategy { _, _ ->
                searchCalls += 1
                emptyList()
            },
        )
        registry.register(
            metadata = LucideIconMetadata(
                LucideIconKey("alpha"),
                displayName = "Alpha",
                zhDisplayName = "阿尔法",
            ),
            creator = LucideIconCreator {
                ImageVector.Builder("alpha", 24f.dp, 24f.dp, 24f, 24f).build()
            },
        )
        registry.register(
            metadata = LucideIconMetadata(
                LucideIconKey("beta"),
                displayName = "Beta",
                zhDisplayName = "贝塔",
            ),
            creator = LucideIconCreator {
                ImageVector.Builder("beta", 24f.dp, 24f.dp, 24f, 24f).build()
            },
        )

        val firstEnglish = registry.search("", locale = LucideLocale.En, limit = 10)
        val secondEnglish = registry.search("", locale = LucideLocale.En, limit = 1)
        val firstChinese = registry.search("", locale = LucideLocale.Zh, limit = 10)
        val secondChinese = registry.search("", locale = LucideLocale.Zh, limit = 1)

        assertEquals(0, searchCalls)
        assertEquals(listOf("Alpha", "Beta"), firstEnglish.map { it.displayName(LucideLocale.En) })
        assertEquals(listOf("Alpha"), secondEnglish.map { it.displayName(LucideLocale.En) })
        assertEquals(
            firstChinese.map { it.displayName(LucideLocale.Zh) }.sorted(),
            firstChinese.map { it.displayName(LucideLocale.Zh) },
        )
        assertEquals(
            firstChinese.take(1).map { it.displayName(LucideLocale.Zh) },
            secondChinese.map { it.displayName(LucideLocale.Zh) },
        )
    }

    @Test
    fun invalidatesCategoryIndexAfterRegister() {
        val registry = DefaultIconRegistry()
        registry.register(
            metadata = LucideIconMetadata(
                key = LucideIconKey("alpha"),
                categories = setOf(LucideIconCategory.Text),
            ),
            creator = LucideIconCreator {
                ImageVector.Builder("alpha", 24f.dp, 24f.dp, 24f, 24f).build()
            },
        )

        val firstResults = registry.byCategory(LucideIconCategory.Text)
        registry.register(
            metadata = LucideIconMetadata(
                key = LucideIconKey("beta"),
                categories = setOf(LucideIconCategory.Text),
            ),
            creator = LucideIconCreator {
                ImageVector.Builder("beta", 24f.dp, 24f.dp, 24f, 24f).build()
            },
        )
        val refreshedResults = registry.byCategory(LucideIconCategory.Text)

        assertEquals(listOf("alpha"), firstResults.map { it.key.value })
        assertEquals(listOf("alpha", "beta"), refreshedResults.map { it.key.value })
    }
}
