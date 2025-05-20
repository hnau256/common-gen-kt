plugins {
    val kotlinVersion = "2.1.20"
    kotlin("jvm") version kotlinVersion
    id("maven-publish")
}

group = "com.github.hnau256"
version = "1.0.0"

repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

/*dependencies {
    implementation("com.github.hnau256:common-kotlin:1.0.0")
}*/

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
