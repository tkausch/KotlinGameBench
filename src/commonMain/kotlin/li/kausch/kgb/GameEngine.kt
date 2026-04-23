package li.kausch.kgb

/**
 * Core game logic that's platform-agnostic.
 * Works with any MoveProvider implementation.
 */
class GameEngine {
    private val board = ConnectBoard()
    private var currentPlayer = Player.WHITE
    private var gameOver = false
    private val aiPlayer = ConnectPlayer()

    /**
     * Runs the game with the given move provider.
     */
    suspend fun playGame(moveProvider: MoveProvider) {
        moveProvider.displayWelcome()
        moveProvider.displayInitialBoard(board)

        while (!gameOver) {
            playTurn(moveProvider)
        }

        moveProvider.notifyGameOver(board.winner())
    }

    private suspend fun playTurn(moveProvider: MoveProvider) {
        val playerSymbol = getPlayerSymbol(currentPlayer)

        if (currentPlayer == Player.WHITE) {
            playHumanTurn(moveProvider, playerSymbol)
        } else {
            playAITurn(moveProvider, playerSymbol)
        }
    }

    private suspend fun playHumanTurn(moveProvider: MoveProvider, playerSymbol: String) {
        while (true) {
            val column = moveProvider.getHumanMove(board, currentPlayer)

            if (column < 0 || column >= board.columns) {
                moveProvider.displayInvalidInput(
                    "Invalid column! Please enter a number between 1 and ${board.columns}"
                )
                continue
            }

            val move = ConnectMove(currentPlayer, column, 0)
            val actualMove = board.move(move)

            if (actualMove == null) {
                moveProvider.displayColumnFull(column + 1)
                continue
            }

            moveProvider.notifyMoveResult(playerSymbol, column + 1, board)
            checkGameEnd()
            break
        }
    }

    private suspend fun playAITurn(moveProvider: MoveProvider, playerSymbol: String) {
        moveProvider.displayAIThinking(playerSymbol)

        val aiMove = aiPlayer.getBestMove(board, currentPlayer, depth = 6)

        if (aiMove != null) {
            val move = board.move(aiMove)
            if (move != null) {
                moveProvider.notifyMoveResult(playerSymbol, aiMove.column + 1, board)
                checkGameEnd()
            }
        }
    }

    private fun checkGameEnd() {
        if (board.isEndOfGame()) {
            gameOver = true
        } else {
            currentPlayer = currentPlayer.opponent()
        }
    }

    private fun getPlayerSymbol(player: Player): String {
        return if (player == Player.BLACK) "●" else "○"
    }
}

