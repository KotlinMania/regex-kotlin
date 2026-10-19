// port-lint: source regex/string.rs
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

        /** Attempts to parse a string into a regular expression. */
        public fun fromStr(s: String): Regex = new(s)

        /** Attempts to parse a string into a regular expression. */
        public fun tryFrom(s: String): Regex = new(s)

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
    public fun isMatch(haystack: String): Boolean = isMatchAt(haystack, 0)

    /** Returns true if and only if a match exists in the haystack starting at the given offset. */
    public fun isMatchAt(haystack: String, start: Int): Boolean {
        require(start <= haystack.length) { "start offset $start exceeds haystack length ${haystack.length}" }
        return internalRegex.find(haystack, start) != null
    }

    /** Returns the end offset of the shortest match in the haystack. */
    public fun shortestMatch(haystack: String): Int? = shortestMatchAt(haystack, 0)

    /** Returns the end offset of the shortest match in the haystack starting at the given offset. */
    public fun shortestMatchAt(haystack: String, start: Int): Int? {
        require(start <= haystack.length) { "start offset $start exceeds haystack length ${haystack.length}" }
        return internalRegex.find(haystack, start)?.let { it.range.last + 1 }
    }

    /** Returns the start and end byte offsets of the first match in the haystack. */
    public fun find(haystack: String): Match? = findAt(haystack, 0)

    /** Returns the start and end byte offsets of the first match in the haystack starting at offset. */
    public fun findAt(haystack: String, start: Int): Match? {
        require(start <= haystack.length) { "start offset $start exceeds haystack length ${haystack.length}" }
        val result = internalRegex.find(haystack, start) ?: return null
        return Match(result.range.first, result.range.last + 1, result.value)
    }

    /** Returns an iterator over all non-overlapping matches in the haystack. */
    public fun findIter(haystack: String): Matches =
        Matches(
            internalRegex.findAll(haystack).map { result ->
                Match(result.range.first, result.range.last + 1, result.value)
            },
        )

    /** Returns the capture groups corresponding to the first match in the haystack. */
    public fun captures(haystack: String): Captures? = capturesAt(haystack, 0)

    /** Returns the capture groups corresponding to the first match in the haystack starting at offset. */
    public fun capturesAt(haystack: String, start: Int): Captures? {
        require(start <= haystack.length) { "start offset $start exceeds haystack length ${haystack.length}" }
        val result = internalRegex.find(haystack, start) ?: return null
        return toCaptures(haystack, result)
    }

    /** Returns an iterator over all non-overlapping capture groups in the haystack. */
    public fun capturesIter(haystack: String): CaptureMatches =
        CaptureMatches(internalRegex.findAll(haystack).map { result -> toCaptures(haystack, result) })

    /** Returns a fresh allocated set of capture locations for this regex. */
    public fun captureLocations(): CaptureLocations = CaptureLocations(capturesLen())

    /** An alias for captureLocations. */
    public fun locations(): CaptureLocations = captureLocations()

    /** Populates the given locations with the capture groups of the first match. */
    public fun capturesRead(locs: CaptureLocations, haystack: String): Match? =
        capturesReadAt(locs, haystack, 0)

    /** Populates the given locations starting at offset with the capture groups of the first match. */
    public fun capturesReadAt(locs: CaptureLocations, haystack: String, start: Int): Match? {
        val caps = capturesAt(haystack, start)
        locs.populate(caps)
        return caps?.get(0)
    }

    /** An alias for capturesReadAt. */
    public fun readCapturesAt(locs: CaptureLocations, haystack: String, start: Int): Match? =
        capturesReadAt(locs, haystack, start)

    private fun toCaptures(haystack: String, matchResult: MatchResult): Captures {
        val fullStart = matchResult.range.first
        val fullEnd = matchResult.range.last + 1
        val fullMatch = Match(fullStart, fullEnd, matchResult.value)

        val groups = mutableListOf<Match?>()
        groups.add(fullMatch)

        var searchOffset = fullStart
        for (i in 1 until matchResult.groupValues.size) {
            val group = matchResult.groups[i]
            if (group == null) {
                groups.add(null)
            } else {
                val valStr = group.value
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

    /** Returns an iterator of substrings of haystack delimited by a match of the regex. */
    public fun split(haystack: String): Split = Split(internalRegex.split(haystack).asSequence())

    /** Returns an iterator of at most limit substrings of haystack delimited by a match of the regex. */
    public fun splitn(haystack: String, limit: Int): SplitN =
        SplitN(if (limit == 0) emptySequence() else internalRegex.split(haystack, limit).asSequence())

    /** Replaces the leftmost-first match in haystack with the given replacement. */
    public fun replace(haystack: String, rep: String): String = internalRegex.replaceFirst(haystack, rep)

    /** Replaces the leftmost-first match in haystack using a Replacer. */
    public fun replace(haystack: String, replacer: Replacer): String {
        val noExp = replacer.noExpansion()
        if (noExp != null) {
            val foundMatch = internalRegex.find(haystack) ?: return haystack
            return haystack.replaceRange(foundMatch.range, noExp)
        }
        val foundMatch = internalRegex.find(haystack) ?: return haystack
        val captures = toCaptures(haystack, foundMatch)
        val sb = StringBuilder()
        replacer.replaceAppend(captures, sb)
        return haystack.replaceRange(foundMatch.range, sb.toString())
    }

    /** Replaces the leftmost-first match in haystack using a function. */
    public fun replace(haystack: String, transform: (Captures) -> String): String {
        val foundMatch = internalRegex.find(haystack) ?: return haystack
        val captures = toCaptures(haystack, foundMatch)
        val replacement = transform(captures)
        return haystack.replaceRange(foundMatch.range, replacement)
    }

    /** Replaces all non-overlapping matches in haystack with the given replacement. */
    public fun replaceAll(haystack: String, rep: String): String = internalRegex.replace(haystack, rep)

    /** Replaces all non-overlapping matches in haystack using a Replacer. */
    public fun replaceAll(haystack: String, replacer: Replacer): String {
        val noExp = replacer.noExpansion()
        if (noExp != null) {
            return internalRegex.replace(haystack, noExp)
        }
        return internalRegex.replace(haystack) { matchResult ->
            val captures = toCaptures(haystack, matchResult)
            val sb = StringBuilder()
            replacer.replaceAppend(captures, sb)
            sb.toString()
        }
    }

    /** Replaces all non-overlapping matches in haystack using a function. */
    public fun replaceAll(haystack: String, transform: (Captures) -> String): String =
        internalRegex.replace(haystack) { matchResult ->
            transform(toCaptures(haystack, matchResult))
        }

    /** Replaces at most limit non-overlapping matches in haystack with the given replacement. */
    public fun replacen(haystack: String, limit: Int, rep: String): String {
        var count = 0
        return internalRegex.replace(haystack) { matchResult ->
            if (count < limit) {
                count++
                var replacement = rep
                matchResult.groupValues.forEachIndexed { idx, value ->
                    replacement = replacement.replace("\${$idx}", value).replace("$$idx", value)
                }
                replacement
            } else {
                matchResult.value
            }
        }
    }

    /** Replaces at most limit non-overlapping matches in haystack using a Replacer. */
    public fun replacen(haystack: String, limit: Int, replacer: Replacer): String {
        val noExp = replacer.noExpansion()
        var count = 0
        return internalRegex.replace(haystack) { matchResult ->
            if (count < limit) {
                count++
                if (noExp != null) {
                    noExp
                } else {
                    val captures = toCaptures(haystack, matchResult)
                    val sb = StringBuilder()
                    replacer.replaceAppend(captures, sb)
                    sb.toString()
                }
            } else {
                matchResult.value
            }
        }
    }

    /** Replaces at most limit non-overlapping matches in haystack using a function. */
    public fun replacen(haystack: String, limit: Int, transform: (Captures) -> String): String {
        var count = 0
        return internalRegex.replace(haystack) { matchResult ->
            if (count < limit) {
                count++
                transform(toCaptures(haystack, matchResult))
            } else {
                matchResult.value
            }
        }
    }

    /** Returns the total number of capture groups in this regex (including the full match at index 0). */
    public fun capturesLen(): Int = groupNames.size

    /** Returns the static number of capture groups in this regex, if known. */
    public fun staticCapturesLen(): Int? = groupNames.size

    /** Returns an iterator over the capture group names in this regex. */
    public fun captureNames(): CaptureNames = CaptureNames(groupNames)

    /** Returns the original pattern string. */
    public fun asStr(): String = pattern

    override fun toString(): String = pattern
}

/**
 * A low level representation of the byte offsets of each capture group.
 */
public class CaptureLocations internal constructor(
    private val totalGroups: Int,
) {
    internal val groups: MutableList<Pair<Int, Int>?> = MutableList(totalGroups) { null }

    /** Returns the start and end byte offsets of the capture group at index i. */
    public fun get(i: Int): Pair<Int, Int>? = groups.getOrNull(i)

    /** An alias for the get method. */
    public fun pos(i: Int): Pair<Int, Int>? = get(i)

    /** Returns the total number of capture groups. */
    public fun len(): Int = totalGroups

    internal fun populate(captures: Captures?) {
        for (i in 0 until totalGroups) {
            val m = captures?.get(i)
            groups[i] = if (m != null) Pair(m.start, m.end) else null
        }
    }
}

public typealias Locations = CaptureLocations

/**
 * An iterator over all non-overlapping matches in a haystack.
 */
public class Matches internal constructor(
    private val sequence: Sequence<Match>,
) : Sequence<Match> {
    override fun iterator(): Iterator<Match> = sequence.iterator()

    public fun count(): Int = sequence.count()
}

/**
 * An iterator over all non-overlapping capture matches in a haystack.
 */
public class CaptureMatches internal constructor(
    private val sequence: Sequence<Captures>,
) : Sequence<Captures> {
    override fun iterator(): Iterator<Captures> = sequence.iterator()

    public fun count(): Int = sequence.count()
}

/**
 * An iterator over all substrings delimited by a regex match.
 */
public class Split internal constructor(
    private val sequence: Sequence<String>,
) : Sequence<String> {
    override fun iterator(): Iterator<String> = sequence.iterator()
}

/**
 * An iterator over at most limit substrings delimited by a regex match.
 */
public class SplitN internal constructor(
    private val sequence: Sequence<String>,
) : Sequence<String> {
    override fun iterator(): Iterator<String> = sequence.iterator()

    public fun sizeHint(): Pair<Int, Int?> {
        val list = sequence.toList()
        return Pair(list.size, list.size)
    }
}

/**
 * An iterator over the names of all capture groups in a regex.
 */
public class CaptureNames internal constructor(
    private val names: List<String?>,
) : Sequence<String?> {
    override fun iterator(): Iterator<String?> = names.iterator()

    public fun count(): Int = names.size

    public fun sizeHint(): Pair<Int, Int?> = Pair(names.size, names.size)
}

/**
 * A trait for types that can be used to replace matches in a haystack.
 */
public fun interface Replacer {
    public fun replaceAppend(caps: Captures, dst: StringBuilder)

    public fun noExpansion(): String? = null

    public fun byRef(): ReplacerRef = ReplacerRef(this)
}

/**
 * A by-reference adaptor for a [Replacer].
 */
public class ReplacerRef(
    private val replacer: Replacer,
) : Replacer {
    override fun replaceAppend(caps: Captures, dst: StringBuilder) {
        replacer.replaceAppend(caps, dst)
    }

    override fun noExpansion(): String? = replacer.noExpansion()
}

/**
 * A helper type for forcing literal string replacement without expanding capture groups.
 */
public class NoExpand(
    public val text: String,
) : Replacer {
    override fun replaceAppend(caps: Captures, dst: StringBuilder) {
        dst.append(text)
    }

    override fun noExpansion(): String = text
}
