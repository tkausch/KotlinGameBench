plugins {
    kotlin("multiplatform") version "2.3.10"
    id("maven-publish")
}

group = "li.kausch.kgb"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(21)

    // JVM target
    jvm()

    // JavaScript target
    js {
        browser()
        nodejs()
    }

    // iOS targets (ARM only)
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain {
            dependencies {
            }
        }

        commonTest {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        jvmMain {
            dependencies {
            }
        }

        jvmTest {
            dependencies {
                implementation(kotlin("test"))
                implementation("org.junit.jupiter:junit-jupiter-engine:5.10.0")
                implementation("org.junit.jupiter:junit-jupiter-api:5.10.0")
            }
        }

        jsMain {
            dependencies {
            }
        }

        jsTest {
            dependencies {
            }
        }

        val iosMain by creating {
            dependsOn(commonMain.get())
        }

        val iosTest by creating {
            dependsOn(commonTest.get())
        }

        iosArm64Main.get().dependsOn(iosMain)
        iosArm64Test.get().dependsOn(iosTest)

        iosSimulatorArm64Main.get().dependsOn(iosMain)
        iosSimulatorArm64Test.get().dependsOn(iosTest)
    }
}

// Create a run task for the JVM application
tasks.register<JavaExec>("run") {
    group = "application"
    description = "Run the Connect Four game"
    classpath = kotlin.targets["jvm"].compilations["main"].output.classesDirs + configurations.named("jvmRuntimeClasspath").get()
    mainClass.set("li.kausch.kgb.MainKt")
    standardInput = System.`in`
}

// Configure JVM test task to use JUnit5
tasks.named<Test>("jvmTest") {
    useJUnitPlatform()
}

// Publish configuration
publishing {
    repositories {
        mavenLocal()
    }
}
