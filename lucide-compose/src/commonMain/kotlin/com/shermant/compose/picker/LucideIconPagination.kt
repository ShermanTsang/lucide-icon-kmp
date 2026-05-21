package com.shermant.compose.picker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
internal fun LucideIconPagination(
    currentPage: Int,
    pageCount: Int,
    totalResults: Int,
    onPageChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
    style: LucideIconPickerPaginationStyle,
) {
    if (pageCount <= 1) {
        return
    }

    val hasPrevious = currentPage > 0
    val hasNext = currentPage + 1 < pageCount

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(style.horizontalSpacing),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TextButton(
            enabled = hasPrevious,
            onClick = { onPageChange(currentPage - 1) },
        ) {
            BasicText(
                text = style.previousLabel,
                style = style.textStyle.copy(
                    color = if (hasPrevious) style.contentColor else style.disabledContentColor,
                ),
            )
        }
        BasicText(
            text = "${currentPage + 1} / $pageCount ($totalResults)",
            style = style.textStyle.copy(color = style.contentColor),
        )
        TextButton(
            enabled = hasNext,
            onClick = { onPageChange(currentPage + 1) },
        ) {
            BasicText(
                text = style.nextLabel,
                style = style.textStyle.copy(
                    color = if (hasNext) style.contentColor else style.disabledContentColor,
                ),
            )
        }
    }
}
