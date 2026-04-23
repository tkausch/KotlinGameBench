package li.kausch.kgb

/**
 * Console-based implementation of MoveProvider for JVM.
 * Handles all console I/O using readLine().
 */
class ConsoleGameController : MoveProvider {
    override suspend fun getHumanMove(board: SearchableBoard, player: Player): Int {
        println("Player ○'s turn (Human)")
        print("Enter column (1-7): ")

        return try {
            val input = readLine()?.trim()
            if (input.isNullOrEmpty()) {
                -1
            } else {
                input.toInt() - 1 // Convert to 0-based index
            }
        } catch (e: NumberFormatException) {
            -1
        }
    }

    override fun notifyMoveResult(playerSymbol: String, column: Int, board: SearchableBoard) {
        println()
        println("Player $playerSymbol played in column $column")
        println()
        println(board)
        println("Current heuristic value: ${board.getHeuristicValue()}")
        println()
    }

    override fun notifyGameOver(winner: Player?) {
        println()
        if (winner != null) {
            val winnerSymbol = if (winner == Player.BLACK) "●" else "○"
            println("🎉 Game Over! Player $winnerSymbol wins! 🎉")
        } else {
            println("🤝 Game Over! It's a tie! The board is full.")
        }
        println("Thanks for playing Connect Four!")
    }

    override fun displayWelcome() {
        println("Welcome to Connect Four!")
        println("Players: ● (BLACK - AI) vs ○ (WHITE - Human)")
        println("Enter column numbers 1-7 to drop your piece when it's your turn")
        println()
    }

    override fun displayInitialBoard(board: SearchableBoard) {
        println("Initial Board:")
        println(board)
        println()
    }

    override fun displayAIThinking(playerSymbol: String) {
        println("Player $playerSymbol's turn (AI)")
        println("AI is thinking...")
    }

    override fun displayColumnFull(column: Int) {
        println("Column $column is full! Try another column.")
        println()
    }

    override fun displayInvalidInput(message: String) {
        println(message)
        println()
    }
}
