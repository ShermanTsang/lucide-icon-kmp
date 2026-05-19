rootProject.name = "lucide-icon-kmp"

pluginManagement {
    repositories {
        google()
        maven { url = uri("https://mirrors.cloud.tencent.com/nexus/repository/maven-public/") }
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_PROJECT)
    repositories {
        google()
        maven { url = uri("https://mirrors.cloud.tencent.com/nexus/repository/maven-public/") }
        mavenCentral()
    }
}

include(":lucide-core")
include(":lucide-compose")
include(":lucide-generator")
include(":sample-compose")
