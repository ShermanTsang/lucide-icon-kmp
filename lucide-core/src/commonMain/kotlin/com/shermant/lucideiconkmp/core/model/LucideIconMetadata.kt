package com.shermant.lucideiconkmp.core.model

data class LucideIconMetadata(
    val key: LucideIconKey,
    val displayName: String = key.value,
    val tags: Set<String> = emptySet(),
    val zhDisplayName: String? = null,
    val zhTags: Set<String> = emptySet(),
    val categories: Set<LucideIconCategory> = emptySet(),
    val defaultStrokeWidth: Float = 2f,
    val sourceSet: IconSourceSet = IconSourceSet.BuiltIn,
) {
    fun displayName(locale: LucideLocale): String = when (locale) {
        LucideLocale.En -> displayName
        LucideLocale.Zh -> zhDisplayName ?: displayName
    }

    fun localizedTags(locale: LucideLocale): Set<String> = when (locale) {
        LucideLocale.En -> tags
        LucideLocale.Zh -> if (zhTags.isEmpty()) tags else zhTags
    }

    fun searchTerms(locale: LucideLocale): List<String> = buildList {
        add(key.value)
        add(displayName)
        addAll(tags)
        if (locale == LucideLocale.Zh) {
            zhDisplayName?.let(::add)
            addAll(zhTags)
        }
    }

    fun allSearchTerms(): List<String> = buildList {
        add(key.value)
        add(displayName)
        addAll(tags)
        zhDisplayName?.let(::add)
        addAll(zhTags)
    }
}
