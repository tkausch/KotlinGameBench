package org.example

class SearchEngine {

    /**
     * Finds the best move for the given player using minimax search.
     * @param board The current board state
     * @param player The player to find the best move for
     * @param depth The look-ahead depth (k)
     * @return The best move, or null if no valid moves available
     */
    fun findBestMove(board: SearchableBoard, player: Player, depth: Int): Move? {
        val validMoves = board.nextMoves(player)
        if (validMoves.isEmpty()) {
            return null
        }

        var bestMove: Move? = null
        var bestValue = if (player == Player.WHITE) Int.MIN_VALUE else Int.MAX_VALUE

        for (move in validMoves) {
            // Make the move
            board.move(move)

            // Evaluate this move using minimax
            val moveValue = minimax(board, depth - 1, player.opponent())

            // Undo the move
            board.undoMove()

            // Update best move if this move is better
            if (player == Player.WHITE) {
                // White is maximizer
                if (moveValue > bestValue) {
                    bestValue = moveValue
                    bestMove = move
                }
            } else {
                // Black is minimizer
                if (moveValue < bestValue) {
                    bestValue = moveValue
                    bestMove = move
                }
            }
        }

        return bestMove
    }

    /**
     * Minimax algorithm implementation.
     * @param board The current board state
     * @param depth Remaining search depth
     * @param player Current player to move
     * @return The heuristic value of the best move from this position
     */
    private fun minimax(board: SearchableBoard, depth: Int, player: Player): Int {
        // Terminal conditions
        if (depth == 0 || board.isEndOfGame()) {
            return board.getHeuristicValue()
        }

        val validMoves = board.nextMoves(player)
        if (validMoves.isEmpty()) {
            return board.getHeuristicValue()
        }

        if (player == Player.WHITE) {
            // White is maximizer
            var maxValue = Int.MIN_VALUE
            for (move in validMoves) {
                board.move(move)
                val value = minimax(board, depth - 1, player.opponent())
                board.undoMove()
                maxValue = maxOf(maxValue, value)
            }
            return maxValue
        } else {
            // Black is minimizer
            var minValue = Int.MAX_VALUE
            for (move in validMoves) {
                board.move(move)
                val value = minimax(board, depth - 1, player.opponent())
                board.undoMove()
                minValue = minOf(minValue, value)
            }
            return minValue
        }
    }

    /**
     * Finds the best move with alpha-beta pruning for improved performance.
     * @param board The current board state
     * @param player The player to find the best move for
     * @param depth The look-ahead depth (k)
     * @return The best move, or null if no valid moves available
     */
    fun findBestMoveWithPruning(board: SearchableBoard, player: Player, depth: Int): Move? {
        val validMoves = board.nextMoves(player)
        if (validMoves.isEmpty()) {
            return null
        }

        var bestMove: Move? = null
        var bestValue = if (player == Player.WHITE) Int.MIN_VALUE else Int.MAX_VALUE

        for (move in validMoves) {
            // Make the move
            board.move(move)

            // Evaluate this move using minimax with alpha-beta pruning
            val moveValue = minimaxWithPruning(
                board,
                depth - 1,
                player.opponent(),
                Int.MIN_VALUE,
                Int.MAX_VALUE
            )

            // Undo the move
            board.undoMove()

            // Update best move if this move is better
            if (player == Player.WHITE) {
                // White is maximizer
                if (moveValue > bestValue) {
                    bestValue = moveValue
                    bestMove = move
                }
            } else {
                // Black is minimizer
                if (moveValue < bestValue) {
                    bestValue = moveValue
                    bestMove = move
                }
            }
        }

        return bestMove
    }

    /**
     * Minimax algorithm with alpha-beta pruning.
     * @param board The current board state
     * @param depth Remaining search depth
     * @param player Current player to move
     * @param alpha Best value for maximizer
     * @param beta Best value for minimizer
     * @return The heuristic value of the best move from this position
     */
    private fun minimaxWithPruning(
        board: SearchableBoard,
        depth: Int,
        player: Player,
        alpha: Int,
        beta: Int
    ): Int {
        // Terminal conditions
        if (depth == 0 || board.isEndOfGame()) {
            return board.getHeuristicValue()
        }

        val validMoves = board.nextMoves(player)
        if (validMoves.isEmpty()) {
            return board.getHeuristicValue()
        }

        var currentAlpha = alpha
        var currentBeta = beta

        if (player == Player.WHITE) {
            // White is maximizer
            var maxValue = Int.MIN_VALUE
            for (move in validMoves) {
                board.move(move)
                val value = minimaxWithPruning(board, depth - 1, player.opponent(), currentAlpha, currentBeta)
                board.undoMove()

                maxValue = maxOf(maxValue, value)
                currentAlpha = maxOf(currentAlpha, value)

                // Alpha-beta pruning
                if (currentBeta <= currentAlpha) {
                    break
                }
            }
            return maxValue
        } else {
            // Black is minimizer
            var minValue = Int.MAX_VALUE
            for (move in validMoves) {
                board.move(move)
                val value = minimaxWithPruning(board, depth - 1, player.opponent(), currentAlpha, currentBeta)
                board.undoMove()

                minValue = minOf(minValue, value)
                currentBeta = minOf(currentBeta, value)

                // Alpha-beta pruning
                if (currentBeta <= currentAlpha) {
                    break
                }
            }
            return minValue
        }
    }
}
