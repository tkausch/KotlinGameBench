# AGENTS.md

## Project Overview
This is **KotlinGameBench** - a Kotlin Multiplatform library serving as a game development framework. Currently features a complete Connect Four implementation with AI, and is designed to support multiple game types through common interfaces. This project compiles to **JVM, JavaScript (Node.js & Browser), and Native (Linux, macOS, Windows)** targets.

## Architecture & Structure
- **Package Structure**: Uses `li.kausch.kgb` as the base package in `src/commonMain/kotlin/` (shared code) and `src/jvmMain/kotlin/` (JVM-specific)
- **Main Entry Point**: `src/jvmMain/kotlin/li/kausch/kgb/Main.kt` - launches the Connect Four game (JVM only)
- **Build System**: Gradle with Kotlin Multiplatform DSL, using KMP with JVM toolchain 21
- **Testing**: JUnit 5 for JVM, with tests in `src/commonTest/kotlin/` available on all platforms

## Multiplatform Structure

### Source Sets
```
src/
├── commonMain/kotlin/li/kausch/kgb/        # Shared code (all platforms)
│   ├── Move.kt
│   ├── Player.kt
│   ├── SearchableBoard.kt
│   ├── SearchEngine.kt
│   ├── ConnectMove.kt
│   ├── ConnectBoard.kt
│   ├── ConnectPlayer.kt
│   └── ConnectGame.kt
├── commonTest/kotlin/li/kausch/kgb/        # Shared tests (all platforms)
│   ├── ConnectMoveTest.kt
│   └── ConnectBoardTest.kt
├── jvmMain/kotlin/li/kausch/kgb/           # JVM-specific code
│   └── Main.kt                              # Interactive console application
├── jsMain/kotlin/li/kausch/kgb/            # JavaScript-specific code (empty)
└── nativeMain/kotlin/li/kausch/kgb/        # Native-specific code (empty)
```

### Supported Targets
- **JVM**: Java 21+ (with interactive console game)
- **JavaScript**: Node.js & Browser runtimes
- **Native**: 
  - Linux X64
  - macOS X64
  - macOS ARM64
  - Windows X64

## Development Workflows

### Building & Running
```bash
# Build all targets
./gradlew build

# Build JVM only
./gradlew buildJvm

# Run the Connect Four game (JVM)
./gradlew run

# Run tests (all platforms)
./gradlew allTests

# Run JVM tests
./gradlew jvmTest

# Clean build artifacts
./gradlew clean
```

### Key Build Configuration
- **Kotlin Version**: 2.3.10 (configured in `build.gradle.kts`)
- **JVM Target**: 21 (configured via `jvmToolchain(21)`)
- **Gradle Version**: 9.2.1 (see `gradle/wrapper/gradle-wrapper.properties`)
- **Kotlin Multiplatform**: Enabled with multiple targets

## Project-Specific Patterns

### Code Organization
- **Common code** in `src/commonMain/kotlin/` - framework interfaces and Connect Four implementation
- **Framework interfaces**: `SearchableBoard`, `Move`, `Player` for game abstraction (multiplatform)
- **Connect Four implementation**: `ConnectBoard`, `ConnectMove`, `ConnectPlayer`, `ConnectGame` (multiplatform)
- **AI engine**: `SearchEngine` with minimax algorithm and alpha-beta pruning (multiplatform)
- **JVM-specific code**: `Main.kt` - interactive console interface (JVM only)

### Framework Components
- **Game Interfaces**: `SearchableBoard`, `Move` - generic game abstractions
- **AI Engine**: `SearchEngine` - reusable minimax implementation
- **Connect Four**: Complete implementation using framework interfaces
- **Player System**: Flexible player representation (enum and integer-based)
- **Platform-Agnostic**: All core logic is in `commonMain` for cross-platform compatibility

### Dependencies
- Minimal setup: only Kotlin test framework included
- Framework designed for extensibility without external dependencies
- Consider adding for future enhancements:
  - GUI frameworks (Compose Multiplatform for all platforms)
  - Serialization libraries for game state (kotlinx.serialization)
  - Network libraries for multiplayer (Ktor)

## Development Guidelines
- Follow Kotlin naming conventions (camelCase for functions/variables, PascalCase for classes)
- **Keep `commonMain` pure**: No platform-specific imports or code
- Implement games using framework interfaces (`SearchableBoard`, `Move`)
- Add platform-specific implementations in appropriate source sets (jvmMain, jsMain, nativeMain)
- Maintain compatibility between Player enum and integer player systems
- Add tests in `src/commonTest/kotlin/` for logic to be tested on all platforms
- Use Kotlin idioms (data classes, sealed classes, extension functions) where appropriate
- Use `@OptIn(ExperimentalMultiplatformApi::class)` for platform-specific code only when necessary

## Quick Start for Game Development
1. Study Connect Four implementation as framework usage example (in `commonMain`)
2. Implement `SearchableBoard` interface for new game types
3. Create game-specific `Move` implementations
4. Use `SearchEngine` for AI opponents
5. Add tests in `src/commonTest/kotlin/`
6. Use `./gradlew run` to test JVM implementation during development

## Publishing

To publish this library to Maven Central or a local repository:

```bash
# Publish to local Maven repository
./gradlew publishToMavenLocal

# Use in another project
dependencies {
    implementation("li.kausch.kgb:KotlinGameBench:1.0-SNAPSHOT")
}
```

This library can be used in any Kotlin Multiplatform project targeting JVM, JS, or Native platforms.
