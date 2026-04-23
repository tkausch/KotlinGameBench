package li.kausch.kgb

/**
 * Interface for game boards that can be used with the SearchEngine.
 * Defines the contract for minimax search algorithms.
 */
interface SearchableBoard {

    /**
     * Returns a list of all valid moves for the given player.
     * @param player The player (0 or 1, where 0 represents the first player)
     */
    fun nextMoves(player: Player): List<Move>

    /**
     * Makes a move on the board.
     */
    fun move(move: Move): Move?

    /**
     * Undoes the last move made on the board.
     * @return The move that was undone, null if no moves to undo
     */
    fun undoMove(): Move?

    /**
     * Checks if the game has ended.
     * @return true if the game is over (win or draw)
     */
    fun isEndOfGame(): Boolean

    /**
     * Returns the heuristic value of the current board position.
     * Positive values favor player 0, negative values favor player 1.
     * @return Heuristic evaluation of the position
     */
    fun getHeuristicValue(): Int
}
