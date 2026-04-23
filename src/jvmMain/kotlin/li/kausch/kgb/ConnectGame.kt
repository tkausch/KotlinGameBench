package li.kausch.kgb

import kotlinx.coroutines.runBlocking

/**
 * Main game controller for JVM that uses the platform-agnostic GameEngine.
 */
class ConnectGame {
    fun run() {
        val engine = GameEngine()
        val controller = ConsoleGameController()

        runBlocking {
            engine.playGame(controller)
        }
    }
}
