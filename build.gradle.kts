plugins {
    kotlin("jvm") version "1.9.21"
    kotlin("plugin.serialization") version "1.6.0"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    application
}

group = "dev.butter"
version = "1.0"
project.setProperty("mainClassName", "dev.butter.mathbot.MainKt")

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.21")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("net.objecthunter:exp4j:0.4.8")
    implementation("com.google.inject:guice:4.1.0")
    implementation("com.google.inject.extensions:guice-multibindings:4.1.0")
    implementation("com.authzee.kotlinguice4:kotlin-guice:1.3.0")
    implementation("com.authzee.kotlinguice4:kotlin-guice-multibindings:1.3.0")
}

kotlin {
    jvmToolchain(8)
}

sourceSets {
    main {
        kotlin {
            srcDirs("src/main/kotlin")
        }
    }
}

tasks.build {
    dependsOn(tasks.shadowJar)
}

tasks.shadowJar {
    archiveFileName.set("MathBot.jar")
    destinationDirectory.set(File("C:/Users/omarc/Desktop"))
}