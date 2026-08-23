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
    /** Returns the range over the haystack of this match. */
    public val range: IntRange get() = start until end
}
