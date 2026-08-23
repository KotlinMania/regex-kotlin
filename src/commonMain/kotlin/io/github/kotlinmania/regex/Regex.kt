// port-lint: source src/regex/string.rs
package io.github.kotlinmania.regex

/**
 * A compiled regular expression for matching text.
 */
public class Regex internal constructor(
    private val pattern: String,
    private val internalRegex: kotlin.text.Regex,
    private val groupNames: List<String?> = emptyList(),
) {
    public companion object {
        /** Compiles a regular expression with default options. */
        public fun new(pattern: String): Regex = RegexBuilder(pattern).build()

        internal fun normalizePattern(
            rawPattern: String,
            dotMatchesNewLine: Boolean,
            ignoreWhitespace: Boolean,
        ): Pair<String, List<String?>> {
            var pat = rawPattern
            var dotAll = dotMatchesNewLine
            var verbose = ignoreWhitespace

            // Check and strip inline flags
            if (pat.contains("(?s)")) {
                dotAll = true
                pat = pat.replace("(?s)", "")
            }
            if (pat.contains("(?x)")) {
                verbose = true
                pat = pat.replace("(?x)", "")
            }
            pat = pat.replace("(?i)", "")
            pat = pat.replace("(?-i)", "")
            pat = pat.replace("(?m)", "")
            pat = pat.replace("(?-m)", "")
            pat = pat.replace("(?u)", "")
            pat = pat.replace("(?-u)", "")

            // Replace (?P<name> with (?<name>
            pat = pat.replace(kotlin.text.Regex("""\(\?P<([a-zA-Z0-9_]+)>"""), "(?<$1>")

            // Extract group names in order of opening parens
            val names = mutableListOf<String?>()
            names.add(null) // group 0 is full match

            var i = 0
            while (i < pat.length) {
                if (pat[i] == '\\' && i + 1 < pat.length) {
                    i += 2
                    continue
                }
                if (pat[i] == '(' && i + 1 < pat.length && pat[i + 1] != '?') {
                    names.add(null)
                } else if (pat.startsWith("(?<", i)) {
                    val end = pat.indexOf('>', i + 3)
                    if (end != -1) {
                        names.add(pat.substring(i + 3, end))
                    }
                }
                i++
            }

            // If verbose mode, strip comments and unescaped whitespace outside of character classes
            if (verbose) {
                val sb = StringBuilder()
                var inCharClass = false
                var escaped = false
                var j = 0
                while (j < pat.length) {
                    val c = pat[j]
                    if (escaped) {
                        sb.append('\\').append(c)
                        escaped = false
                        j++
                        continue
                    }
                    if (c == '\\') {
                        escaped = true
                        j++
                        continue
                    }
                    if (c == '[' && !inCharClass) {
                        inCharClass = true
                        sb.append(c)
                        j++
                        continue
                    }
                    if (c == ']' && inCharClass) {
                        inCharClass = false
                        sb.append(c)
                        j++
                        continue
                    }
                    if (!inCharClass) {
                        if (c == '#') {
                            val nextLine = pat.indexOf('\n', j)
                            if (nextLine == -1) break
                            j = nextLine + 1
                            continue
                        }
                        if (c.isWhitespace()) {
                            j++
                            continue
                        }
                    }
                    sb.append(c)
                    j++
                }
                if (escaped) sb.append('\\')
                pat = sb.toString()
            }

            // If dotAll mode, replace unescaped '.' outside character classes with '[\s\S]'
            if (dotAll) {
                val sb = StringBuilder()
                var inCharClass = false
                var escaped = false
                var j = 0
                while (j < pat.length) {
                    val c = pat[j]
                    if (escaped) {
                        sb.append('\\').append(c)
                        escaped = false
                        j++
                        continue
                    }
                    if (c == '\\') {
                        escaped = true
                        j++
                        continue
                    }
                    if (c == '[' && !inCharClass) {
                        inCharClass = true
                        sb.append(c)
                        j++
                        continue
                    }
                    if (c == ']' && inCharClass) {
                        inCharClass = false
                        sb.append(c)
                        j++
                        continue
                    }
                    if (c == '.' && !inCharClass) {
                        sb.append("""[\s\S]""")
                        j++
                        continue
                    }
                    sb.append(c)
                    j++
                }
                if (escaped) sb.append('\\')
                pat = sb.toString()
            }

            return Pair(pat, names)
        }
    }

    /** Returns true if and only if a match exists in the haystack. */
    public fun isMatch(haystack: String): Boolean = internalRegex.containsMatchIn(haystack)

    /** Returns the start and end byte offsets of the first match in the haystack. */
    public fun find(haystack: String): Match? {
        val result = internalRegex.find(haystack) ?: return null
        return Match(result.range.first, result.range.last + 1, result.value)
    }

    /** Returns an iterator over all non-overlapping matches in the haystack. */
    public fun findIter(haystack: String): Sequence<Match> =
        internalRegex.findAll(haystack).map { result ->
            Match(result.range.first, result.range.last + 1, result.value)
        }

    /** Returns the capture groups corresponding to the first match in the haystack. */
    public fun captures(haystack: String): Captures? {
        val result = internalRegex.find(haystack) ?: return null
        return toCaptures(haystack, result)
    }

    /** Returns an iterator over all non-overlapping capture groups in the haystack. */
    public fun capturesIter(haystack: String): Sequence<Captures> = internalRegex.findAll(haystack).map { result -> toCaptures(haystack, result) }

    private fun toCaptures(haystack: String, matchResult: MatchResult): Captures {
        val fullStart = matchResult.range.first
        val fullEnd = matchResult.range.last + 1
        val fullMatch = Match(fullStart, fullEnd, matchResult.value)

        val groups = mutableListOf<Match?>()
        groups.add(fullMatch)

        var searchOffset = fullStart
        matchResult.groupValues.drop(1).forEach { valStr ->
            if (valStr.isEmpty() && !haystack.regionMatches(searchOffset, "", 0, 0)) {
                groups.add(null)
            } else {
                val idx = haystack.indexOf(valStr, searchOffset)
                if (idx >= 0 && idx <= fullEnd) {
                    groups.add(Match(idx, idx + valStr.length, valStr))
                    searchOffset = idx + valStr.length
                } else {
                    groups.add(Match(fullStart, fullStart + valStr.length, valStr))
                }
            }
        }

        val named = mutableMapOf<String, Match>()
        groupNames.forEachIndexed { idx, name ->
            if (name != null && idx < groups.size) {
                val m = groups[idx]
                if (m != null) {
                    named[name] = m
                }
            }
        }
        return Captures(groups, named)
    }

    /** Returns a sequence of substrings of haystack delimited by a match of the regex. */
    public fun split(haystack: String): Sequence<String> = internalRegex.split(haystack).asSequence()

    /** Returns a sequence of at most limit substrings of haystack delimited by a match of the regex. */
    public fun splitn(haystack: String, limit: Int): Sequence<String> = internalRegex.split(haystack, limit).asSequence()

    /** Replaces the leftmost-first match in haystack with the given replacement. */
    public fun replace(haystack: String, rep: String): String = internalRegex.replaceFirst(haystack, rep)

    /** Replaces the leftmost-first match in haystack using a function. */
    public fun replace(haystack: String, transform: (Captures) -> String): String {
        val match = internalRegex.find(haystack) ?: return haystack
        val captures = toCaptures(haystack, match)
        val replacement = transform(captures)
        return haystack.replaceRange(match.range, replacement)
    }

    /** Replaces all non-overlapping matches in haystack with the given replacement. */
    public fun replaceAll(haystack: String, rep: String): String = internalRegex.replace(haystack, rep)

    /** Replaces all non-overlapping matches in haystack using a function. */
    public fun replaceAll(haystack: String, transform: (Captures) -> String): String =
        internalRegex.replace(haystack) { matchResult ->
            transform(toCaptures(haystack, matchResult))
        }

    /** Returns the original pattern string. */
    public fun asStr(): String = pattern

    override fun toString(): String = pattern
}
