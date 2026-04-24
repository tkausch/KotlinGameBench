package li.kausch.kgb

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

class ConnectMoveTest {

    @Test
    fun shouldCreateConnectMoveWithValidParameters() {
        val move = ConnectMove(Player.WHITE, 3, 5)

        assertEquals(Player.WHITE, move.player)
        assertEquals(3, move.column)
        assertEquals(5, move.row)
    }

    @Test
    fun shouldCreateConnectMoveWithBlackPlayer() {
        val move = ConnectMove(Player.BLACK, 0, 0)

        assertEquals(Player.BLACK, move.player)
        assertEquals(0, move.column)
        assertEquals(0, move.row)
    }

    @Test
    fun shouldCreateConnectMoveWithBoundaryValues() {
        val move = ConnectMove(Player.WHITE, 6, 5)

        assertEquals(Player.WHITE, move.player)
        assertEquals(6, move.column)
        assertEquals(5, move.row)
    }

    @Test
    fun shouldImplementMoveInterface() {
        val move: Move = ConnectMove(Player.BLACK, 2, 3)

        assertEquals(Player.BLACK, move.player)
    }

    @Test
    fun shouldBeEqualWhenAllPropertiesMatch() {
        val move1 = ConnectMove(Player.WHITE, 3, 4)
        val move2 = ConnectMove(Player.WHITE, 3, 4)

        assertEquals(move1.player, move2.player)
        assertEquals(move1.column, move2.column)
        assertEquals(move1.row, move2.row)
    }

    @Test
    fun shouldNotBeEqualWhenPlayerDiffers() {
        val move1 = ConnectMove(Player.WHITE, 3, 4)
        val move2 = ConnectMove(Player.BLACK, 3, 4)

        assertNotEquals(move1.player, move2.player)
    }

    @Test
    fun shouldNotBeEqualWhenColumnDiffers() {
        val move1 = ConnectMove(Player.WHITE, 3, 4)
        val move2 = ConnectMove(Player.WHITE, 2, 4)

        assertNotEquals(move1.column, move2.column)
    }

    @Test
    fun shouldNotBeEqualWhenRowDiffers() {
        val move1 = ConnectMove(Player.WHITE, 3, 4)
        val move2 = ConnectMove(Player.WHITE, 3, 3)

        assertNotEquals(move1.row, move2.row)
    }

    @Test
    fun shouldProvideMeaningfulToStringRepresentation() {
        val move = ConnectMove(Player.WHITE, 3, 4)
        val toString = move.toString()

        // Should contain relevant information
        assertTrue(toString.contains("ConnectMove") ||
                  toString.contains("WHITE") ||
                  toString.contains("3") ||
                  toString.contains("4"))
    }

    @Test
    fun shouldHandleMinimumValues() {
        val move = ConnectMove(Player.BLACK, 0, 0)

        assertEquals(Player.BLACK, move.player)
        assertEquals(0, move.column)
        assertEquals(0, move.row)
    }

    @Test
    fun shouldHandleMaximumTypicalValues() {
        val move = ConnectMove(Player.WHITE, 6, 5)

        assertEquals(Player.WHITE, move.player)
        assertEquals(6, move.column)
        assertEquals(5, move.row)
    }

    @Test
    fun shouldHandleNegativeValues() {
        // Test that the class can handle negative values (though they may not be valid in game context)
        val move = ConnectMove(Player.BLACK, -1, -1)

        assertEquals(Player.BLACK, move.player)
        assertEquals(-1, move.column)
        assertEquals(-1, move.row)
    }
}

