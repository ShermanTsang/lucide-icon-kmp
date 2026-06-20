package com.shermant.lucideiconkmp.core.registry

import androidx.compose.ui.graphics.vector.ImageVector

fun interface LucideIconCreator {
    fun create(): ImageVector
}
