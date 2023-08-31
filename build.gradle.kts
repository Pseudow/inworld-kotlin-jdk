plugins {
    kotlin("jvm") version("1.9.10")
    kotlin("plugin.serialization") version("1.9.10")
    `maven-publish`
}

group = "net.pseudow.inworld.sdk"
version = "1.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
    implementation("com.github.kittinunf.fuel:fuel:2.3.1")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

java {
    withSourcesJar()
    withJavadocJar()
}

kotlin {
    jvmToolchain(17)
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group as String
            artifactId = project.name as String
            version = project.version as String

            pom {
                name.set(project.name)
                description.set("Inworld SDK used in Kotlin/Java platform")

                developers {
                    developer {
                        id.set("pseudow")
                        name.set("Nathan OGUETON")
                        url.set("https://github.com/Pseudow")
                    }
                }
            }

            from(components["java"])
        }
    }
}