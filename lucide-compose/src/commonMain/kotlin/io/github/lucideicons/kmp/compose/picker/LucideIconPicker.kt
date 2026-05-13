package io.github.lucideicons.kmp.compose.picker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.github.lucideicons.kmp.compose.LucideIconDefaults
import io.github.lucideicons.kmp.core.model.LucideIconCategory
import io.github.lucideicons.kmp.core.model.LucideIconMetadata
import io.github.lucideicons.kmp.core.registry.IconRegistry
import io.github.lucideicons.kmp.core.registry.LucideIcons

@Composable
fun LucideIconPicker(
    state: LucideIconPickerState,
    modifier: Modifier = Modifier,
    registry: IconRegistry = LucideIcons.registry,
    columns: Int = 6,
    iconSize: Dp = 20.dp,
    iconColor: Color = Color.Unspecified,
    strokeWidth: Float = LucideIconDefaults.StrokeWidth,
    showSearchBar: Boolean = true,
    showCategories: Boolean = true,
    onIconSelected: (LucideIconMetadata) -> Unit,
) {
    val categories = remember(registry) { LucideIconCategory.entries }
    val results = remember(registry, state.query, state.selectedCategory) {
        val filtered = registry.search(state.query)
        state.selectedCategory?.let { category ->
            filtered.filter { category in it.categories }
        } ?: filtered
    }

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        if (showSearchBar) {
            LucideIconSearchBar(
                query = state.query,
                onQueryChange = { state.query = it },
            )
        }
        if (showCategories) {
            LucideIconCategoryTabs(
                categories = categories,
                selectedCategory = state.selectedCategory,
                onCategorySelected = { state.selectedCategory = it },
            )
        }
        LucideIconGrid(
            icons = results,
            columns = columns,
            iconSize = iconSize,
            iconColor = iconColor,
            strokeWidth = strokeWidth,
            onIconSelected = onIconSelected,
        )
    }
}

@Composable
fun LucideIconPicker(
    modifier: Modifier = Modifier,
    query: String,
    onQueryChange: (String) -> Unit,
    onIconSelected: (String) -> Unit,
    registry: IconRegistry = LucideIcons.registry,
) {
    val state = rememberLucideIconPickerState(initialQuery = query)
    state.query = query
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        LucideIconSearchBar(
            query = query,
            onQueryChange = onQueryChange,
        )
        LucideIconPicker(
            state = state,
            registry = registry,
            showSearchBar = false,
            onIconSelected = { onIconSelected(it.key.value) },
        )
    }
}
