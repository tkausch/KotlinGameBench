plugins {
    kotlin("jvm") version "2.3.10"
    application
}

group = "li.kausch.kgb"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("li.kausch.kgb.MainKt")
    applicationName = "KotlinGameBench"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(21)
}

tasks.test {
    useJUnitPlatform()
}

// Explicitly configure the run task
tasks.named<JavaExec>("run") {
    standardInput = System.`in`
    args = listOf()
}

// Add a custom task for running the main class
tasks.register<JavaExec>("runMain") {
    group = "application"
    description = "Run the main class"
    classpath = sourceSets.main.get().runtimeClasspath
    mainClass.set("li.kausch.kgb.MainKt")
    standardInput = System.`in`
}

// Task to print classpath for debugging
tasks.register("printClasspath") {
    doLast {
        println(sourceSets.main.get().runtimeClasspath.asPath)
    }
}

