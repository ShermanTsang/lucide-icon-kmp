package com.shermant.lucideiconkmp.compose.picker

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp

@Composable
internal fun LucideIconSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    style: LucideIconPickerSearchBarStyle,
) {
    BasicTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = style.height)
            .clip(style.shape)
            .background(style.backgroundColor)
            .then(
                if (style.borderWidth > 0.dp) {
                    Modifier.border(
                        width = style.borderWidth,
                        color = style.borderColor,
                        shape = style.shape,
                    )
                } else {
                    Modifier
                },
            )
            .padding(style.contentPadding),
        textStyle = style.textStyle,
        singleLine = true,
        cursorBrush = SolidColor(style.cursorColor),
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterStart,
            ) {
                if (query.isBlank() && style.labelText.isNotEmpty()) {
                    BasicText(
                        text = style.labelText,
                        style = style.labelTextStyle,
                    )
                }
                innerTextField()
            }
        },
    )
}
