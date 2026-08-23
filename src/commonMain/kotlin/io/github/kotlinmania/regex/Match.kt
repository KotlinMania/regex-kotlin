// port-lint: source src/regex/string.rs
package io.github.kotlinmania.regex

/**
 * Match represents a single match of a regex in a haystack.
 */
public data class Match(
    public val start: Int,
    public val end: Int,
    public val asStr: String,
) {
    /** Returns the range over the haystack of this match. */
    public val range: IntRange get() = start until end

    /** Returns the starting byte offset of the match in the haystack. */
    public fun start(): Int = start

    /** Returns the ending byte offset of the match in the haystack. */
    public fun end(): Int = end

    /** Returns the matched substring. */
    public fun asStr(): String = asStr
}
