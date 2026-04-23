package li.kausch.kgb

import kotlinx.coroutines.runBlocking

/**
 * Main game controller for JVM that uses the platform-agnostic ConnectGameEngine.
 */
class ConnectGame {
    fun run() {
        val engine = ConnectGameEngine()
        val controller = ConsoleGameController()

        runBlocking {
            engine.playGame(controller)
        }
    }
}
