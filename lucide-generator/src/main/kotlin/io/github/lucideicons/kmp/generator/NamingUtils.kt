package io.github.lucideicons.kmp.generator

internal fun String.toDisplayName(): String =
    split('-', '_')
        .filter { it.isNotBlank() }
        .joinToString(" ") { token ->
            token.replaceFirstChar { character ->
                if (character.isLowerCase()) {
                    character.titlecase()
                } else {
                    character.toString()
                }
            }
        }

internal fun String.toPascalCaseIdentifier(suffix: String = ""): String {
    val base = split('-', '_')
        .filter { it.isNotBlank() }
        .joinToString("") { token ->
            token.replaceFirstChar { character ->
                if (character.isLowerCase()) {
                    character.titlecase()
                } else {
                    character.toString()
                }
            }
        }
        .ifBlank { "LucideIcon" }

    val prefixedBase = if (base.first().isDigit()) {
        "Icon$base"
    } else {
        base
    }

    return prefixedBase + suffix
}

internal fun String.toEnumIdentifier(): String = toPascalCaseIdentifier()

internal fun String.toCategoryDisplayName(): String =
    split('-', '_')
        .filter { it.isNotBlank() }
        .joinToString(" ") { token ->
            token.replaceFirstChar { character ->
                if (character.isLowerCase()) {
                    character.titlecase()
                } else {
                    character.toString()
                }
            }
        }

