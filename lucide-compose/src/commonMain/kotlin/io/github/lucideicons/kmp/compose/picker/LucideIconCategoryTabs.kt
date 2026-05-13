package io.github.lucideicons.kmp.compose.picker

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.lucideicons.kmp.core.model.LucideIconCategory

@Composable
internal fun LucideIconCategoryTabs(
    categories: List<LucideIconCategory>,
    selectedCategory: LucideIconCategory?,
    onCategorySelected: (LucideIconCategory?) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier.horizontalScroll(rememberScrollState())) {
        FilterChip(
            selected = selectedCategory == null,
            onClick = { onCategorySelected(null) },
            label = { Text("All") },
        )
        categories.forEach { category ->
            FilterChip(
                selected = selectedCategory == category,
                onClick = { onCategorySelected(category) },
                label = { Text(category.name) },
            )
        }
    }
}
