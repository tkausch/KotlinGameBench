package li.kausch.kgb

import kotlinx.coroutines.runBlocking

fun main() {
    runConnectGame()
}

/**
 * macOS/Native-specific wrapper for ConnectGame that provides blocking execution.
 */
fun runConnectGame() {
    val game = ConnectGame(ConsoleController())
    runBlocking {
        game.start()
    }
}

