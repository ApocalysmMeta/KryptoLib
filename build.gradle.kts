import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.20"
    `maven-publish`
}

group = "dev.crash"
version = "0.1"

repositories {
    mavenCentral()
}

apply(plugin = "maven-publish")

dependencies {
    api("com.fasterxml.jackson.module:jackson-module-kotlin:2.12.4")
    api("org.bouncycastle:bcprov-jdk15to18:1.69")
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