package li.kausch.kgb

/**
 * Platform-agnostic console-based implementation of ConnectController.
 * Works on all platforms (JVM, iOS, JS, Native) using the ConsolePrinter abstraction.
 */
class ConsoleController : ConnectController {
    override suspend fun getHumanMove(board: SearchableBoard, player: Player): Int {
        console.println("Player ○'s turn (Human)")
        console.print("Enter column (1-7): ")

        return try {
            val input = console.readLine()?.trim()
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
        console.println("")
        console.println("Player $playerSymbol played in column $column")
        console.println("")
        console.println(board)
        console.println("Current heuristic value: ${board.getHeuristicValue()}")
        console.println("")
    }

    override fun notifyGameOver(winner: Player?) {
        console.println("")
        if (winner != null) {
            val winnerSymbol = if (winner == Player.BLACK) "●" else "○"
            console.println("🎉 Game Over! Player $winnerSymbol wins! 🎉")
        } else {
            console.println("🤝 Game Over! It's a tie! The board is full.")
        }
        console.println("Thanks for playing Connect Four!")
    }

    override fun displayWelcome() {
        console.println("Welcome to Connect Four!")
        console.println("Players: ● (BLACK - AI) vs ○ (WHITE - Human)")
        console.println("Enter column numbers 1-7 to drop your piece when it's your turn")
        console.println("")
    }

    override fun displayInitialBoard(board: SearchableBoard) {
        console.println("Initial Board:")
        console.println(board)
        console.println("")
    }

    override fun displayAIThinking(playerSymbol: String) {
        console.println("Player $playerSymbol's turn (AI)")
        console.println("AI is thinking...")
    }

    override fun displayColumnFull(column: Int) {
        console.println("Column $column is full! Try another column.")
        console.println("")
    }

    override fun displayInvalidInput(message: String) {
        console.println(message)
        console.println("")
    }
}

