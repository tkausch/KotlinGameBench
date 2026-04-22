package org.example

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.BeforeEach

class ConnectBoardTest {

    private lateinit var board: ConnectBoard

    @BeforeEach
    fun setUp() {
        board = ConnectBoard()
    }

    @Nested
    @DisplayName("Constructor Tests")
    inner class ConstructorTests {

        @Test
        @DisplayName("Should create board with default dimensions")
        fun shouldCreateBoardWithDefaultDimensions() {
            val defaultBoard = ConnectBoard()
            assertEquals(6, defaultBoard.rows)
            assertEquals(7, defaultBoard.columns)
        }

        @Test
        @DisplayName("Should create board with custom dimensions")
        fun shouldCreateBoardWithCustomDimensions() {
            val customBoard = ConnectBoard(8, 10)
            assertEquals(8, customBoard.rows)
            assertEquals(10, customBoard.columns)
        }

        @Test
        @DisplayName("Should initialize empty board")
        fun shouldInitializeEmptyBoard() {
            val board = ConnectBoard()
            for (row in 0 until 6) {
                for (col in 0 until 7) {
                    assertNull(board.getPlayer(row, col))
                }
            }
        }
    }

    @Nested
    @DisplayName("Move Functionality Tests")
    inner class MoveFunctionalityTests {

        @Test
        @DisplayName("Should make valid move using Move interface")
        fun shouldMakeValidMoveUsingMoveInterface() {
            val move = ConnectMove(Player.WHITE, 3, 5)
            val result = board.move(move)

            assertNotNull(result)
            assertInstanceOf(ConnectMove::class.java, result)
            val connectMove = result as ConnectMove
            assertEquals(Player.WHITE, connectMove.player)
            assertEquals(3, connectMove.column)
        }

        @Test
        @DisplayName("Should make valid move using player and column")
        fun shouldMakeValidMoveUsingPlayerAndColumn() {
            val result = board.move(Player.WHITE, 3)

            assertNotNull(result)
            assertEquals(Player.WHITE, result?.player)
            assertEquals(3, result?.column)
            assertEquals(5, result?.row) // Bottom row in default 6x7 board
        }

        @Test
        @DisplayName("Should reject invalid column numbers")
        fun shouldRejectInvalidColumnNumbers() {
            assertNull(board.move(Player.WHITE, -1))
            assertNull(board.move(Player.WHITE, 7))
            assertNull(board.move(Player.WHITE, 100))
        }

        @Test
        @DisplayName("Should place pieces in correct position (gravity)")
        fun shouldPlacePiecesInCorrectPosition() {
            val move1 = board.move(Player.WHITE, 3)
            val move2 = board.move(Player.BLACK, 3)

            assertEquals(5, move1?.row) // Bottom
            assertEquals(4, move2?.row) // On top of first piece
        }

        @Test
        @DisplayName("Should fill column from bottom to top")
        fun shouldFillColumnFromBottomToTop() {
            // Fill entire column
            for (i in 0 until 6) {
                val move = board.move(Player.WHITE, 0)
                assertNotNull(move)
                assertEquals(5 - i, move?.row)
            }

            // Next move should fail (column full)
            assertNull(board.move(Player.BLACK, 0))
        }
    }

    @Nested
    @DisplayName("Undo Functionality Tests")
    inner class UndoFunctionalityTests {

        @Test
        @DisplayName("Should undo last move successfully")
        fun shouldUndoLastMoveSuccessfully() {
            val originalMove = board.move(Player.WHITE, 3)
            assertNotNull(originalMove)

            val undoneMove = board.undoMove()
            assertEquals(originalMove, undoneMove)
            assertNull(board.getPlayer(5, 3))
        }

        @Test
        @DisplayName("Should return null when undoing on empty board")
        fun shouldReturnNullWhenUndoingOnEmptyBoard() {
            assertNull(board.undoMove())
        }

        @Test
        @DisplayName("Should undo multiple moves in correct order")
        fun shouldUndoMultipleMovesInCorrectOrder() {
            val move1 = board.move(Player.WHITE, 0)
            val move2 = board.move(Player.BLACK, 1)
            val move3 = board.move(Player.WHITE, 2)

            assertEquals(move3, board.undoMove())
            assertEquals(move2, board.undoMove())
            assertEquals(move1, board.undoMove())
            assertNull(board.undoMove())
        }

        @Test
        @DisplayName("Should restore board state after undo")
        fun shouldRestoreBoardStateAfterUndo() {
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
    }

    @Nested
    @DisplayName("Win Detection Tests")
    inner class WinDetectionTests {

        @Test
        @DisplayName("Should detect horizontal win")
        fun shouldDetectHorizontalWin() {
            // Create horizontal line
            board.move(Player.WHITE, 0)
            board.move(Player.WHITE, 1)
            board.move(Player.WHITE, 2)
            board.move(Player.WHITE, 3)

            assertEquals(Player.WHITE, board.winner())
            assertTrue(board.isEndOfGame())
        }

        @Test
        @DisplayName("Should detect vertical win")
        fun shouldDetectVerticalWin() {
            // Create vertical line in column 0
            board.move(Player.BLACK, 0)
            board.move(Player.BLACK, 0)
            board.move(Player.BLACK, 0)
            board.move(Player.BLACK, 0)

            assertEquals(Player.BLACK, board.winner())
            assertTrue(board.isEndOfGame())
        }

        @Test
        @DisplayName("Should detect diagonal win (top-left to bottom-right)")
        fun shouldDetectDiagonalWinTopLeftToBottomRight() {
            // Create diagonal win pattern
            // Row 5: W
            // Row 4: B W
            // Row 3: B B W
            // Row 2: B B B W

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
        @DisplayName("Should detect diagonal win (top-right to bottom-left)")
        fun shouldDetectDiagonalWinTopRightToBottomLeft() {
            // Create diagonal win pattern going other direction
            // Row 5:       W
            // Row 4:     W B
            // Row 3:   W B B
            // Row 2: W B B B

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
        @DisplayName("Should not detect false win with only 3 in a row")
        fun shouldNotDetectFalseWinWithOnly3InARow() {
            board.move(Player.WHITE, 0)
            board.move(Player.WHITE, 1)
            board.move(Player.WHITE, 2)

            assertNull(board.winner())
            assertFalse(board.isEndOfGame())
        }
    }

    @Nested
    @DisplayName("Board State Tests")
    inner class BoardStateTests {

        @Test
        @DisplayName("Should detect full board")
        fun shouldDetectFullBoard() {
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
        @DisplayName("Should track move count correctly")
        fun shouldTrackMoveCountCorrectly() {
            assertEquals(0, board.getMoveCount())

            board.move(Player.WHITE, 0)
            assertEquals(1, board.getMoveCount())

            board.move(Player.BLACK, 1)
            assertEquals(2, board.getMoveCount())

            board.undoMove()
            assertEquals(1, board.getMoveCount())
        }

        @Test
        @DisplayName("Should provide move history")
        fun shouldProvideMoveHistory() {
            val move1 = board.move(Player.WHITE, 0)
            val move2 = board.move(Player.BLACK, 1)

            val history = board.getMoveHistory()
            assertEquals(2, history.size)
            assertEquals(move1, history[0])
            assertEquals(move2, history[1])
        }

        @Test
        @DisplayName("Should validate moves correctly")
        fun shouldValidateMovesCorrectly() {
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
        @DisplayName("Should reset board correctly")
        fun shouldResetBoardCorrectly() {
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
    }

    @Nested
    @DisplayName("Next Moves Tests")
    inner class NextMovesTests {

        @Test
        @DisplayName("Should return all valid moves on empty board")
        fun shouldReturnAllValidMovesOnEmptyBoard() {
            val nextMoves = board.nextMoves(Player.WHITE)

            assertEquals(7, nextMoves.size)
            for (col in 0 until 7) {
                assertTrue(nextMoves.any {
                    it is ConnectMove && it.column == col && it.row == 5
                })
            }
        }

        @Test
        @DisplayName("Should exclude full columns from next moves")
        fun shouldExcludeFullColumnsFromNextMoves() {
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
        @DisplayName("Should calculate correct drop positions")
        fun shouldCalculateCorrectDropPositions() {
            board.move(Player.WHITE, 3) // Should go to row 5
            board.move(Player.BLACK, 3) // Should go to row 4

            val nextMoves = board.nextMoves(Player.WHITE)
            val column3Move = nextMoves.find {
                it is ConnectMove && it.column == 3
            } as ConnectMove?

            assertNotNull(column3Move)
            assertEquals(3, column3Move?.row) // Next piece should go to row 3
        }
    }

    @Nested
    @DisplayName("Heuristic Value Tests")
    inner class HeuristicValueTests {

        @Test
        @DisplayName("Should start with zero heuristic value")
        fun shouldStartWithZeroHeuristicValue() {
            assertEquals(0, board.getHeuristicValue())
        }

        @Test
        @DisplayName("Should update heuristic value based on moves")
        fun shouldUpdateHeuristicValueBasedOnMoves() {
            val initialValue = board.getHeuristicValue()

            board.move(Player.WHITE, 3) // Center column, should be positive for WHITE
            val afterWhite = board.getHeuristicValue()
            assertTrue(afterWhite > initialValue)

            board.move(Player.BLACK, 3) // Same column, should decrease for BLACK
            val afterBlack = board.getHeuristicValue()
            assertTrue(afterBlack < afterWhite)
        }

        @Test
        @DisplayName("Should return extreme values for game over states")
        fun shouldReturnExtremeValuesForGameOverStates() {
            // Create a win for WHITE
            board.move(Player.WHITE, 0)
            board.move(Player.WHITE, 1)
            board.move(Player.WHITE, 2)
            board.move(Player.WHITE, 3)

            assertEquals(Int.MAX_VALUE, board.getHeuristicValue())
        }

        @Test
        @DisplayName("Should reverse heuristic value on undo")
        fun shouldReverseHeuristicValueOnUndo() {
            val initialValue = board.getHeuristicValue()

            board.move(Player.WHITE, 3)
            board.getHeuristicValue()

            board.undoMove()
            val afterUndo = board.getHeuristicValue()

            assertEquals(initialValue, afterUndo)
        }
    }

    @Nested
    @DisplayName("Copy Functionality Tests")
    inner class CopyFunctionalityTests {

        @Test
        @DisplayName("Should create independent copy of board")
        fun shouldCreateIndependentCopyOfBoard() {
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
        @DisplayName("Should copy move history correctly")
        fun shouldCopyMoveHistoryCorrectly() {
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
    }

    @Nested
    @DisplayName("ToString Tests")
    inner class ToStringTests {

        @Test
        @DisplayName("Should provide board visualization")
        fun shouldProvideBoardVisualization() {
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
    }

    @Nested
    @DisplayName("Edge Cases and Error Handling")
    inner class EdgeCasesAndErrorHandlingTests {

        @Test
        @DisplayName("Should handle boundary positions correctly")
        fun shouldHandleBoundaryPositionsCorrectly() {
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
        @DisplayName("Should handle non-ConnectMove in move method")
        fun shouldHandleNonConnectMoveInMoveMethod() {
            val nonConnectMove = object : Move {
                override val player = Player.WHITE
            }

            assertNull(board.move(nonConnectMove))
        }

        @Test
        @DisplayName("Should maintain consistency after multiple operations")
        fun shouldMaintainConsistencyAfterMultipleOperations() {
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
}
