package com.shermant.lucideiconkmp.compose.picker

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shermant.lucideiconkmp.core.model.LucideLocale

object LucideIconPickerDefaults {
    fun modifiers(
        searchBar: Modifier = Modifier,
        categories: Modifier = Modifier,
        grid: Modifier = Modifier,
        pagination: Modifier = Modifier,
    ): LucideIconPickerModifiers = LucideIconPickerModifiers(
        searchBar = searchBar,
        categories = categories,
        grid = grid,
        pagination = pagination,
    )

    fun style(
        locale: LucideLocale = LucideLocale.En,
        containerPadding: PaddingValues = PaddingValues(16.dp),
        containerVerticalSpacing: androidx.compose.ui.unit.Dp = 12.dp,
        containerBackgroundColor: Color = Color.Unspecified,
        sharedCornerRadius: androidx.compose.ui.unit.Dp? = 12.dp,
        sharedBorderColorLight: Color = Color(0xFFD1D5DB),
        sharedBorderColorDark: Color = Color(0xFF9CA3AF),
        searchBar: LucideIconPickerSearchBarStyle = LucideIconPickerSearchBarStyle(
            height = 56.dp,
            shape = RoundedCornerShape(sharedCornerRadius ?: 12.dp),
            backgroundColor = Color.Transparent,
            borderColor = Color.Unspecified,
            borderWidth = 1.dp,
            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
            labelText = searchLabelText(locale),
            labelTextStyle = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
            ),
            textStyle = TextStyle(
                fontSize = 14.sp,
            ),
            cursorColor = Color.Unspecified,
        ),
        categories: LucideIconPickerCategoryTabsStyle = LucideIconPickerCategoryTabsStyle(
            chipSpacing = 8.dp,
            chipContentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
            chipShape = RoundedCornerShape(sharedCornerRadius ?: 12.dp),
            chipBorderWidth = 1.dp,
            edgeOverlayWidth = 24.dp,
            edgeOverlayColor = Color.Unspecified,
            edgeOverlayMaxAlpha = 0.92f,
            unselectedBackgroundColor = Color.Transparent,
            unselectedBorderColor = Color.Unspecified,
            unselectedContentColor = Color.Unspecified,
            selectedBackgroundColor = Color.Transparent,
            selectedBorderColor = Color.Unspecified,
            selectedContentColor = Color.Unspecified,
            allLabelText = allLabelText(locale),
            textStyle = TextStyle(
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
            ),
        ),
        grid: LucideIconPickerGridStyle = LucideIconPickerGridStyle(
            horizontalSpacing = 12.dp,
            verticalSpacing = 12.dp,
            itemPadding = PaddingValues(8.dp),
            itemVerticalSpacing = 6.dp,
        ),
        pagination: LucideIconPickerPaginationStyle = LucideIconPickerPaginationStyle(
            horizontalSpacing = 12.dp,
            previousLabel = previousLabelText(locale),
            nextLabel = nextLabelText(locale),
            textStyle = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
            ),
            contentColor = Color.Unspecified,
            disabledContentColor = Color.Unspecified,
        ),
    ): LucideIconPickerStyle = LucideIconPickerStyle(
        containerPadding = containerPadding,
        containerVerticalSpacing = containerVerticalSpacing,
        containerBackgroundColor = containerBackgroundColor,
        sharedCornerRadius = sharedCornerRadius,
        sharedBorderColorLight = sharedBorderColorLight,
        sharedBorderColorDark = sharedBorderColorDark,
        containerShape = RectangleShape,
        searchBar = searchBar,
        categories = categories,
        grid = grid,
        pagination = pagination,
    )

    private fun searchLabelText(locale: LucideLocale): String = when (locale) {
        LucideLocale.En -> "Search icons"
        LucideLocale.Zh -> "搜索图标"
    }

    private fun allLabelText(locale: LucideLocale): String = when (locale) {
        LucideLocale.En -> "All"
        LucideLocale.Zh -> "全部"
    }

    private fun previousLabelText(locale: LucideLocale): String = when (locale) {
        LucideLocale.En -> "Previous"
        LucideLocale.Zh -> "上一页"
    }

    private fun nextLabelText(locale: LucideLocale): String = when (locale) {
        LucideLocale.En -> "Next"
        LucideLocale.Zh -> "下一页"
    }
}
