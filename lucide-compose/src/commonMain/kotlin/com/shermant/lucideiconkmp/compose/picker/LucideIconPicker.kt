package com.shermant.lucideiconkmp.compose.picker

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
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

    val resolvedStyle = resolvePickerStyle(style)
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
                        .clip(resolvedStyle.containerShape)
                        .background(resolvedStyle.containerBackgroundColor)
                } else {
                    Modifier
                },
            )
            .padding(resolvedStyle.containerPadding),
        verticalArrangement = Arrangement.spacedBy(resolvedStyle.containerVerticalSpacing),
    ) {
        if (showSearchBar) {
            LucideIconSearchBar(
                query = state.query,
                onQueryChange = state::updateQuery,
                modifier = modifiers.searchBar,
                style = resolvedStyle.searchBar,
            )
        }
        if (showCategories) {
            LucideIconCategoryTabs(
                categories = categories,
                locale = locale,
                selectedCategory = state.selectedCategory,
                onCategorySelected = state::selectCategory,
                containerBackgroundColor = resolvedStyle.containerBackgroundColor,
                modifier = modifiers.categories,
                style = resolvedStyle.categories,
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
            style = resolvedStyle.grid,
        )
        LucideIconPagination(
            currentPage = pagination.currentPage,
            pageCount = pagination.pageCount,
            totalResults = pagination.totalItems,
            onPageChange = state::goToPage,
            modifier = modifiers.pagination,
            style = resolvedStyle.pagination,
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
    val resolvedStyle = resolvePickerStyle(style)
    if (state.query != query) {
        state.updateQuery(query)
    }
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(resolvedStyle.containerVerticalSpacing),
    ) {
        LucideIconSearchBar(
            query = query,
            onQueryChange = onQueryChange,
            modifier = modifiers.searchBar,
            style = resolvedStyle.searchBar,
        )
        LucideIconPicker(
            state = state,
            modifiers = modifiers,
            registry = registry,
            locale = locale,
            showSearchBar = false,
            pageSize = pageSize,
            searchLimit = searchLimit,
            style = resolvedStyle,
            onIconSelected = { onIconSelected(it.key.value) },
        )
    }
}

@Composable
private fun resolvePickerStyle(style: LucideIconPickerStyle): LucideIconPickerStyle {
    val isDark = isSystemInDarkTheme()
    val sharedBorderColor = resolveSharedBorderColor(style, isDark)
    val sharedShape = style.sharedCornerRadius?.let(::RoundedCornerShape)

    return style.copy(
        searchBar = style.searchBar.copy(
            shape = sharedShape ?: style.searchBar.shape,
            borderColor = style.searchBar.borderColor.orElse(sharedBorderColor),
            labelTextStyle = style.searchBar.labelTextStyle.withFallbackColor(
                if (isDark) Color(0xFFCBD5E1) else Color(0xFF6B7280),
            ),
            textStyle = style.searchBar.textStyle.withFallbackColor(
                if (isDark) Color(0xFFF9FAFB) else Color(0xFF111827),
            ),
            cursorColor = style.searchBar.cursorColor.orElse(
                if (isDark) Color(0xFFF9FAFB) else Color(0xFF111827),
            ),
        ),
        categories = style.categories.copy(
            chipShape = sharedShape ?: style.categories.chipShape,
            unselectedBorderColor = style.categories.unselectedBorderColor.orElse(sharedBorderColor),
            selectedBorderColor = style.categories.selectedBorderColor.orElse(sharedBorderColor),
            unselectedContentColor = style.categories.unselectedContentColor.orElse(
                if (isDark) Color(0xFFE5E7EB) else Color(0xFF374151),
            ),
            selectedContentColor = style.categories.selectedContentColor.orElse(
                if (isDark) Color(0xFFF9FAFB) else Color(0xFF111827),
            ),
            textStyle = style.categories.textStyle.withFallbackColor(
                if (isDark) Color(0xFFE5E7EB) else Color(0xFF374151),
            ),
        ),
        pagination = style.pagination.copy(
            textStyle = style.pagination.textStyle.withFallbackColor(
                if (isDark) Color(0xFFE5E7EB) else Color(0xFF374151),
            ),
            contentColor = style.pagination.contentColor.orElse(
                if (isDark) Color(0xFFF9FAFB) else Color(0xFF111827),
            ),
            disabledContentColor = style.pagination.disabledContentColor.orElse(
                if (isDark) Color(0xFF9CA3AF) else Color(0xFF9CA3AF),
            ),
        ),
    )
}

private fun resolveSharedBorderColor(
    style: LucideIconPickerStyle,
    isDark: Boolean,
): Color {
    val preferred = if (isDark) style.sharedBorderColorDark else style.sharedBorderColorLight
    val fallback = if (isDark) style.sharedBorderColorLight else style.sharedBorderColorDark
    return preferred.orElse(fallback)
}

private fun Color.orElse(fallback: Color): Color {
    return if (this != Color.Unspecified) this else fallback
}

private fun TextStyle.withFallbackColor(color: Color): TextStyle {
    return if (this.color != Color.Unspecified) this else copy(color = color)
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
