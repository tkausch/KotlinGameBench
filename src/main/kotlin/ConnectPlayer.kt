package org.example

/**
 * Example usage of SearchEngine for AI gameplay
 */
class ConnectPlayer {
    private val searchEngine = SearchEngine()

    /**
     * Gets the AI's best move for the given ConnectBoard state.
     * @param board Current board state
     * @param player The AI player (WHITE or BLACK)
     * @param depth Search depth (recommended: 4-8 for good performance)
     * @return Best move or null if no moves available
     */
    fun getBestMove(board: SearchableBoard, player: Player, depth: Int = 6): ConnectMove? {
        return searchEngine.findBestMoveWithPruning(board, player, depth) as? ConnectMove
    }

    /**
     * Simple evaluation of how good a ConnectBoard position is for the AI.
     * @param board Board to evaluate
     * @return Heuristic value
     */
    fun evaluatePosition(board: SearchableBoard): Int {
        return board.getHeuristicValue()
    }
}
