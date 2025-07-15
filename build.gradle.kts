plugins {
    val kotlinVersion = "2.2.0"
    kotlin("jvm") version kotlinVersion
    id("maven-publish")
}

group = "com.github.hnau256.common-gen-kt"
version = "1.1.0"

repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

tasks {
    create<Jar>("sourcesJar") {
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
