// port-lint: source find_byte.rs
package io.github.kotlinmania.regex

/**
 * Searches for the given byte in the given byte array.
 *
 * If literal-performance support is enabled, then this uses the optimized byte
 * search implementation. Otherwise, it uses the naive byte-at-a-time
 * implementation.
 */
internal fun findByte(needle: Byte, haystack: ByteArray): Int? {
    fun imp(needle: Byte, haystack: ByteArray): Int? {
        val index = haystack.indexOfFirst { byte -> byte == needle }
        return if (index >= 0) index else null
    }

    return imp(needle, haystack)
}
