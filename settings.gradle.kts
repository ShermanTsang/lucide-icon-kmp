rootProject.name = "lucide-icon-kotlin-kmp"

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

include(":lucide-core")
include(":lucide-compose")
include(":lucide-generator")
include(":sample-compose")
