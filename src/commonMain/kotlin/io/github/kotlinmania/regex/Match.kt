// port-lint: source regex/string.rs
package io.github.kotlinmania.regex

/**
 * Match represents a single match of a regex in a haystack.
 */
public data class Match(
    public val start: Int,
    public val end: Int,
    public val asStr: String,
) {
    /** Returns true if and only if this match has a length of zero. */
    public fun isEmpty(): Boolean = start == end

    /** Returns the length of this match. */
    public val len: Int get() = end - start

    /** Returns the range over the starting and ending byte offsets of the match in the haystack. */
    public val range: IntRange get() = start until end
}
