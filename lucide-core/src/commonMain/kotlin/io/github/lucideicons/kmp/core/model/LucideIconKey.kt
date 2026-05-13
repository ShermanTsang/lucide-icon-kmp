package io.github.lucideicons.kmp.core.model

@JvmInline
value class LucideIconKey(val value: String) {
    init {
        require(value.isNotBlank()) { "Icon name must not be blank." }
    }

    override fun toString(): String = value
}
