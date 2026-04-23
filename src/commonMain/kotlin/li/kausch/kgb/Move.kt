package li.kausch.kgb

/**
 * Interface representing a game move with player information.
 */
interface Move {
    /**
     * The player making this move (0 or 1, where 0 represents the first player).
     */
    val player: Player

}
