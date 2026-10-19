// port-lint: source regex/string.rs
package io.github.kotlinmania.regex

/**
 * Captures represents the match and capture group matches of a regex in a haystack.
 */
public class Captures internal constructor(
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
    public fun iter(): SubCaptureMatches = SubCaptureMatches(groups)

    /** Expands all instances of `$ref` in `replacement` to the corresponding capture group into `dst`. */
    public fun expand(replacement: String, dst: StringBuilder) {
        var i = 0
        while (i < replacement.length) {
            if (replacement[i] == '$' && i + 1 < replacement.length) {
                if (replacement[i + 1] == '$') {
                    dst.append('$')
                    i += 2
                    continue
                }
                if (replacement[i + 1] == '{') {
                    val end = replacement.indexOf('}', i + 2)
                    if (end != -1) {
                        val ref = replacement.substring(i + 2, end)
                        val num = ref.toIntOrNull()
                        val m = if (num != null) get(num) else name(ref)
                        if (m != null) {
                            dst.append(m.asStr)
                        }
                        i = end + 1
                        continue
                    }
                }
                var j = i + 1
                while (j < replacement.length && (replacement[j].isLetterOrDigit() || replacement[j] == '_')) {
                    j++
                }
                if (j > i + 1) {
                    val ref = replacement.substring(i + 1, j)
                    val num = ref.toIntOrNull()
                    val m = if (num != null) get(num) else name(ref)
                    if (m != null) {
                        dst.append(m.asStr)
                    }
                    i = j
                    continue
                }
            }
            dst.append(replacement[i])
            i++
        }
    }

    /** Extracts the full match and sub-group matches as strings. */
    public fun extract(): Pair<String, List<String>> {
        val full = groups.firstOrNull()?.asStr.orEmpty()
        val subs = groups.drop(1).map { it?.asStr.orEmpty() }
        return Pair(full, subs)
    }

    override fun toString(): String =
        "Captures(${groups.mapIndexed { idx, m -> "$idx: ${m?.asStr}" }.joinToString(", ")})"
}

/**
 * An iterator over all group matches in a [Captures] value.
 */
public class SubCaptureMatches internal constructor(
    private val matches: List<Match?>,
) : Sequence<Match?> {
    override fun iterator(): Iterator<Match?> = matches.iterator()

    public fun count(): Int = matches.size

    public fun sizeHint(): Pair<Int, Int?> = Pair(matches.size, matches.size)
}
