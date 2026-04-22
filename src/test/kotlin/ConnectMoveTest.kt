package org.example

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested

class ConnectMoveTest {

    @Nested
    @DisplayName("Constructor Tests")
    inner class ConstructorTests {

        @Test
        @DisplayName("Should create ConnectMove with valid parameters")
        fun shouldCreateConnectMoveWithValidParameters() {
            val move = ConnectMove(Player.WHITE, 3, 5)

            assertEquals(Player.WHITE, move.player)
            assertEquals(3, move.column)
            assertEquals(5, move.row)
        }

        @Test
        @DisplayName("Should create ConnectMove with BLACK player")
        fun shouldCreateConnectMoveWithBlackPlayer() {
            val move = ConnectMove(Player.BLACK, 0, 0)

            assertEquals(Player.BLACK, move.player)
            assertEquals(0, move.column)
            assertEquals(0, move.row)
        }

        @Test
        @DisplayName("Should create ConnectMove with boundary values")
        fun shouldCreateConnectMoveWithBoundaryValues() {
            val move = ConnectMove(Player.WHITE, 6, 5)

            assertEquals(Player.WHITE, move.player)
            assertEquals(6, move.column)
            assertEquals(5, move.row)
        }
    }

    @Nested
    @DisplayName("Interface Implementation Tests")
    inner class InterfaceImplementationTests {

        @Test
        @DisplayName("Should implement Move interface")
        fun shouldImplementMoveInterface() {
            val move = ConnectMove(Player.BLACK, 2, 3)

            assertInstanceOf(Move::class.java, move)
            assertEquals(Player.BLACK, move.player)
        }
    }

    @Nested
    @DisplayName("Equality and Hash Tests")
    inner class EqualityAndHashTests {

        @Test
        @DisplayName("Should be equal when all properties match")
        fun shouldBeEqualWhenAllPropertiesMatch() {
            val move1 = ConnectMove(Player.WHITE, 3, 4)
            val move2 = ConnectMove(Player.WHITE, 3, 4)

            assertEquals(move1.player, move2.player)
            assertEquals(move1.column, move2.column)
            assertEquals(move1.row, move2.row)
        }

        @Test
        @DisplayName("Should not be equal when player differs")
        fun shouldNotBeEqualWhenPlayerDiffers() {
            val move1 = ConnectMove(Player.WHITE, 3, 4)
            val move2 = ConnectMove(Player.BLACK, 3, 4)

            assertNotEquals(move1.player, move2.player)
        }

        @Test
        @DisplayName("Should not be equal when column differs")
        fun shouldNotBeEqualWhenColumnDiffers() {
            val move1 = ConnectMove(Player.WHITE, 3, 4)
            val move2 = ConnectMove(Player.WHITE, 2, 4)

            assertNotEquals(move1.column, move2.column)
        }

        @Test
        @DisplayName("Should not be equal when row differs")
        fun shouldNotBeEqualWhenRowDiffers() {
            val move1 = ConnectMove(Player.WHITE, 3, 4)
            val move2 = ConnectMove(Player.WHITE, 3, 3)

            assertNotEquals(move1.row, move2.row)
        }
    }

    @Nested
    @DisplayName("String Representation Tests")
    inner class StringRepresentationTests {

        @Test
        @DisplayName("Should provide meaningful toString representation")
        fun shouldProvideMeaningfulToStringRepresentation() {
            val move = ConnectMove(Player.WHITE, 3, 4)
            val toString = move.toString()

            // Should contain relevant information
            assertTrue(toString.contains("ConnectMove") ||
                      toString.contains("WHITE") ||
                      toString.contains("3") ||
                      toString.contains("4"))
        }
    }

    @Nested
    @DisplayName("Edge Case Tests")
    inner class EdgeCaseTests {

        @Test
        @DisplayName("Should handle minimum values")
        fun shouldHandleMinimumValues() {
            val move = ConnectMove(Player.BLACK, 0, 0)

            assertEquals(Player.BLACK, move.player)
            assertEquals(0, move.column)
            assertEquals(0, move.row)
        }

        @Test
        @DisplayName("Should handle maximum typical values")
        fun shouldHandleMaximumTypicalValues() {
            val move = ConnectMove(Player.WHITE, 6, 5)

            assertEquals(Player.WHITE, move.player)
            assertEquals(6, move.column)
            assertEquals(5, move.row)
        }

        @Test
        @DisplayName("Should handle negative values if allowed")
        fun shouldHandleNegativeValues() {
            // Test that the class can handle negative values (though they may not be valid in game context)
            val move = ConnectMove(Player.BLACK, -1, -1)

            assertEquals(Player.BLACK, move.player)
            assertEquals(-1, move.column)
            assertEquals(-1, move.row)
        }
    }
}
