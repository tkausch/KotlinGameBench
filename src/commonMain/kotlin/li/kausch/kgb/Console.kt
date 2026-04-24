package li.kausch.kgb

/**
 * Platform-agnostic interface for console/logging output.
 * Different platforms (JVM, iOS, JS, Native) can provide their own implementations.
 */
interface Console {
    /**
     * Print a line with newline.
     */
    fun println(message: String)

    /**
     * Print a line with newline.
     */
    fun println(message: Any?)

    /**
     * Print without newline.
     */
    fun print(message: String)

    /**
     * Print without newline.
     */
    fun print(message: Any?)

    /**
     * Read a line from input (suspend function for potential async I/O).
     * Returns null if EOF or unsupported on platform.
     */
    suspend fun readLine(): String?
}

/**
 * Common implementation of Console using Kotlin's standard library.
 * Works on JVM, Native (macOS, iOS), and other platforms.
 */
class CommonConsole : Console {
    override fun println(message: String) {
        kotlin.io.println(message)
    }

    override fun println(message: Any?) {
        kotlin.io.println(message)
    }

    override fun print(message: String) {
        kotlin.io.print(message)
    }

    override fun print(message: Any?) {
        kotlin.io.print(message)
    }

    override suspend fun readLine(): String? {
        return kotlin.io.readlnOrNull()
    }
}

/**
 * Global console instance available on all platforms.
 */
val console: Console = CommonConsole()
