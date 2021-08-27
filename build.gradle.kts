import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.21"
    `maven-publish`
}

group = "dev.crash"
version = "0.1"

repositories {
    mavenCentral()
    mavenLocal()
}

apply(plugin = "maven-publish")

dependencies {
    api("com.fasterxml.jackson.module:jackson-module-kotlin:2.12.4")
    api("org.bouncycastle:bcprov-jdk15to18:1.69")
    api("org.java-websocket:Java-WebSocket:1.5.2")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.1-native-mt")
    api("org.slf4j:slf4j-api:1.8.0-beta4")
    api("org.slf4j:slf4j-simple:1.8.0-beta4")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "16"
}

publishing {
    publications {
        create<MavenPublication>("maven"){
            groupId = "dev.crash"
            artifactId = "kryptoLib"
            version = "0.1"
        }
    }
}

tasks.withType<PublishToMavenLocal> {
    doLast {
        project.file("/build/libs/KryptoLib-$version.jar").copyTo(file("C:\\Users\\${System.getProperties()["user.name"]}\\.m2\\repository\\dev\\crash\\kryptoLib\\0.1\\KryptoLib-$version.jar"), overwrite = true)
    }
}