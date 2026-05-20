plugins {
    alias(libs.plugins.kotlin.jvm)
    application
}

kotlin {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_22)
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(23))
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.release.set(22)
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.1")
    testImplementation(kotlin("test-junit"))
}

application {
    mainClass.set("com.shermant.generator.GeneratorMainKt")
}

val bundledLucideInputDirectory = layout.projectDirectory.dir("src/main/resources/lucide-icons/icons")
val generatedCoreOutputDirectory = layout.projectDirectory.dir("../lucide-core/src/commonMain/kotlin/com/shermant/core/generated")
val generatedCorePackageName = "com.shermant.core.generated"

tasks.register<JavaExec>("generateBundledLucide") {
    group = "generation"
    description = "Generate the committed lucide-core built-ins from the pinned Lucide snapshot."
    classpath = sourceSets.main.get().runtimeClasspath
    mainClass.set(application.mainClass)
    args(
        bundledLucideInputDirectory.asFile.absolutePath,
        generatedCoreOutputDirectory.asFile.absolutePath,
        generatedCorePackageName,
    )
}
