import com.vanniktech.maven.publish.MavenPublishBaseExtension
import org.gradle.api.GradleException
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.plugins.signing.SigningExtension
import java.nio.charset.StandardCharsets
import java.util.Base64
import java.util.Properties

plugins {
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.compose.multiplatform) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.vanniktech.maven.publish) apply false
}

group =
    providers
        .gradleProperty("GROUP")
        .orElse("com.shermant")
        .get()
version =
    providers
        .gradleProperty("VERSION_NAME")
        .orElse("0.1.0-SNAPSHOT")
        .get()

description =
    providers
        .gradleProperty("POM_DESCRIPTION")
        .orElse("Lucide icons for Kotlin Multiplatform and Compose Multiplatform.")
        .get()

val localSecretsFile = rootProject.layout.projectDirectory.file(".secrets").asFile
val localSecrets =
    Properties().apply {
        if (localSecretsFile.isFile) {
            localSecretsFile.inputStream().use(::load)
        }
    }

fun localSecretProvider(propertyName: String) =
    providers.provider {
        localSecrets.getProperty(propertyName)?.trim()?.takeIf(String::isNotBlank)
    }

val publishableLibraryProjects =
    setOf(
        ":lucide-core",
        ":lucide-compose",
    )
val appleVariantLibraryProjects = publishableLibraryProjects
val publishedArtifactIds =
    mapOf(
        ":lucide-core" to "lucide-icon-kmp",
        ":lucide-compose" to "lucide-icon-kmp-compose",
    )
val publishedPomNames =
    mapOf(
        ":lucide-core" to "lucide-icon-kmp",
        ":lucide-compose" to "lucide-icon-kmp-compose",
    )

val mavenCentralUsernameProvider =
    providers
        .gradleProperty("mavenCentralUsername")
        .orElse(localSecretProvider("MAVEN_CENTRAL_USERNAME"))
        .orElse(providers.environmentVariable("ORG_GRADLE_PROJECT_mavenCentralUsername"))
        .orElse(providers.environmentVariable("MAVEN_CENTRAL_USERNAME"))
val mavenCentralPasswordProvider =
    providers
        .gradleProperty("mavenCentralPassword")
        .orElse(localSecretProvider("MAVEN_CENTRAL_PASSWORD"))
        .orElse(providers.environmentVariable("ORG_GRADLE_PROJECT_mavenCentralPassword"))
        .orElse(providers.environmentVariable("MAVEN_CENTRAL_PASSWORD"))
val signingKeyBase64Provider =
    providers
        .gradleProperty("signingInMemoryKeyBase64")
        .orElse(localSecretProvider("SIGNING_KEY_BASE64"))
        .orElse(providers.environmentVariable("SIGNING_KEY_BASE64"))
val signingInMemoryKeyProvider =
    providers
        .gradleProperty("signingInMemoryKey")
        .orElse(localSecretProvider("SIGNING_IN_MEMORY_KEY"))
        .orElse(providers.environmentVariable("ORG_GRADLE_PROJECT_signingInMemoryKey"))
        .orElse(
            signingKeyBase64Provider.map { encodedSigningKey ->
                String(Base64.getDecoder().decode(encodedSigningKey), StandardCharsets.UTF_8)
            },
        )
val signingInMemoryKeyPasswordProvider =
    providers
        .gradleProperty("signingInMemoryKeyPassword")
        .orElse(localSecretProvider("SIGNING_PASSWORD"))
        .orElse(providers.environmentVariable("ORG_GRADLE_PROJECT_signingInMemoryKeyPassword"))
        .orElse(providers.environmentVariable("SIGNING_PASSWORD"))
val pomNameProvider =
    providers
        .gradleProperty("POM_NAME")
        .orElse(rootProject.name)
val pomDescriptionProvider =
    providers
        .gradleProperty("POM_DESCRIPTION")
        .orElse(rootProject.description.toString())
val pomUrlProvider =
    providers
        .gradleProperty("POM_URL")
        .orElse("https://github.com/ShermanTsang/${rootProject.name}")
val pomLicenseNameProvider =
    providers
        .gradleProperty("POM_LICENSE_NAME")
        .orElse("MIT License")
val pomLicenseUrlProvider =
    providers
        .gradleProperty("POM_LICENSE_URL")
        .orElse("https://opensource.org/licenses/MIT")
val pomDeveloperIdProvider =
    providers
        .gradleProperty("POM_DEVELOPER_ID")
        .orElse("maintainer")
val pomDeveloperNameProvider =
    providers
        .gradleProperty("POM_DEVELOPER_NAME")
        .orElse("Project Maintainer")
val pomDeveloperEmailProvider = providers.gradleProperty("POM_DEVELOPER_EMAIL")
val pomDeveloperUrlProvider = providers.gradleProperty("POM_DEVELOPER_URL")
val pomScmUrlProvider =
    providers
        .gradleProperty("POM_SCM_URL")
        .orElse("https://github.com/ShermanTsang/${rootProject.name}")
val pomScmConnectionProvider =
    providers
        .gradleProperty("POM_SCM_CONNECTION")
        .orElse("scm:git:git://github.com/ShermanTsang/lucide-icon-kmp.git")
val pomScmDeveloperConnectionProvider =
    providers
        .gradleProperty("POM_SCM_DEV_CONNECTION")
        .orElse("scm:git:ssh://git@github.com/ShermanTsang/lucide-icon-kmp.git")

fun String?.isConfigured(): Boolean = !this.isNullOrBlank()

fun requireConfigured(propertyName: String, propertyValue: String?) {
    if (propertyValue.isConfigured()) {
        return
    }

    throw GradleException("$propertyName is required for Maven Central publishing.")
}

fun requirePairedConfiguration(propertyName: String, propertyValue: String?, relatedPropertyName: String, relatedPropertyValue: String?) {
    val hasPrimaryValue = propertyValue.isConfigured()
    val hasSecondaryValue = relatedPropertyValue.isConfigured()

    if (hasPrimaryValue == hasSecondaryValue) {
        return
    }

    val missingName =
        if (hasPrimaryValue) {
            relatedPropertyName
        } else {
            propertyName
        }
    val presentName =
        if (hasPrimaryValue) {
            propertyName
        } else {
            relatedPropertyName
        }

    throw GradleException("$missingName is required when $presentName is configured for Maven Central publishing.")
}

fun Project.bridgeExtraPropertyIfMissing(propertyName: String, propertyValue: String?) {
    if (!propertyValue.isConfigured() || findProperty(propertyName) != null) {
        return
    }

    extensions.extraProperties[propertyName] = propertyValue
}

fun currentHostIsMacOs(): Boolean = System.getProperty("os.name").orEmpty().lowercase().contains("mac")

val validateCentralSnapshotConfig =
    tasks.register("validateCentralSnapshotConfig") {
        group = "publishing"
        description = "Validates required configuration for publishing SNAPSHOT versions to Maven Central."

        doLast {
            val versionName = version.toString().trim()
            val mavenCentralUsername = mavenCentralUsernameProvider.orNull?.trim()
            val mavenCentralPassword = mavenCentralPasswordProvider.orNull?.trim()
            val signingInMemoryKey = signingInMemoryKeyProvider.orNull?.trim()
            val signingInMemoryKeyPassword = signingInMemoryKeyPasswordProvider.orNull?.trim()

            if (!versionName.endsWith("-SNAPSHOT")) {
                throw GradleException("publishCentralSnapshot requires VERSION_NAME to end with -SNAPSHOT.")
            }

            requireConfigured(
                propertyName = "mavenCentralUsername or ORG_GRADLE_PROJECT_mavenCentralUsername or MAVEN_CENTRAL_USERNAME",
                propertyValue = mavenCentralUsername,
            )
            requireConfigured(
                propertyName = "mavenCentralPassword or ORG_GRADLE_PROJECT_mavenCentralPassword or MAVEN_CENTRAL_PASSWORD",
                propertyValue = mavenCentralPassword,
            )

            requirePairedConfiguration(
                propertyName = "signingInMemoryKey or signingInMemoryKeyBase64 or ORG_GRADLE_PROJECT_signingInMemoryKey or SIGNING_KEY_BASE64",
                propertyValue = signingInMemoryKey,
                relatedPropertyName = "signingInMemoryKeyPassword or ORG_GRADLE_PROJECT_signingInMemoryKeyPassword or SIGNING_PASSWORD",
                relatedPropertyValue = signingInMemoryKeyPassword,
            )
        }
    }

val validateApplePublicationHost =
    tasks.register("validateApplePublicationHost") {
        group = "publishing"
        description = "Ensures public publishing that includes Apple variants runs on macOS."

        doLast {
            if (appleVariantLibraryProjects.isEmpty() || currentHostIsMacOs()) {
                return@doLast
            }

            val projectList = appleVariantLibraryProjects.joinToString(", ")
            throw GradleException(
                "Public Apple variant publishing must run on macOS. " +
                    "The current host is '${System.getProperty("os.name")}' and the following projects publish iOS variants: $projectList. " +
                    "Use a macOS machine or macOS CI to run publishCentralSnapshot / publishCentralRelease.",
            )
        }
    }

val validatePublishedCoordinates =
    tasks.register("validatePublishedCoordinates") {
        group = "publishing"
        description = "Validates published artifact coordinates before uploading to Maven Central."

        doLast {
            publishableLibraryProjects.forEach { projectPath ->
                val subproject = project(projectPath)
                val publishingExtension = subproject.extensions.findByType(PublishingExtension::class.java)
                    ?: throw GradleException("Publishing extension is missing for $projectPath.")
                val expectedArtifactId = publishedArtifactIds[projectPath]
                    ?: throw GradleException("Expected artifactId is not configured for $projectPath.")
                val internalProjectName = subproject.name
                val publications = publishingExtension.publications.withType(MavenPublication::class.java)

                if (publications.isEmpty()) {
                    throw GradleException("No Maven publications were created for $projectPath.")
                }

                publications.forEach { publication ->
                    val artifactId = publication.artifactId.trim()
                    if (artifactId.isBlank()) {
                        throw GradleException("Publication ${publication.name} in $projectPath has a blank artifactId.")
                    }
                    if (!artifactId.startsWith(expectedArtifactId)) {
                        throw GradleException(
                            "Publication ${publication.name} in $projectPath uses artifactId '$artifactId', " +
                                "but public coordinates must start with '$expectedArtifactId'.",
                        )
                    }
                    if (artifactId.startsWith(internalProjectName)) {
                        throw GradleException(
                            "Publication ${publication.name} in $projectPath uses internal module prefix '$internalProjectName'. " +
                                "Public coordinates must use the published artifact base '$expectedArtifactId'.",
                        )
                    }
                }
            }
        }
    }

val validateCentralReleaseConfig =
    tasks.register("validateCentralReleaseConfig") {
        group = "publishing"
        description = "Validates required configuration for publishing releases through Central Portal."

        doLast {
            val versionName = version.toString().trim()
            val mavenCentralUsername = mavenCentralUsernameProvider.orNull?.trim()
            val mavenCentralPassword = mavenCentralPasswordProvider.orNull?.trim()
            val signingInMemoryKey = signingInMemoryKeyProvider.orNull?.trim()
            val signingInMemoryKeyPassword = signingInMemoryKeyPasswordProvider.orNull?.trim()

            if (versionName.endsWith("-SNAPSHOT")) {
                throw GradleException("publishCentralRelease requires VERSION_NAME without the -SNAPSHOT suffix.")
            }

            requireConfigured(
                propertyName = "mavenCentralUsername or ORG_GRADLE_PROJECT_mavenCentralUsername or MAVEN_CENTRAL_USERNAME",
                propertyValue = mavenCentralUsername,
            )
            requireConfigured(
                propertyName = "mavenCentralPassword or ORG_GRADLE_PROJECT_mavenCentralPassword or MAVEN_CENTRAL_PASSWORD",
                propertyValue = mavenCentralPassword,
            )
            requireConfigured(
                propertyName = "signingInMemoryKey or signingInMemoryKeyBase64 or ORG_GRADLE_PROJECT_signingInMemoryKey or SIGNING_KEY_BASE64",
                propertyValue = signingInMemoryKey,
            )
            requireConfigured(
                propertyName = "signingInMemoryKeyPassword or ORG_GRADLE_PROJECT_signingInMemoryKeyPassword or SIGNING_PASSWORD",
                propertyValue = signingInMemoryKeyPassword,
            )
        }
    }

val publishCentralSnapshot =
    tasks.register("publishCentralSnapshot") {
        group = "publishing"
        description = "Publishes all library modules as SNAPSHOT artifacts to Maven Central snapshots."
        dependsOn(validateCentralSnapshotConfig)
        dependsOn(validateApplePublicationHost)
        dependsOn(validatePublishedCoordinates)
        dependsOn(publishableLibraryProjects.map { "$it:publishToMavenCentral" })
    }

val publishCentralRelease =
    tasks.register("publishCentralRelease") {
        group = "publishing"
        description = "Publishes and releases all library modules through the Central Portal."
        dependsOn(validateCentralReleaseConfig)
        dependsOn(validateApplePublicationHost)
        dependsOn(validatePublishedCoordinates)
        dependsOn(publishableLibraryProjects.map { "$it:publishAndReleaseToMavenCentral" })
    }

subprojects {
    group = rootProject.group
    version = rootProject.version
    val publishedArtifactId = publishedArtifactIds[path]
    val publishedPomName = publishedPomNames[path]

    if (path !in publishableLibraryProjects) {
        return@subprojects
    }

    bridgeExtraPropertyIfMissing("mavenCentralUsername", mavenCentralUsernameProvider.orNull?.trim())
    bridgeExtraPropertyIfMissing("mavenCentralPassword", mavenCentralPasswordProvider.orNull?.trim())
    bridgeExtraPropertyIfMissing("signingInMemoryKey", signingInMemoryKeyProvider.orNull?.trim())
    bridgeExtraPropertyIfMissing("signingInMemoryKeyPassword", signingInMemoryKeyPasswordProvider.orNull?.trim())

    apply(plugin = "com.vanniktech.maven.publish")

    extensions.configure<SigningExtension> {
        val signingInMemoryKey = signingInMemoryKeyProvider.orNull?.trim()
        val signingInMemoryKeyPassword = signingInMemoryKeyPasswordProvider.orNull?.trim()

        if (signingInMemoryKey.isConfigured() && signingInMemoryKeyPassword.isConfigured()) {
            useInMemoryPgpKeys(signingInMemoryKey, signingInMemoryKeyPassword)
        }
    }

    extensions.configure<MavenPublishBaseExtension> {
        coordinates(rootProject.group.toString(), publishedArtifactId ?: project.name, rootProject.version.toString())

        pom {
            name.set(publishedPomName ?: pomNameProvider.get())
            description.set(pomDescriptionProvider)
            url.set(pomUrlProvider)

            licenses {
                license {
                    name.set(pomLicenseNameProvider)
                    url.set(pomLicenseUrlProvider)
                }
            }

            developers {
                developer {
                    id.set(pomDeveloperIdProvider)
                    name.set(pomDeveloperNameProvider)
                    pomDeveloperEmailProvider.orNull
                        ?.takeIf(String::isNotBlank)
                        ?.let(email::set)
                    pomDeveloperUrlProvider.orNull
                        ?.takeIf(String::isNotBlank)
                        ?.let(url::set)
                }
            }

            scm {
                url.set(pomScmUrlProvider)
                connection.set(pomScmConnectionProvider)
                developerConnection.set(pomScmDeveloperConnectionProvider)
            }
        }
    }
}
