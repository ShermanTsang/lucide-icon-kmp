package com.shermant.lucideiconkmp.compose.picker

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.foundation.text.BasicText
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import com.shermant.lucideiconkmp.core.model.LucideIconCategory
import com.shermant.lucideiconkmp.core.model.LucideLocale
import com.shermant.lucideiconkmp.core.model.displayName

@Composable
internal fun LucideIconCategoryTabs(
    categories: List<LucideIconCategory>,
    locale: LucideLocale,
    selectedCategory: LucideIconCategory?,
    onCategorySelected: (LucideIconCategory?) -> Unit,
    containerBackgroundColor: Color,
    modifier: Modifier = Modifier,
    style: LucideIconPickerCategoryTabsStyle,
) {
    val scrollState = rememberScrollState()
    val overlayColor = resolveOverlayColor(
        style = style,
        containerBackgroundColor = containerBackgroundColor,
    )
    val overlayAlpha = style.edgeOverlayMaxAlpha.coerceIn(0f, 1f)
    val showLeftOverlay = overlayColor != null &&
        style.edgeOverlayWidth > Dp.Hairline &&
        overlayAlpha > 0f &&
        scrollState.value > 0
    val showRightOverlay = overlayColor != null &&
        style.edgeOverlayWidth > Dp.Hairline &&
        overlayAlpha > 0f &&
        scrollState.value < scrollState.maxValue

    Box(modifier = modifier) {
        Row(
            modifier = Modifier.horizontalScroll(scrollState),
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

        if (showLeftOverlay) {
            EdgeOverlay(
                modifier = Modifier.align(Alignment.CenterStart),
                width = style.edgeOverlayWidth,
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        overlayColor.copy(alpha = overlayAlpha),
                        overlayColor.copy(alpha = 0f),
                    ),
                ),
            )
        }
        if (showRightOverlay) {
            EdgeOverlay(
                modifier = Modifier.align(Alignment.CenterEnd),
                width = style.edgeOverlayWidth,
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        overlayColor.copy(alpha = 0f),
                        overlayColor.copy(alpha = overlayAlpha),
                    ),
                ),
            )
        }
    }
}

@Composable
private fun EdgeOverlay(
    modifier: Modifier,
    width: Dp,
    brush: Brush,
) {
    Box(
        modifier = modifier
            .fillMaxHeight()
            .width(width)
            .background(brush = brush),
    )
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

internal fun resolveOverlayColor(
    style: LucideIconPickerCategoryTabsStyle,
    containerBackgroundColor: Color,
): Color? {
    return when {
        style.edgeOverlayColor != Color.Unspecified -> style.edgeOverlayColor
        containerBackgroundColor != Color.Unspecified -> containerBackgroundColor
        else -> null
    }
}
