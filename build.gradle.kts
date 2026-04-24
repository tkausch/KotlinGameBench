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

    // Native target for macOS ARM64
    macosArm64("mac") {
        binaries {
            executable {
                entryPoint = "li.kausch.kgb.main"
            }
            framework {
                baseName = "KotlinGameBench"
            }
        }
    }

    // iOS targets for xcframework
    iosArm64("ios") {
        binaries {
            framework {
                baseName = "KotlinGameBench"
                isStatic = true
            }
        }
    }

    iosSimulatorArm64("iosSimulator") {
        binaries {
            framework {
                baseName = "KotlinGameBench"
                isStatic = true
            }
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
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

        val macMain by getting {
            dependencies {
            }
        }

        val iosMain by getting {
            dependencies {
            }
        }

        val iosSimulatorMain by getting {
            dependencies {
            }
        }
    }
}

// Create a run task for the JVM application
tasks.register<JavaExec>("run") {
    group = "application"
    description = "Run the Connect Four game"
    dependsOn("jvmMainClasses")
    classpath = kotlin.targets["jvm"].compilations["main"].output.classesDirs + configurations.named("jvmRuntimeClasspath").get()
    mainClass.set("li.kausch.kgb.MainKt")
    standardInput = System.`in`
}

// Configure JVM test task to use JUnit5
// tasks.named<Test>("jvmTest") {
//     useJUnitPlatform()
// }

// Task to build xcframework components
tasks.register("buildXCFramework") {
    group = "build"
    description = "Build XCFramework for iOS and macOS"

    dependsOn(
        "linkReleaseFrameworkIos",
        "linkReleaseFrameworkIosSimulator",
        "linkReleaseFrameworkMac"
    )

    doLast {
        println("\n✅ XCFramework components built successfully!")
        println("\n📍 Framework locations:")
        println("  iOS ARM64:        build/bin/ios/releaseFramework/KotlinGameBench.framework")
        println("  iOS Simulator:    build/bin/iosSimulator/releaseFramework/KotlinGameBench.framework")
        println("  macOS ARM64:      build/bin/mac/releaseFramework/KotlinGameBench.framework")
        println("\n📦 To create the xcframework bundle, run:")
        println("  ./scripts/create-xcframework.sh")
        println("\nor use the Gradle wrapper:")
        println("  ./gradlew createXCFramework")
    }
}

// Complete task: Build and create xcframework
tasks.register<Exec>("createXCFramework") {
    group = "build"
    description = "Build and create complete XCFramework (iOS, iOS Simulator, macOS)"
    dependsOn("buildXCFramework")

    val projectDir = projectDir.absolutePath
    val iosFramework = "$projectDir/build/bin/ios/releaseFramework/KotlinGameBench.framework"
    val iosSimFramework = "$projectDir/build/bin/iosSimulator/releaseFramework/KotlinGameBench.framework"
    val macFramework = "$projectDir/build/bin/mac/releaseFramework/KotlinGameBench.framework"
    val output = "$projectDir/KotlinGameBench.xcframework"

    commandLine(
        "xcodebuild",
        "-create-xcframework",
        "-framework", iosFramework,
        "-framework", iosSimFramework,
        "-framework", macFramework,
        "-output", output
    )

    doFirst {
        println("\n🚀 Creating XCFramework bundle...")
        println("  iOS:           $iosFramework")
        println("  iOS Simulator: $iosSimFramework")
        println("  macOS:         $macFramework")
        println("  Output:        $output")
    }

    doLast {
        println("\n✅ XCFramework created successfully!")
        println("📍 Location: $output")
        println("\n📋 XCFramework contains:")
        println("  ✓ iOS ARM64 (devices)")
        println("  ✓ iOS ARM64 Simulator (simulator)")
        println("  ✓ macOS ARM64 (Apple Silicon)")
        println("\n🎯 Ready to use in Xcode projects!")
    }
}


publishing {
    repositories {
        mavenLocal()
    }
}
