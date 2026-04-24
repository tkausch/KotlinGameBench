package li.kausch.kgb

/**
 * Platform-agnostic Connect Four game controller.
 * Provides core game initialization and setup.
 * Platform-specific implementations handle running the game with appropriate coroutine/async patterns.
 */
class ConnectGame(private val controller: ConnectController = ConsoleController()) {
    private val engine = ConnectGameEngine()

    /**
     * Get the game engine.
     */
    fun getEngine(): ConnectGameEngine = engine

    /**
     * Get the controller.
     */
    fun getController(): ConnectController = controller

    /**
     * Start the game (suspend function for multiplatform compatibility).
     * Platforms call this with appropriate coroutine context.
     */
    suspend fun start() {
        engine.playGame(controller)
    }
}

