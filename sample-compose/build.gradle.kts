plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.application)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    androidTarget()
    jvm("desktop")
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            implementation(project(":lucide-core"))
            implementation(project(":lucide-compose"))
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
        }
        androidMain.dependencies {
            implementation("androidx.activity:activity-compose:1.9.3")
        }
    }
}

android {
    namespace = "io.github.lucideicons.kmp.sample"
    compileSdk = libs.versions.android.compile.sdk.get().toInt()

    defaultConfig {
        applicationId = "io.github.lucideicons.kmp.sample"
        minSdk = libs.versions.android.min.sdk.get().toInt()
        targetSdk = libs.versions.android.target.sdk.get().toInt()
        versionCode = 1
        versionName = "1.0.0"
    }
}

compose.desktop {
    application {
        mainClass = "io.github.lucideicons.kmp.sample.MainKt"
    }
}
