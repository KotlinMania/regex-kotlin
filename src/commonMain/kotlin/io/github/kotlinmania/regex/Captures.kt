// port-lint: source regex/string.rs
package io.github.kotlinmania.regex

/**
 * Captures represents the match and capture group matches of a regex in a haystack.
 */
public class Captures(
    private val groups: List<Match?>,
    private val namedGroups: Map<String, Match>,
) {
    /** Returns the match associated with the capture group at index `index`. */
    public operator fun get(index: Int): Match? = groups.getOrNull(index)

    /** Returns the match associated with the capture group named `name`. */
    public fun name(name: String): Match? = namedGroups[name]

    /** Index by group name. */
    public operator fun get(name: String): Match? = namedGroups[name]

    /** Returns the total number of capture groups (including the full match at index 0). */
    public fun len(): Int = groups.size

    /** Returns an iterator over all capture groups. */
    public fun iter(): Sequence<Match?> = groups.asSequence()

    /** Extracts the full match and sub-group matches as strings. */
    public fun extract(): Pair<String, List<String>> {
        val full = groups.firstOrNull()?.asStr.orEmpty()
        val subs = groups.drop(1).map { it?.asStr.orEmpty() }
        return Pair(full, subs)
    }

    override fun toString(): String =
        "Captures(${groups.mapIndexed { idx, m -> "$idx: ${m?.asStr}" }.joinToString(", ")})"
}
