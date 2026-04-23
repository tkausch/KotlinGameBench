package li.kausch.kgb

class ConnectGame {
    private val board = ConnectBoard()
    private var currentPlayer = Player.WHITE  // White (Human) starts first
    private var gameOver = false
    private val aiPlayer = ConnectPlayer()

    /**
     * Starts and runs the Connect Four game.
     */
    fun run() {
        displayWelcome()
        displayInitialConnectBoard()

        while (!gameOver) {
            playTurn()
        }

        displayGameEnd()
    }

    /**
     * Displays the welcome message and game instructions.
     */
    private fun displayWelcome() {
        println("Welcome to Connect Four!")
        println("Players: ● (BLACK - AI) vs ○ (WHITE - Human)")
        println("Enter column numbers 1-7 to drop your piece when it's your turn")
        println()
    }

    /**
     * Displays the initial empty ConnectBoard.
     */
    private fun displayInitialConnectBoard() {
        println("Initial ConnectBoard:")
        println(board)
        println()
    }

    /**
     * Handles a single player turn.
     */
    private fun playTurn() {
        val playerSymbol = getPlayerSymbol(currentPlayer)

        if (currentPlayer == Player.WHITE) {
            // Human player turn
            playHumanTurn(playerSymbol)
        } else {
            // AI player turn
            playAITurn(playerSymbol)
        }
    }

    /**
     * Handles human player's turn (White player).
     */
    private fun playHumanTurn(playerSymbol: String) {
        println("Player $playerSymbol's turn (Human)")
        print("Enter column (1-7): ")

        try {
            val input = readLine()?.trim()

            if (input.isNullOrEmpty()) {
                println("Please enter a valid column number!")
                return
            }

            val column = input.toInt() - 1 // Convert to 0-based index

            if (!isValidColumnInput(column)) {
                println("Invalid column! Please enter a number between 1 and ${board.columns}")
                return
            }

            // Make the move
            val move = ConnectMove(currentPlayer, column, 0) // Row will be determined by the board

            val actualMove = board.move(move)

            if (actualMove == null) {
                println("Column ${column + 1} is full! Try another column.")
                return
            }

            displayMoveResult(playerSymbol, column + 1)
            checkGameEnd()

        } catch (e: NumberFormatException) {
            println("Invalid input! Please enter a number between 1 and ${board.columns}")
        }
    }

    /**
     * Handles AI player's turn (Black player).
     */
    private fun playAITurn(playerSymbol: String) {
        println("Player $playerSymbol's turn (AI)")
        println("AI is thinking...")

        // Get AI move with depth 6 for good performance
        val aiMove = aiPlayer.getBestMove(board, currentPlayer, depth = 6)

        if (aiMove != null) {
            // Make the AI move
            val move = board.move(aiMove)

            if (move != null) {
                displayMoveResult(playerSymbol, aiMove.column + 1)
                checkGameEnd()
            } else {
                println("AI attempted invalid move! This shouldn't happen.")
            }
        } else {
            println("AI couldn't find a valid move! This shouldn't happen.")
        }
    }

    /**
     * Validates if the column input is within valid range.
     */
    private fun isValidColumnInput(column: Int): Boolean {
        return column in 0 until board.columns
    }

    /**
     * Displays the result of a move and the updated ConnectBoard.
     */
    private fun displayMoveResult(playerSymbol: String, column: Int) {
        println()
        println("Player $playerSymbol played in column $column")
        println()
        println(board)
        println("Current heuristic value: ${board.getHeuristicValue()}")
        println()
    }

    /**
     * Checks if the game has ended and handles end game logic.
     */
    private fun checkGameEnd() {
        if (board.isEndOfGame()) {
            gameOver = true
            val winner = board.winner()

            if (winner != null) {
                val winnerSymbol = getPlayerSymbol(winner)
                println("🎉 Game Over! Player $winnerSymbol wins! 🎉")
            } else {
                println("🤝 Game Over! It's a tie! The ConnectBoard is full.")
            }
        } else {
            // Switch to the other player
            currentPlayer = currentPlayer.opponent()
        }
    }

    /**
     * Displays the game end message and statistics.
     */
    private fun displayGameEnd() {
        println()
        println("Thanks for playing Connect Four!")
        println("Total moves played: ${board.getMoveCount()}")
    }

    /**
     * Gets the visual symbol for a player.
     */
    private fun getPlayerSymbol(player: Player): String {
        return if (player == Player.BLACK) "●" else "○"
    }
}
