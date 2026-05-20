package com.shermant.compose.picker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.shermant.compose.LucideIconDefaults
import com.shermant.core.model.LucideIconCategory
import com.shermant.core.model.LucideLocale
import com.shermant.core.model.LucideIconMetadata
import com.shermant.core.registry.IconRegistry
import com.shermant.core.registry.LucideIcons

@Composable
fun LucideIconPicker(
    state: LucideIconPickerState,
    modifier: Modifier = Modifier,
    registry: IconRegistry = LucideIcons.registry,
    locale: LucideLocale = LucideLocale.En,
    columns: Int = 6,
    iconSize: Dp = 20.dp,
    iconColor: Color = Color.Unspecified,
    strokeWidth: Float = LucideIconDefaults.StrokeWidth,
    showSearchBar: Boolean = true,
    showCategories: Boolean = true,
    style: LucideIconPickerStyle = LucideIconPickerDefaults.style(),
    onIconSelected: (LucideIconMetadata) -> Unit,
) {
    val categories = remember(registry) { LucideIconCategory.entries }
    val results = remember(registry, locale, state.query, state.selectedCategory) {
        val filtered = registry.search(state.query, locale = locale)
        state.selectedCategory?.let { category ->
            filtered.filter { category in it.categories }
        } ?: filtered
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .then(
                if (style.containerBackgroundColor != Color.Unspecified) {
                    Modifier
                        .clip(style.containerShape)
                        .background(style.containerBackgroundColor)
                } else {
                    Modifier
                },
            )
            .padding(style.containerPadding),
        verticalArrangement = Arrangement.spacedBy(style.containerVerticalSpacing),
    ) {
        if (showSearchBar) {
            LucideIconSearchBar(
                query = state.query,
                onQueryChange = { state.query = it },
                style = style.searchBar,
            )
        }
        if (showCategories) {
            LucideIconCategoryTabs(
                categories = categories,
                locale = locale,
                selectedCategory = state.selectedCategory,
                onCategorySelected = { state.selectedCategory = it },
                style = style.categories,
            )
        }
        LucideIconGrid(
            icons = results,
            columns = columns,
            iconSize = iconSize,
            iconColor = iconColor,
            strokeWidth = strokeWidth,
            onIconSelected = onIconSelected,
            style = style.grid,
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
    locale: LucideLocale = LucideLocale.En,
    style: LucideIconPickerStyle = LucideIconPickerDefaults.style(locale = locale),
) {
    val state = rememberLucideIconPickerState(initialQuery = query)
    state.query = query
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(style.containerVerticalSpacing),
    ) {
        LucideIconSearchBar(
            query = query,
            onQueryChange = onQueryChange,
            style = style.searchBar,
        )
        LucideIconPicker(
            state = state,
            registry = registry,
            locale = locale,
            showSearchBar = false,
            style = style,
            onIconSelected = { onIconSelected(it.key.value) },
        )
    }
}
