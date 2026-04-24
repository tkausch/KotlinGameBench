package li.kausch.kgb

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals

class ConnectBoardTest {

    private fun createBoard() = ConnectBoard()

    @Test
    fun shouldCreateBoardWithDefaultDimensions() {
        val defaultBoard = ConnectBoard()
        assertEquals(6, defaultBoard.rows)
        assertEquals(7, defaultBoard.columns)
    }

    @Test
    fun shouldCreateBoardWithCustomDimensions() {
        val customBoard = ConnectBoard(8, 10)
        assertEquals(8, customBoard.rows)
        assertEquals(10, customBoard.columns)
    }

    @Test
    fun shouldInitializeEmptyBoard() {
        val board = ConnectBoard()
        for (row in 0 until 6) {
            for (col in 0 until 7) {
                assertNull(board.getPlayer(row, col))
            }
        }
    }

    @Test
    fun shouldMakeValidMoveUsingMoveInterface() {
        val board = createBoard()
        val move = ConnectMove(Player.WHITE, 3, 5)
        val result = board.move(move)

        assertNotNull(result)
        val connectMove = result as ConnectMove
        assertEquals(Player.WHITE, connectMove.player)
        assertEquals(3, connectMove.column)
    }

    @Test
    fun shouldMakeValidMoveUsingPlayerAndColumn() {
        val board = createBoard()
        val result = board.move(Player.WHITE, 3)

        assertNotNull(result)
        assertEquals(Player.WHITE, result.player)
        assertEquals(3, result.column)
        assertEquals(5, result.row) // Bottom row in default 6x7 board
    }

    @Test
    fun shouldRejectInvalidColumnNumbers() {
        val board = createBoard()
        assertNull(board.move(Player.WHITE, -1))
        assertNull(board.move(Player.WHITE, 7))
        assertNull(board.move(Player.WHITE, 100))
    }

    @Test
    fun shouldPlacePiecesInCorrectPosition() {
        val board = createBoard()
        val move1 = board.move(Player.WHITE, 3)
        val move2 = board.move(Player.BLACK, 3)

        assertNotNull(move1)
        assertNotNull(move2)
        assertEquals(5, move1.row) // Bottom
        assertEquals(4, move2.row) // On top of first piece
    }

    @Test
    fun shouldFillColumnFromBottomToTop() {
        val board = createBoard()
        // Fill entire column
        for (i in 0 until 6) {
            val move = board.move(Player.WHITE, 0)
            assertNotNull(move)
            assertEquals(5 - i, move.row)
        }

        // Next move should fail (column full)
        assertNull(board.move(Player.BLACK, 0))
    }

    @Test
    fun shouldUndoLastMoveSuccessfully() {
        val board = createBoard()
        val originalMove = board.move(Player.WHITE, 3)
        assertNotNull(originalMove)

        val undoneMove = board.undoMove()
        assertEquals(originalMove, undoneMove)
        assertNull(board.getPlayer(5, 3))
    }

    @Test
    fun shouldReturnNullWhenUndoingOnEmptyBoard() {
        val board = createBoard()
        assertNull(board.undoMove())
    }

    @Test
    fun shouldUndoMultipleMovesInCorrectOrder() {
        val board = createBoard()
        val move1 = board.move(Player.WHITE, 0)
        val move2 = board.move(Player.BLACK, 1)
        val move3 = board.move(Player.WHITE, 2)

        assertEquals(move3, board.undoMove())
        assertEquals(move2, board.undoMove())
        assertEquals(move1, board.undoMove())
        assertNull(board.undoMove())
    }

    @Test
    fun shouldRestoreBoardStateAfterUndo() {
        val board = createBoard()
        board.move(Player.WHITE, 3)
        board.move(Player.BLACK, 3)

        assertNotNull(board.getPlayer(5, 3))
        assertNotNull(board.getPlayer(4, 3))

        board.undoMove()
        assertNotNull(board.getPlayer(5, 3))
        assertNull(board.getPlayer(4, 3))

        board.undoMove()
        assertNull(board.getPlayer(5, 3))
        assertNull(board.getPlayer(4, 3))
    }

    @Test
    fun shouldDetectHorizontalWin() {
        val board = createBoard()
        // Create horizontal line
        board.move(Player.WHITE, 0)
        board.move(Player.WHITE, 1)
        board.move(Player.WHITE, 2)
        board.move(Player.WHITE, 3)

        assertEquals(Player.WHITE, board.winner())
        assertTrue(board.isEndOfGame())
    }

    @Test
    fun shouldDetectVerticalWin() {
        val board = createBoard()
        // Create vertical line in column 0
        board.move(Player.BLACK, 0)
        board.move(Player.BLACK, 0)
        board.move(Player.BLACK, 0)
        board.move(Player.BLACK, 0)

        assertEquals(Player.BLACK, board.winner())
        assertTrue(board.isEndOfGame())
    }

    @Test
    fun shouldDetectDiagonalWinTopLeftToBottomRight() {
        val board = createBoard()
        // Create diagonal win pattern
        board.move(Player.WHITE, 0) // (5,0)
        board.move(Player.BLACK, 1) // (5,1)
        board.move(Player.WHITE, 1) // (4,1)
        board.move(Player.BLACK, 2) // (5,2)
        board.move(Player.BLACK, 2) // (4,2)
        board.move(Player.WHITE, 2) // (3,2)
        board.move(Player.BLACK, 3) // (5,3)
        board.move(Player.BLACK, 3) // (4,3)
        board.move(Player.BLACK, 3) // (3,3)
        board.move(Player.WHITE, 3) // (2,3) - This should create diagonal win

        assertEquals(Player.WHITE, board.winner())
        assertTrue(board.isEndOfGame())
    }

    @Test
    fun shouldDetectDiagonalWinTopRightToBottomLeft() {
        val board = createBoard()
        // Create diagonal win pattern going other direction
        board.move(Player.WHITE, 3) // (5,3)
        board.move(Player.BLACK, 2) // (5,2)
        board.move(Player.WHITE, 2) // (4,2)
        board.move(Player.BLACK, 1) // (5,1)
        board.move(Player.BLACK, 1) // (4,1)
        board.move(Player.WHITE, 1) // (3,1)
        board.move(Player.BLACK, 0) // (5,0)
        board.move(Player.BLACK, 0) // (4,0)
        board.move(Player.BLACK, 0) // (3,0)
        board.move(Player.WHITE, 0) // (2,0) - This should create diagonal win

        assertEquals(Player.WHITE, board.winner())
        assertTrue(board.isEndOfGame())
    }

    @Test
    fun shouldNotDetectFalseWinWithOnly3InARow() {
        val board = createBoard()
        board.move(Player.WHITE, 0)
        board.move(Player.WHITE, 1)
        board.move(Player.WHITE, 2)

        assertNull(board.winner())
        assertFalse(board.isEndOfGame())
    }

    @Test
    fun shouldDetectFullBoard() {
        val board = createBoard()
        // Fill entire board
        for (col in 0 until 7) {
            for (row in 0 until 6) {
                board.move(if ((col + row) % 2 == 0) Player.WHITE else Player.BLACK, col)
            }
        }

        assertTrue(board.isFull())
        assertTrue(board.isEndOfGame())
    }

    @Test
    fun shouldTrackMoveCountCorrectly() {
        val board = createBoard()
        assertEquals(0, board.getMoveCount())

        board.move(Player.WHITE, 0)
        assertEquals(1, board.getMoveCount())

        board.move(Player.BLACK, 1)
        assertEquals(2, board.getMoveCount())

        board.undoMove()
        assertEquals(1, board.getMoveCount())
    }

    @Test
    fun shouldProvideMoveHistory() {
        val board = createBoard()
        val move1 = board.move(Player.WHITE, 0)
        val move2 = board.move(Player.BLACK, 1)

        val history = board.getMoveHistory()
        assertEquals(2, history.size)
        assertEquals(move1, history[0])
        assertEquals(move2, history[1])
    }

    @Test
    fun shouldValidateMovesCorrectly() {
        val board = createBoard()
        assertTrue(board.isValidMove(0))
        assertTrue(board.isValidMove(3))
        assertTrue(board.isValidMove(6))

        assertFalse(board.isValidMove(-1))
        assertFalse(board.isValidMove(7))

        // Fill column 0 completely
        for (i in 0 until 6) {
            board.move(Player.WHITE, 0)
        }
        assertFalse(board.isValidMove(0))
    }

    @Test
    fun shouldResetBoardCorrectly() {
        val board = createBoard()
        // Make some moves
        board.move(Player.WHITE, 0)
        board.move(Player.BLACK, 1)
        board.move(Player.WHITE, 2)

        board.reset()

        assertEquals(0, board.getMoveCount())
        assertTrue(board.getMoveHistory().isEmpty())
        for (row in 0 until 6) {
            for (col in 0 until 7) {
                assertNull(board.getPlayer(row, col))
            }
        }
    }

    @Test
    fun shouldReturnAllValidMovesOnEmptyBoard() {
        val board = createBoard()
        val nextMoves = board.nextMoves(Player.WHITE)

        assertEquals(7, nextMoves.size)
        for (col in 0 until 7) {
            assertTrue(nextMoves.any {
                it is ConnectMove && it.column == col && it.row == 5
            })
        }
    }

    @Test
    fun shouldExcludeFullColumnsFromNextMoves() {
        val board = createBoard()
        // Fill column 0 completely
        for (i in 0 until 6) {
            board.move(Player.WHITE, 0)
        }

        val nextMoves = board.nextMoves(Player.BLACK)
        assertEquals(6, nextMoves.size)
        assertTrue(nextMoves.none {
            it is ConnectMove && it.column == 0
        })
    }

    @Test
    fun shouldCalculateCorrectDropPositions() {
        val board = createBoard()
        board.move(Player.WHITE, 3) // Should go to row 5
        board.move(Player.BLACK, 3) // Should go to row 4

        val nextMoves = board.nextMoves(Player.WHITE)
        val column3Move = nextMoves.find {
            it is ConnectMove && it.column == 3
        } as ConnectMove?

        assertNotNull(column3Move)
        assertEquals(3, column3Move.row) // Next piece should go to row 3
    }

    @Test
    fun shouldStartWithZeroHeuristicValue() {
        val board = createBoard()
        assertEquals(0, board.getHeuristicValue())
    }

    @Test
    fun shouldUpdateHeuristicValueBasedOnMoves() {
        val board = createBoard()
        val initialValue = board.getHeuristicValue()

        board.move(Player.WHITE, 3) // Center column, should be positive for WHITE
        val afterWhite = board.getHeuristicValue()
        assertTrue(afterWhite > initialValue)

        board.move(Player.BLACK, 3) // Same column, should decrease for BLACK
        val afterBlack = board.getHeuristicValue()
        assertTrue(afterBlack < afterWhite)
    }

    @Test
    fun shouldReturnExtremeValuesForGameOverStates() {
        val board = createBoard()
        // Create a win for WHITE
        board.move(Player.WHITE, 0)
        board.move(Player.WHITE, 1)
        board.move(Player.WHITE, 2)
        board.move(Player.WHITE, 3)

        assertEquals(Int.MAX_VALUE, board.getHeuristicValue())
    }

    @Test
    fun shouldReverseHeuristicValueOnUndo() {
        val board = createBoard()
        val initialValue = board.getHeuristicValue()

        board.move(Player.WHITE, 3)
        board.getHeuristicValue()

        board.undoMove()
        val afterUndo = board.getHeuristicValue()

        assertEquals(initialValue, afterUndo)
    }

    @Test
    fun shouldCreateIndependentCopyOfBoard() {
        val board = createBoard()
        board.move(Player.WHITE, 0)
        board.move(Player.BLACK, 1)

        val copy = board.copy()

        // Verify initial state is the same
        assertEquals(board.getMoveCount(), copy.getMoveCount())
        assertEquals(board.getPlayer(5, 0), copy.getPlayer(5, 0))
        assertEquals(board.getPlayer(5, 1), copy.getPlayer(5, 1))

        // Verify they are independent
        board.move(Player.WHITE, 2)
        assertNotEquals(board.getMoveCount(), copy.getMoveCount())
    }

    @Test
    fun shouldCopyMoveHistoryCorrectly() {
        val board = createBoard()
        board.move(Player.WHITE, 0)
        board.move(Player.BLACK, 1)

        val copy = board.copy()

        assertEquals(board.getMoveHistory().size, copy.getMoveHistory().size)
        for (i in board.getMoveHistory().indices) {
            val originalMove = board.getMoveHistory()[i]
            val copiedMove = copy.getMoveHistory()[i]
            assertEquals(originalMove.player, copiedMove.player)
            assertEquals(originalMove.column, copiedMove.column)
            assertEquals(originalMove.row, copiedMove.row)
        }
    }

    @Test
    fun shouldProvideBoardVisualization() {
        val board = createBoard()
        board.move(Player.WHITE, 3)
        board.move(Player.BLACK, 4)

        val boardString = board.toString()

        // Should contain column numbers
        assertTrue(boardString.contains("1"))
        assertTrue(boardString.contains("7"))

        // Should contain player symbols
        assertTrue(boardString.contains("○") || boardString.contains("WHITE"))
        assertTrue(boardString.contains("●") || boardString.contains("BLACK"))

        // Should contain board structure
        assertTrue(boardString.contains("|"))
        assertTrue(boardString.contains("+"))
    }

    @Test
    fun shouldHandleBoundaryPositionsCorrectly() {
        val board = createBoard()
        // Out of bounds should return null
        assertNull(board.getPlayer(-1, 0))
        assertNull(board.getPlayer(0, -1))
        assertNull(board.getPlayer(6, 0))
        assertNull(board.getPlayer(0, 7))

        // Valid positions should return null for empty board (not throw exceptions)
        assertNull(board.getPlayer(0, 0))
        assertNull(board.getPlayer(5, 6))

        // But placing a piece should make it retrievable
        board.move(Player.WHITE, 0)
        assertNotNull(board.getPlayer(5, 0))
        assertEquals(Player.WHITE, board.getPlayer(5, 0))
    }

    @Test
    fun shouldHandleNonConnectMoveInMoveMethod() {
        val board = createBoard()
        val nonConnectMove = object : Move {
            override val player = Player.WHITE
        }

        assertNull(board.move(nonConnectMove))
    }

    @Test
    fun shouldMaintainConsistencyAfterMultipleOperations() {
        val board = createBoard()
        // Perform various operations
        board.move(Player.WHITE, 0)
        board.move(Player.BLACK, 1)
        board.undoMove()
        board.move(Player.BLACK, 2)
        board.reset()
        board.move(Player.WHITE, 3)

        // Board should still be in a valid state
        assertEquals(1, board.getMoveCount())
        assertEquals(Player.WHITE, board.getPlayer(5, 3))
        assertTrue(board.isValidMove(0))
    }
}

