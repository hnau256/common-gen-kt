import org.gradle.kotlin.dsl.register
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    val kotlinVersion = "2.3.0"
    kotlin("jvm") version kotlinVersion
    id("maven-publish")
}

group = "com.github.hnau256.common-gen-kt"
version = "1.6.0"

repositories {
    mavenCentral()
    maven("https://jitpack.io")
    google()
    mavenLocal()
}

dependencies {
    implementation("com.google.devtools.ksp:symbol-processing-api:2.3.3")
    implementation("io.arrow-kt:arrow-core:2.2.1.1")
    implementation("com.github.hnau256.common-kotlin:common-kotlin:1.7.0")
    implementation("com.squareup:kotlinpoet-ksp:1.15.0")
}

kotlin {
    jvmToolchain(17)
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
    }
}

tasks {
    register<Jar>("sourcesJar") {
        archiveClassifier.set("sources")
        from(sourceSets.main.get().allSource)
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group as String
            artifactId = project.name
            version = project.version as String
            from(components["java"])
            artifact(tasks["sourcesJar"])
        }
    }
}
