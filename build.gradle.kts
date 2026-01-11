import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import io.papermc.paperweight.userdev.ReobfArtifactConfiguration

group = "me.iris"
version = "${property("version")}"

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.shadow)
    alias(libs.plugins.paper.userdev)
    `maven-publish`
}

paperweight.reobfArtifactConfiguration = ReobfArtifactConfiguration.MOJANG_PRODUCTION

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://oss.sonatype.org/content/groups/public/")
    maven("https://maven.noxcrew.com/public")
    maven("https://repo.codemc.org/repository/maven-public/")
    maven("https://repo.viaversion.com")
    maven("https://maven.enginehub.org/repo/")
}

dependencies {
    paperweight.paperDevBundle("1.21.11-R0.1-SNAPSHOT")
    implementation(libs.glowingentities)
    implementation(libs.bundles.noxesium)
    implementation(libs.viaversion)
    implementation(libs.prtree)
    implementation("org.jetbrains.kotlin:kotlin-stdlib")

}

kotlin {
    jvmToolchain(21)
}

tasks.build {
    dependsOn("shadowJar")
}

tasks.processResources {
    val props = mapOf("version" to version)
    inputs.properties(props)
    filteringCharset = "UTF-8"
    filesMatching("plugin.yml") {
        expand(props)
    }
}

tasks {
    withType<KotlinCompile> {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_21)
        }
    }
}

java {
    withJavadocJar()
    withSourcesJar()
}

tasks.withType<ShadowJar> {
    manifest {
        attributes["paperweight-mappings-namespace"] = "mojang"
    }
}

tasks.withType<Jar> {
    // Otherwise you'll get a "No main manifest attribute" error
    manifest {
        attributes["Main-Class"] = "me.iris.noxesiumapi.NoxesiumAPIPlugin"
        attributes["paperweight-mappings-namespace"] = "mojang"
    }

    // To avoid the duplicate handling strategy error
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    // To add all of the dependencies
    from(sourceSets.main.get().output)

    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
    })
}

publishing {
    repositories {
        maven {
            name = "astrofoxRepository"
            url = uri("https://maven.femboys.tech/releases")
            credentials(PasswordCredentials::class)
            authentication {
                create<BasicAuthentication>("basic")
            }
        }
    }
    publications {
        create<MavenPublication>("maven") {
            groupId = "me.iris"
            artifactId = "noxesiumapi"
            version = "${property("version")}"
            from(components["java"])
        }
    }
}
