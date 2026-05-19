package io.github.lucideicons.kmp.compose.picker

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import io.github.lucideicons.kmp.compose.LucideIcon
import io.github.lucideicons.kmp.core.model.LucideIconMetadata

@Composable
internal fun LucideIconGrid(
    icons: List<LucideIconMetadata>,
    columns: Int,
    iconSize: Dp,
    iconColor: Color,
    strokeWidth: Float,
    onIconSelected: (LucideIconMetadata) -> Unit,
    modifier: Modifier = Modifier,
    style: LucideIconPickerGridStyle,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(style.horizontalSpacing),
        verticalArrangement = Arrangement.spacedBy(style.verticalSpacing),
    ) {
        items(icons, key = { it.key.value }) { metadata ->
            Column(
                modifier = Modifier
                    .clickable { onIconSelected(metadata) }
                    .padding(style.itemPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(style.itemVerticalSpacing),
            ) {
                LucideIcon(
                    name = metadata.key.value,
                    size = iconSize,
                    color = iconColor,
                    strokeWidth = strokeWidth,
                )
                BasicText(
                    text = metadata.displayName,
                    style = style.labelTextStyle,
                )
            }
        }
    }
}
