import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.plugins.signing.SigningExtension
import java.nio.charset.StandardCharsets
import java.util.Base64

plugins {
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.compose.multiplatform) apply false
    alias(libs.plugins.compose.compiler) apply false
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

val publishableLibraryProjects =
    setOf(
        ":lucide-core",
        ":lucide-compose",
    )
val publishedArtifactIds =
    mapOf(
        ":lucide-core" to "lucide-icon-kmp",
        ":lucide-compose" to "lucide-icon-kmp-compose",
    )

val repositoryUrlProvider =
    providers
        .gradleProperty("mavenRepositoryUrl")
        .orElse(providers.environmentVariable("MAVEN_REPOSITORY_URL"))
val repositoryUsernameProvider =
    providers
        .gradleProperty("mavenUsername")
        .orElse(providers.environmentVariable("MAVEN_USERNAME"))
val repositoryPasswordProvider =
    providers
        .gradleProperty("mavenPassword")
        .orElse(providers.environmentVariable("MAVEN_PASSWORD"))
val signingKeyBase64Provider =
    providers
        .gradleProperty("signingInMemoryKeyBase64")
        .orElse(providers.environmentVariable("SIGNING_KEY_BASE64"))
val signingPasswordProvider =
    providers
        .gradleProperty("signingInMemoryKeyPassword")
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
val pomScmUrlProvider =
    providers
        .gradleProperty("POM_SCM_URL")
        .orElse("https://github.com/ShermanTsang/${rootProject.name}")

subprojects {
    group = rootProject.group
    version = rootProject.version
    val publishedArtifactId = publishedArtifactIds[path]

    if (path !in publishableLibraryProjects) {
        return@subprojects
    }

    apply(plugin = "maven-publish")
    apply(plugin = "signing")

    extensions.configure<PublishingExtension> {
        repositories {
            mavenLocal()

            val repositoryUrl = repositoryUrlProvider.orNull
            if (!repositoryUrl.isNullOrBlank()) {
                maven {
                    name = "Remote"
                    url = uri(repositoryUrl)

                    val repositoryUsername = repositoryUsernameProvider.orNull
                    val repositoryPassword = repositoryPasswordProvider.orNull
                    if (!repositoryUsername.isNullOrBlank() || !repositoryPassword.isNullOrBlank()) {
                        credentials {
                            username = repositoryUsername
                            password = repositoryPassword
                        }
                    }
                }
            }
        }

        publications.withType<MavenPublication>().configureEach {
            publishedArtifactId?.let { artifactId = it }

            pom {
                name.set(pomNameProvider)
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
                    }
                }

                scm {
                    url.set(pomScmUrlProvider)
                }
            }
        }
    }

    val signingKeyBase64 = signingKeyBase64Provider.orNull
    val signingPassword = signingPasswordProvider.orNull
    if (!signingKeyBase64.isNullOrBlank() && !signingPassword.isNullOrBlank()) {
        val signingKey =
            String(Base64.getDecoder().decode(signingKeyBase64), StandardCharsets.UTF_8)
        extensions.configure<SigningExtension> {
            useInMemoryPgpKeys(signingKey, signingPassword)
            sign(extensions.getByType<PublishingExtension>().publications)
        }
    }
}
