package com.shermant.compose.picker

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shermant.core.model.LucideLocale

object LucideIconPickerDefaults {
    fun style(
        locale: LucideLocale = LucideLocale.En,
        containerPadding: PaddingValues = PaddingValues(16.dp),
        containerVerticalSpacing: androidx.compose.ui.unit.Dp = 12.dp,
        containerBackgroundColor: Color = Color.Unspecified,
        searchBar: LucideIconPickerSearchBarStyle = LucideIconPickerSearchBarStyle(
            height = 56.dp,
            shape = RoundedCornerShape(12.dp),
            backgroundColor = Color.White,
            borderColor = Color(0xFFD1D5DB),
            borderWidth = 1.dp,
            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
            labelText = searchLabelText(locale),
            labelTextStyle = TextStyle(
                color = Color(0xFF6B7280),
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
            ),
            textStyle = TextStyle(
                color = Color(0xFF111827),
                fontSize = 14.sp,
            ),
            cursorColor = Color(0xFF111827),
        ),
        categories: LucideIconPickerCategoryTabsStyle = LucideIconPickerCategoryTabsStyle(
            chipSpacing = 8.dp,
            chipContentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
            chipShape = RoundedCornerShape(999.dp),
            chipBorderWidth = 1.dp,
            unselectedBackgroundColor = Color.White,
            unselectedBorderColor = Color(0xFFD1D5DB),
            unselectedContentColor = Color(0xFF374151),
            selectedBackgroundColor = Color(0xFF111827),
            selectedBorderColor = Color(0xFF111827),
            selectedContentColor = Color.White,
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
            labelTextStyle = TextStyle(
                color = Color(0xFF374151),
                fontSize = 12.sp,
            ),
        ),
    ): LucideIconPickerStyle = LucideIconPickerStyle(
        containerPadding = containerPadding,
        containerVerticalSpacing = containerVerticalSpacing,
        containerBackgroundColor = containerBackgroundColor,
        containerShape = RectangleShape,
        searchBar = searchBar,
        categories = categories,
        grid = grid,
    )

    private fun searchLabelText(locale: LucideLocale): String = when (locale) {
        LucideLocale.En -> "Search icons"
        LucideLocale.Zh -> "搜索图标"
    }

    private fun allLabelText(locale: LucideLocale): String = when (locale) {
        LucideLocale.En -> "All"
        LucideLocale.Zh -> "全部"
    }
}
