package com.shermant.lucideiconkmp.sample

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.shermant.lucideiconkmp.sample.screens.CustomIconsDemoScreen
import com.shermant.lucideiconkmp.sample.screens.IconGalleryScreen
import com.shermant.lucideiconkmp.sample.screens.IconPickerDemoScreen

@Composable
fun App() {
    MaterialTheme {
        val selectedIconName = remember { mutableStateOf("activity") }
        Column(
            modifier = Modifier.fillMaxSize().padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            Text(text = "Selected icon: ${selectedIconName.value}")
            IconGalleryScreen(selectedIconName = selectedIconName.value)
            IconPickerDemoScreen(onIconSelected = { selectedIconName.value = it })
            CustomIconsDemoScreen()
        }
    }
}
