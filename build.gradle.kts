plugins {
    kotlin("jvm") version "2.3.10"
    application
    // jacoco - disabled for Java 25 compatibility
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
    jvmToolchain(25)
}

tasks.test {
    useJUnitPlatform()
    // Disable JaCoCo for now due to Java 25 compatibility issues
    // finalizedBy(tasks.jacocoTestReport)
}

// JaCoCo configuration - disabled for Java 25 compatibility
/*
tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required = true
        html.required = true
    }
}

jacoco {
    toolVersion = "0.8.11"
}
*/

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

