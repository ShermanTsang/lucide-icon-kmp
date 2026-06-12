package com.shermant.compose.picker

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class LucideIconPickerModifiers(
    val searchBar: Modifier = Modifier,
    val categories: Modifier = Modifier,
    val grid: Modifier = Modifier,
    val pagination: Modifier = Modifier,
)

data class LucideIconPickerStyle(
    val containerPadding: PaddingValues = PaddingValues(0.dp),
    val containerVerticalSpacing: Dp = 0.dp,
    val containerBackgroundColor: Color = Color.Unspecified,
    val containerShape: Shape,
    val searchBar: LucideIconPickerSearchBarStyle,
    val categories: LucideIconPickerCategoryTabsStyle,
    val grid: LucideIconPickerGridStyle,
    val pagination: LucideIconPickerPaginationStyle,
)

data class LucideIconPickerSearchBarStyle(
    val height: Dp = 0.dp,
    val shape: Shape,
    val backgroundColor: Color = Color.Unspecified,
    val borderColor: Color = Color.Unspecified,
    val borderWidth: Dp = 0.dp,
    val contentPadding: PaddingValues = PaddingValues(0.dp),
    val labelText: String = "",
    val labelTextStyle: TextStyle = TextStyle.Default,
    val textStyle: TextStyle = TextStyle.Default,
    val cursorColor: Color = Color.Unspecified,
)

data class LucideIconPickerCategoryTabsStyle(
    val chipSpacing: Dp = 0.dp,
    val chipContentPadding: PaddingValues = PaddingValues(0.dp),
    val chipShape: Shape,
    val chipBorderWidth: Dp = 0.dp,
    val edgeOverlayWidth: Dp = 0.dp,
    val edgeOverlayColor: Color = Color.Unspecified,
    val edgeOverlayMaxAlpha: Float = 0f,
    val unselectedBackgroundColor: Color = Color.Unspecified,
    val unselectedBorderColor: Color = Color.Unspecified,
    val unselectedContentColor: Color = Color.Unspecified,
    val selectedBackgroundColor: Color = Color.Unspecified,
    val selectedBorderColor: Color = Color.Unspecified,
    val selectedContentColor: Color = Color.Unspecified,
    val allLabelText: String = "",
    val textStyle: TextStyle = TextStyle.Default,
)

data class LucideIconPickerGridStyle(
    val horizontalSpacing: Dp = 0.dp,
    val verticalSpacing: Dp = 0.dp,
    val itemPadding: PaddingValues = PaddingValues(0.dp),
    val itemVerticalSpacing: Dp = 0.dp,
    val showLabel: Boolean = false,
    val labelTextStyle: TextStyle = TextStyle.Default,
)

data class LucideIconPickerPaginationStyle(
    val horizontalSpacing: Dp = 0.dp,
    val previousLabel: String = "",
    val nextLabel: String = "",
    val textStyle: TextStyle = TextStyle.Default,
    val contentColor: Color = Color.Unspecified,
    val disabledContentColor: Color = Color.Unspecified,
)
