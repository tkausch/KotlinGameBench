# AGENTS.md

## Project Overview
This is **KotlinGameBench** - a Kotlin JVM project serving as a game development framework. Currently features a complete Connect Four implementation with AI, and is designed to support multiple game types through common interfaces.

## Architecture & Structure
- **Package Structure**: Uses `org.example` as the base package in `src/main/kotlin/`
- **Main Entry Point**: `src/main/kotlin/Main.kt` - launches the Connect Four game
- **Build System**: Gradle with Kotlin DSL, using JVM toolchain 25
- **Testing**: JUnit 5 configured but no tests exist yet

## Development Workflows

### Building & Running
```bash
# Build the project
./gradlew build

# Run the Connect Four game
./gradlew run

# Run tests (none exist yet)
./gradlew test

# Clean build artifacts
./gradlew clean
```

### Key Build Configuration
- **Kotlin Version**: 2.3.10 (configured in `build.gradle.kts`)
- **JVM Target**: 25 (configured via `jvmToolchain(25)`)
- **Gradle Version**: 9.2.1 (see `gradle/wrapper/gradle-wrapper.properties`)

## Project-Specific Patterns

### Code Organization
- Main application code in `src/main/kotlin/` with `org.example` package
- Framework interfaces: `SearchableBoard`, `Move`, `Player` for game abstraction
- Connect Four implementation: `ConnectBoard`, `ConnectMove`, `ConnectPlayer`, `AIGame`
- AI engine: `SearchEngine` with minimax algorithm and alpha-beta pruning

### Framework Components
- **Game Interfaces**: `SearchableBoard`, `Move` - generic game abstractions
- **AI Engine**: `SearchEngine` - reusable minimax implementation
- **Connect Four**: Complete implementation using framework interfaces
- **Player System**: Flexible player representation (enum and integer-based)

### Dependencies
- Minimal setup: only Kotlin test framework included
- Framework designed for extensibility without external dependencies
- Consider adding for future enhancements:
  - GUI frameworks (JavaFX, Compose)
  - Serialization libraries for game state
  - Network libraries for multiplayer

## Development Guidelines
- Follow Kotlin naming conventions (camelCase for functions/variables, PascalCase for classes)
- Implement games using framework interfaces (`SearchableBoard`, `Move`)
- Maintain compatibility between Player enum and integer player systems
- Add tests in `src/test/kotlin/` mirroring the main package structure
- Use Kotlin idioms (data classes, sealed classes, extension functions) where appropriate

## Quick Start for Game Development
1. Study Connect Four implementation as framework usage example
2. Implement `SearchableBoard` interface for new game types
3. Create game-specific `Move` implementations
4. Use `SearchEngine` for AI opponents
5. Add tests in `src/test/kotlin/`
6. Use `./gradlew run` to test during development
