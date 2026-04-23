package li.kausch.kgb

enum class Player {
    BLACK,
    WHITE;

    fun opponent(): Player {
        return when (this) {
            BLACK -> WHITE
            WHITE -> BLACK
        }
    }
}
