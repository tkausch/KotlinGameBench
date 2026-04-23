package li.kausch.kgb



interface InteractionProvider {

    /**
     * Display welcome message and initial state.
     */
    fun displayWelcome()

    /**
     * Display the initial board state.
     */
    fun displayInitialBoard(board: SearchableBoard)

    /**
     * Display the AI is thinking.
     */
    fun displayAIThinking(playerSymbol: String)

    /**
     * Display that the column is full.
     */
    fun displayColumnFull(column: Int)

    /**
     * Display invalid input message.
     */
    fun displayInvalidInput(message: String)


}