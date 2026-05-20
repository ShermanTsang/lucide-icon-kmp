package com.shermant.compose.picker

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.text.BasicText
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.shermant.core.model.LucideIconCategory
import com.shermant.core.model.LucideLocale
import com.shermant.core.model.displayName

@Composable
internal fun LucideIconCategoryTabs(
    categories: List<LucideIconCategory>,
    locale: LucideLocale,
    selectedCategory: LucideIconCategory?,
    onCategorySelected: (LucideIconCategory?) -> Unit,
    modifier: Modifier = Modifier,
    style: LucideIconPickerCategoryTabsStyle,
) {
    Row(
        modifier = modifier.horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(style.chipSpacing),
    ) {
        CategoryChip(
            label = style.allLabelText,
            selected = selectedCategory == null,
            onClick = { onCategorySelected(null) },
            style = style,
        )
        categories.forEach { category ->
            CategoryChip(
                label = category.displayName(locale),
                selected = selectedCategory == category,
                onClick = { onCategorySelected(category) },
                style = style,
            )
        }
    }
}

@Composable
private fun CategoryChip(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    style: LucideIconPickerCategoryTabsStyle,
) {
    val backgroundColor = if (selected) {
        style.selectedBackgroundColor
    } else {
        style.unselectedBackgroundColor
    }
    val borderColor = if (selected) {
        style.selectedBorderColor
    } else {
        style.unselectedBorderColor
    }
    val textColor = if (selected) {
        style.selectedContentColor
    } else {
        style.unselectedContentColor
    }

    Box(
        modifier = Modifier
            .clip(style.chipShape)
            .background(backgroundColor)
            .border(style.chipBorderWidth, borderColor, style.chipShape)
            .clickable(onClick = onClick)
            .padding(style.chipContentPadding),
    ) {
        BasicText(
            text = label,
            style = style.textStyle.withColor(textColor),
        )
    }
}

private fun TextStyle.withColor(color: Color): TextStyle {
    return copy(color = color)
}
