package li.kausch.kgb

/**
 * Core game logic that's platform-agnostic.
 * Works with any MoveProvider implementation.
 */
class ConnectGameEngine {
    private val board = ConnectBoard()
    private var currentPlayer = Player.WHITE
    private var gameOver = false
    private val aiPlayer = ConnectPlayer()

    /**
     * Runs the game with the given move provider.
     */
    suspend fun playGame(connectController: ConnectController) {
        connectController.displayWelcome()
        connectController.displayInitialBoard(board)

        while (!gameOver) {
            playTurn(connectController)
        }

        connectController.notifyGameOver(board.winner())
    }

    private suspend fun playTurn(connectController: ConnectController) {
        val playerSymbol = getPlayerSymbol(currentPlayer)

        if (currentPlayer == Player.WHITE) {
            playHumanTurn(connectController, playerSymbol)
        } else {
            playAITurn(connectController, playerSymbol)
        }
    }

    private suspend fun playHumanTurn(connectController: ConnectController, playerSymbol: String) {
        while (true) {
            val column = connectController.getHumanMove(board, currentPlayer)

            if (column < 0 || column >= board.columns) {
                connectController.displayInvalidInput(
                    "Invalid column! Please enter a number between 1 and ${board.columns}"
                )
                continue
            }

            val move = ConnectMove(currentPlayer, column, 0)
            val actualMove = board.move(move)

            if (actualMove == null) {
                connectController.displayColumnFull(column + 1)
                continue
            }

            connectController.notifyMoveResult(playerSymbol, column + 1, board)
            checkGameEnd()
            break
        }
    }

    private suspend fun playAITurn(connectController: ConnectController, playerSymbol: String) {
        connectController.displayAIThinking(playerSymbol)

        val aiMove = aiPlayer.getBestMove(board, currentPlayer, depth = 6)

        if (aiMove != null) {
            val move = board.move(aiMove)
            if (move != null) {
                connectController.notifyMoveResult(playerSymbol, aiMove.column + 1, board)
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

