package li.kausch.kgb

/**
 * Interface for providing moves and handling game UI updates.
 * Implementations can provide console, iOS, web, or other UI interactions.
 */
interface MoveProvider {
    /**
     * Get the next move from the human player.
     * @param board Current board state
     * @param player Current player (should be WHITE for human)
     * @return Column number (0-6) or -1 if invalid/quit
     */
    suspend fun getHumanMove(board: SearchableBoard, player: Player): Int

    /**
     * Notify that a move has been made and display the result.
     */
    fun notifyMoveResult(playerSymbol: String, column: Int, board: SearchableBoard)

    /**
     * Notify that the game is over.
     */
    fun notifyGameOver(winner: Player?)

    /**
     * Display welcome message and initial state.
     */
    fun displayWelcome()

    /**
     * Display the initial board state.
     */
    fun displayInitialBoard(board: SearchableBoard)

    /**
     * Display the AI is thinking.
     */
    fun displayAIThinking(playerSymbol: String)

    /**
     * Display that the column is full.
     */
    fun displayColumnFull(column: Int)

    /**
     * Display invalid input message.
     */
    fun displayInvalidInput(message: String)
}

