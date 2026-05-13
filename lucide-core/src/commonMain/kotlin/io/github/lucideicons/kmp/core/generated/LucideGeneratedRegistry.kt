package io.github.lucideicons.kmp.core.generated

import io.github.lucideicons.kmp.core.generated.icons.activityProvider
import io.github.lucideicons.kmp.core.generated.icons.airplayProvider
import io.github.lucideicons.kmp.core.generated.icons.arrowRightProvider
import io.github.lucideicons.kmp.core.registry.LucideIconCreator
import io.github.lucideicons.kmp.core.registry.MutableIconRegistry
import io.github.lucideicons.kmp.core.registry.create

internal fun registerGeneratedIcons(registry: MutableIconRegistry) {
    registry.register(
        metadata = LucideGeneratedMetadata.Activity,
        creator = LucideIconCreator { activityProvider.create() },
        parameterizedProvider = activityProvider,
    )
    registry.register(
        metadata = LucideGeneratedMetadata.Airplay,
        creator = LucideIconCreator { airplayProvider.create() },
        parameterizedProvider = airplayProvider,
    )
    registry.register(
        metadata = LucideGeneratedMetadata.ArrowRight,
        creator = LucideIconCreator { arrowRightProvider.create() },
        parameterizedProvider = arrowRightProvider,
    )
}
