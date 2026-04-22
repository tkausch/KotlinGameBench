package li.kausch.kgb

class ConnectBoard(
    val rows: Int = 6,
    val columns: Int = 7
) : SearchableBoard {
    private val board: Array<Array<Player?>> = Array(rows) { Array(columns) { null } }
    private val moveHistory = mutableListOf<ConnectMove>()

    // Heuristic value tracking
    private var heuristicValue: Int = 0

    // Column values for heuristic calculation [10, 20, 50, 70, 50, 20, 10]
    private val columnValues = intArrayOf(10, 20, 50, 70, 50, 20, 10)

    /**
     * Makes a move on the board using the Move interface.
     */
    override fun move(move: Move): Move? {
        if (move is ConnectMove) {
            val player = move.player
            val column = move.column

            if (isValidMove(column)) {
                val row = dropPiece(column, player)
                if (row != -1) {
                    val actualMove = ConnectMove(player, column, row)
                    moveHistory.add(actualMove)
                    updateHeuristicValue(actualMove)
                    return actualMove
                }
            }
        }
        return null

    }

    /**
     * Makes a move for the given player in the specified column.
     * Returns the Move object if successful, or null if the move is invalid.
     */
    fun move(player: Player, column: Int): ConnectMove? {
        if (!isValidMove(column)) {
            return null
        }

        val row = dropPiece(column, player)
        if (row == -1) {
            return null
        }

        val move = ConnectMove(player, column, row)
        moveHistory.add(move)
        updateHeuristicValue(move)
        return move
    }

    /**
     * Undoes the last move made on the ConnectBoard.
     * Returns the Move that was undone, or null if no moves to undo.
     */
    override fun undoMove(): Move? {
        if (moveHistory.isEmpty()) {
            return null
        }

        val lastMove = moveHistory.removeLastOrNull()
        if (lastMove != null) {
            // Remove the piece from the ConnectBoard
            board[lastMove.row][lastMove.column] = null
            // Update heuristic value
            updateHeuristicValue(lastMove, true)
        }

        return lastMove
    }

    /**
     * Checks if the game has ended (either someone won or the ConnectBoard is full).
     */
    override fun isEndOfGame(): Boolean {
        return winner() != null || isFull()
    }

    /**
     * Returns the winning player, or null if no winner yet.
     */
    fun winner(): Player? {
        return if (moveHistory.isNotEmpty()) {
            checkWinner(moveHistory.last())
        } else {
            null // No moves made, no winner possible
        }
    }

    /**
     * Returns a list of all valid moves for the current ConnectBoard state.
     * Each move represents dropping a piece in a valid column.
     */
    override fun nextMoves(player: Player): List<Move> {
        val moves = mutableListOf<ConnectMove>()

        for (column in 0 until columns) {
            if (isValidMove(column)) {
                // Calculate where the piece would land
                var row = -1
                for (r in rows - 1 downTo 0) {
                    if (board[r][column] == null) {
                        row = r
                        break
                    }
                }
                if (row != -1) {
                    moves.add(ConnectMove(player, column, row))
                }
            }
        }

        return moves
    }

    /**
     * Gets the player at the specified position.
     */
    fun getPlayer(row: Int, column: Int): Player? {
        if (row !in 0..<rows || column !in 0..<columns) return null
        return board[row][column]
    }

    /**
     * Returns a copy of the move history.
     */
    fun getMoveHistory(): List<ConnectMove> {
        return moveHistory.toList()
    }

    /**
     * Checks if a move is valid (the column exists and isn't full).
     */
    fun isValidMove(column: Int): Boolean {
        return column in 0 until columns && board[0][column] == null
    }

    /**
     * Returns true if the ConnectBoard is completely full.
     */
    fun isFull(): Boolean {
        for (column in 0 until columns) {
            if (board[0][column] == null) return false
        }
        return true
    }

    /**
     * Drops a piece for the given player in the specified column.
     * Returns the row where the piece was placed, or -1 if the column is full.
     */
    private fun dropPiece(column: Int, player: Player): Int {
        if (column !in 0..<columns) return -1

        for (row in rows - 1 downTo 0) {
            if (board[row][column] == null) {
                board[row][column] = player
                return row
            }
        }
        return -1 // Column is full
    }

    /**
     * Checks if the given move creates a winning condition.
     * Only examines positions around the move position for optimal performance.
     */
    private fun checkWinner(move: ConnectMove): Player? {
        return horizontalWinner(move)
            ?: verticalWinner(move)
            ?: diagonalWinnerTopLeft(move)
            ?: diagonalWinnerTopRight(move)
    }

    /**
     * Checks for horizontal win (4 in a row) around the given move.
     */
    private fun horizontalWinner(move: ConnectMove): Player? {
        val player = move.player
        val row = move.row
        val col = move.column
        var count = 1 // Count the piece we just placed

        // Count to the left
        for (c in col - 1 downTo maxOf(0, col - 3)) {
            if (board[row][c] == player) count++
            else break
        }

        // Count to the right
        for (c in col + 1..minOf(columns - 1, col + 3)) {
            if (board[row][c] == player) count++
            else break
        }

        return if (count >= 4) player else null
    }

    /**
     * Checks for vertical win (4 in a column) around the given move.
     */
    private fun verticalWinner(move: ConnectMove): Player? {
        val player = move.player
        val row = move.row
        val col = move.column
        var count = 1

        // Only need to check downward since pieces fall
        for (r in row + 1 until minOf(rows, row + 4)) {
            if (board[r][col] == player) count++
            else break
        }

        return if (count >= 4) player else null
    }

    /**
     * Checks for diagonal win (top-left to bottom-right) around the given move.
     */
    private fun diagonalWinnerTopLeft(move: ConnectMove): Player? {
        val player = move.player
        val row = move.row
        val col = move.column
        var count = 1

        // Check up-left
        var r = row - 1
        var c = col - 1
        while (r >= maxOf(0, row - 3) && c >= maxOf(0, col - 3) && board[r][c] == player) {
            count++
            r--
            c--
        }

        // Check down-right
        r = row + 1
        c = col + 1
        while (r <= minOf(rows - 1, row + 3) && c <= minOf(columns - 1, col + 3) && board[r][c] == player) {
            count++
            r++
            c++
        }

        return if (count >= 4) player else null
    }

    /**
     * Checks for diagonal win (top-right to bottom-left) around the given move.
     */
    private fun diagonalWinnerTopRight(move: ConnectMove): Player? {
        val player = move.player
        val row = move.row
        val col = move.column
        var count = 1

        // Check up-right
        var r = row - 1
        var c = col + 1
        while (r >= maxOf(0, row - 3) && c <= minOf(columns - 1, col + 3) && board[r][c] == player) {
            count++
            r--
            c++
        }

        // Check down-left
        r = row + 1
        c = col - 1
        while (r <= minOf(rows - 1, row + 3) && c >= maxOf(0, col - 3) && board[r][c] == player) {
            count++
            r++
            c--
        }

        return if (count >= 4) player else null
    }

    /**
     * Returns a string representation of the current ConnectBoard state for display.
     */
    override fun toString(): String {
        val sb = StringBuilder()

        // Column numbers header
        sb.append(" ")
        for (col in 0 until columns) {
            sb.append(" ${col + 1}")
        }
        sb.append("\n")

        // ConnectBoard rows
        for (row in 0 until rows) {
            sb.append("|")
            for (col in 0 until columns) {
                val piece = when (board[row][col]) {
                    Player.BLACK -> "●"  // Player 0 (was BLACK)
                    Player.WHITE -> "○"  // Player 1 (was WHITE)
                    null -> " "
                }
                sb.append("$piece|")
            }
            sb.append("\n")
        }

        // Bottom border
        sb.append("+")
        repeat(columns) { sb.append("-+") }

        return sb.toString()
    }

    /**
     * Clears the ConnectBoard and resets all state.
     */
    fun reset() {
        for (row in 0 until rows) {
            for (col in 0 until columns) {
                board[row][col] = null
            }
        }
        moveHistory.clear()
        heuristicValue = 0
    }

    /**
     * Returns the number of moves made so far.
     */
    fun getMoveCount(): Int = moveHistory.size

    /**
     * Creates a deep copy of the current ConnectBoard state.
     */
    fun copy(): ConnectBoard {
        val newBoard = ConnectBoard(rows, columns)

        // Copy ConnectBoard state
        for (row in 0 until rows) {
            for (col in 0 until columns) {
                newBoard.board[row][col] = this.board[row][col]
            }
        }

        // Copy move history
        newBoard.moveHistory.addAll(this.moveHistory)

        return newBoard
    }

    /**
     * Updates the heuristic value based on the move.
     * Player 1 (maximizer) adds positive values, Player 0 (minimizer) subtracts values.
     * If isUndo is true, reverses the move's heuristic impact.
     */
    private fun updateHeuristicValue(move: ConnectMove, isUndo: Boolean = false) {
        val columnValue = columnValues[move.column]
        val multiplier = if (isUndo) -1 else 1

        when (move.player) {
            Player.WHITE   -> heuristicValue += columnValue * multiplier  // Player 1 maximizes
            Player.BLACK -> heuristicValue -= columnValue * multiplier  // Player 0 minimizes
        }
    }

    /**
     * Returns the current heuristic value of the ConnectBoard.
     * When game is over and there's a winner, returns extreme values:
     * - Player 1 wins: Int.MAX_VALUE
     * - Player 0 wins: Int.MIN_VALUE
     */
    override fun getHeuristicValue(): Int {
        if (isEndOfGame()) {
            val winner = winner()
            if (winner != null) {
                return when (winner) {
                    Player.WHITE -> Int.MAX_VALUE  // Player 1 wins (maximizer)
                    Player.BLACK -> Int.MIN_VALUE  // Player 0 wins (minimizer)
                }
            }
        }
        return heuristicValue
    }
}
