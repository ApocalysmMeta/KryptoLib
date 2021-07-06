import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.20"
    `maven-publish`
}

group = "de.crash"
version = "0.1"

repositories {
    mavenCentral()
}

apply(plugin = "maven-publish")

dependencies {
    api("com.fasterxml.jackson.module:jackson-module-kotlin:2.12.3")
    api("org.bouncycastle:bcprov-jdk15to18:1.69")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "16"
}

publishing {
    publications {
        create<MavenPublication>("maven"){
            groupId = "de.crash"
            artifactId = "kryptoLib"
            version = "0.1"
        }
    }
}