package com.shermant.lucideiconkmp.core

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.shermant.lucideiconkmp.core.model.LucideIconCategory
import com.shermant.lucideiconkmp.core.registry.DefaultIconRegistry
import com.shermant.lucideiconkmp.core.registry.registerCustomIcon
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class CustomIconRegistrationTest {
    @Test
    fun registersCustomIconMetadataAndVector() {
        val registry = DefaultIconRegistry()
        val icon = ImageVector.Builder("brand-logo", 24.dp, 24.dp, 24f, 24f).build()

        registry.registerCustomIcon(
            name = "brand-logo",
            imageVector = icon,
            categories = setOf(LucideIconCategory.Custom),
            tags = setOf("brand"),
        )

        assertNotNull(registry.get("brand-logo"))
        assertEquals(LucideIconCategory.Custom, registry.metadata("brand-logo")?.categories?.single())
    }
}
