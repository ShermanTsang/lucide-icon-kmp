package io.github.lucideicons.kmp.core.generated

import io.github.lucideicons.kmp.core.model.IconSourceSet
import io.github.lucideicons.kmp.core.model.LucideIconCategory
import io.github.lucideicons.kmp.core.model.LucideIconKey
import io.github.lucideicons.kmp.core.model.LucideIconMetadata

object LucideGeneratedMetadata {
    val Activity = LucideIconMetadata(
        key = LucideIconKey("activity"),
        displayName = "Activity",
        tags = setOf("pulse", "monitoring", "chart"),
        categories = setOf(LucideIconCategory.General),
        sourceSet = IconSourceSet.BuiltIn,
    )

    val Airplay = LucideIconMetadata(
        key = LucideIconKey("airplay"),
        displayName = "Airplay",
        tags = setOf("device", "screen", "cast"),
        categories = setOf(LucideIconCategory.Devices),
        sourceSet = IconSourceSet.BuiltIn,
    )

    val ArrowRight = LucideIconMetadata(
        key = LucideIconKey("arrow-right"),
        displayName = "Arrow Right",
        tags = setOf("direction", "next", "navigation"),
        categories = setOf(LucideIconCategory.Arrows),
        sourceSet = IconSourceSet.BuiltIn,
    )

    val all: List<LucideIconMetadata> = listOf(Activity, Airplay, ArrowRight)
}
