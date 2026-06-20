package com.shermant.lucideiconkmp.compose.picker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.shermant.lucideiconkmp.compose.LucideIconDefaults
import com.shermant.lucideiconkmp.core.model.LucideIconCategory
import com.shermant.lucideiconkmp.core.model.LucideIconMetadata
import com.shermant.lucideiconkmp.core.model.LucideLocale
import com.shermant.lucideiconkmp.core.registry.IconRegistry
import com.shermant.lucideiconkmp.core.registry.LucideIcons

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
    pageSize: Int = 48,
    searchLimit: Int = 100,
    style: LucideIconPickerStyle = LucideIconPickerDefaults.style(),
    modifiers: LucideIconPickerModifiers = LucideIconPickerDefaults.modifiers(),
    onIconSelected: (LucideIconMetadata) -> Unit,
) {
    require(pageSize > 0) { "pageSize must be greater than 0." }
    require(searchLimit > 0) { "searchLimit must be greater than 0." }

    val categories = remember(registry) { LucideIconCategory.entries }
    val allIcons = remember(registry, locale) {
        registry.keys()
            .mapNotNull { key -> registry.metadata(key) }
            .sortedBy { it.displayName(locale) }
    }
    val searchResults = remember(registry, locale, state.query, searchLimit, allIcons) {
        if (state.query.isBlank()) {
            allIcons
        } else {
            registry.search(state.query, locale = locale, limit = searchLimit)
        }
    }
    val visibleResults = remember(searchResults, state.selectedCategory) {
        state.selectedCategory?.let { category ->
            searchResults.filter { category in it.categories }
        } ?: searchResults
    }
    val pagination = remember(visibleResults, pageSize, state.currentPage) {
        paginateItems(
            items = visibleResults,
            pageSize = pageSize,
            requestedPage = state.currentPage,
        )
    }

    LaunchedEffect(state.currentPage, pagination.currentPage) {
        if (state.currentPage != pagination.currentPage) {
            state.goToPage(pagination.currentPage)
        }
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
                onQueryChange = state::updateQuery,
                modifier = modifiers.searchBar,
                style = style.searchBar,
            )
        }
        if (showCategories) {
            LucideIconCategoryTabs(
                categories = categories,
                locale = locale,
                selectedCategory = state.selectedCategory,
                onCategorySelected = state::selectCategory,
                containerBackgroundColor = style.containerBackgroundColor,
                modifier = modifiers.categories,
                style = style.categories,
            )
        }
        LucideIconGrid(
            icons = pagination.items,
            columns = columns,
            iconSize = iconSize,
            iconColor = iconColor,
            strokeWidth = strokeWidth,
            onIconSelected = onIconSelected,
            modifier = modifiers.grid,
            style = style.grid,
        )
        LucideIconPagination(
            currentPage = pagination.currentPage,
            pageCount = pagination.pageCount,
            totalResults = pagination.totalItems,
            onPageChange = state::goToPage,
            modifier = modifiers.pagination,
            style = style.pagination,
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
    pageSize: Int = 48,
    searchLimit: Int = 100,
    style: LucideIconPickerStyle = LucideIconPickerDefaults.style(locale = locale),
    modifiers: LucideIconPickerModifiers = LucideIconPickerDefaults.modifiers(),
) {
    val state = rememberLucideIconPickerState(initialQuery = query)
    if (state.query != query) {
        state.updateQuery(query)
    }
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(style.containerVerticalSpacing),
    ) {
        LucideIconSearchBar(
            query = query,
            onQueryChange = onQueryChange,
            modifier = modifiers.searchBar,
            style = style.searchBar,
        )
        LucideIconPicker(
            state = state,
            modifiers = modifiers,
            registry = registry,
            locale = locale,
            showSearchBar = false,
            pageSize = pageSize,
            searchLimit = searchLimit,
            style = style,
            onIconSelected = { onIconSelected(it.key.value) },
        )
    }
}

internal data class LucideIconPage<T>(
    val items: List<T>,
    val currentPage: Int,
    val pageCount: Int,
    val totalItems: Int,
)

internal fun <T> paginateItems(
    items: List<T>,
    pageSize: Int,
    requestedPage: Int,
): LucideIconPage<T> {
    require(pageSize > 0) { "pageSize must be greater than 0." }

    if (items.isEmpty()) {
        return LucideIconPage(
            items = emptyList(),
            currentPage = 0,
            pageCount = 0,
            totalItems = 0,
        )
    }

    val pageCount = (items.size + pageSize - 1) / pageSize
    val currentPage = requestedPage.coerceIn(0, pageCount - 1)
    val startIndex = currentPage * pageSize
    val endIndex = minOf(startIndex + pageSize, items.size)

    return LucideIconPage(
        items = items.subList(startIndex, endIndex),
        currentPage = currentPage,
        pageCount = pageCount,
        totalItems = items.size,
    )
}
