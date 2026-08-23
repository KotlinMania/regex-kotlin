// port-lint: source src/regexset/string.rs
package io.github.kotlinmania.regex

/**
 * Match multiple, possibly overlapping, regexes in a single search.
 */
public class RegexSet internal constructor(
    private val regexList: List<Regex>,
    private val patternList: List<String>,
) {
    public companion object {
        /** Create a new regex set with the given regular expressions. */
        public fun new(exprs: Iterable<String>): RegexSet = RegexSetBuilder(exprs).build()

        /** Create a new empty regex set. */
        public fun empty(): RegexSet = RegexSet(emptyList(), emptyList())
    }

    /** Returns true if and only if one of the regexes in this set matches the haystack given. */
    public fun isMatch(haystack: String): Boolean = isMatchAt(haystack, 0)

    /** Returns true if and only if one of the regexes in this set matches the haystack given, starting at offset. */
    public fun isMatchAt(haystack: String, start: Int): Boolean {
        if (start > haystack.length) return false
        val sub = if (start == 0) haystack else haystack.substring(start)
        return regexList.any { it.isMatch(sub) }
    }

    /** Returns the set of regexes that match in the given haystack. */
    public fun matches(haystack: String): SetMatches = matchesAt(haystack, 0)

    /** Returns the set of regexes that match in the given haystack starting at offset. */
    public fun matchesAt(haystack: String, start: Int): SetMatches {
        if (start > haystack.length) {
            return SetMatches(emptySet(), regexList.size)
        }
        val sub = if (start == 0) haystack else haystack.substring(start)
        val matched = mutableSetOf<Int>()
        regexList.forEachIndexed { index, regex ->
            if (regex.isMatch(sub)) {
                matched.add(index)
            }
        }
        return SetMatches(matched, regexList.size)
    }

    /** Returns the total number of regexes in this set. */
    public fun len(): Int = patternList.size

    /** Returns true if this set contains no regexes. */
    public fun isEmpty(): Boolean = patternList.isEmpty()

    /** Returns the regex patterns that this regex set was constructed from. */
    public fun patterns(): List<String> = patternList

    override fun toString(): String = "RegexSet($patternList)"
}

/**
 * A set of matches returned by a regex set.
 */
public class SetMatches(
    private val matchedIndices: Set<Int>,
    private val totalPatterns: Int,
) : Iterable<Int> {
    /** Whether this set contains any matches. */
    public fun matchedAny(): Boolean = matchedIndices.isNotEmpty()

    /** Whether all patterns in this set matched. */
    public fun matchedAll(): Boolean = matchedIndices.size == totalPatterns && totalPatterns > 0

    /** Whether the regex at the given index matched. */
    public fun matched(index: Int): Boolean {
        if (index >= totalPatterns) {
            throw IndexOutOfBoundsException("Index $index out of bounds for len $totalPatterns")
        }
        return matchedIndices.contains(index)
    }

    /** The total number of regexes in the set that created these matches. */
    public fun len(): Int = totalPatterns

    /** Returns an iterator over the indices of the regexes that matched. */
    public fun iter(): Iterator<Int> = matchedIndices.sorted().iterator()

    override fun iterator(): Iterator<Int> = iter()
}

/**
 * A configurable builder for a [RegexSet].
 */
public class RegexSetBuilder(
    private val patterns: Iterable<String>,
) {
    private var caseInsensitive: Boolean = false
    private var multiLine: Boolean = false
    private var dotMatchesNewLine: Boolean = false
    private var ignoreWhitespace: Boolean = false
    private var unicode: Boolean = true

    public fun caseInsensitive(yes: Boolean): RegexSetBuilder = apply { this.caseInsensitive = yes }

    public fun multiLine(yes: Boolean): RegexSetBuilder = apply { this.multiLine = yes }

    public fun dotMatchesNewLine(yes: Boolean): RegexSetBuilder = apply { this.dotMatchesNewLine = yes }

    public fun ignoreWhitespace(yes: Boolean): RegexSetBuilder = apply { this.ignoreWhitespace = yes }

    public fun unicode(yes: Boolean): RegexSetBuilder = apply { this.unicode = yes }

    /** Builds the regex set. */
    public fun build(): RegexSet {
        val patternList = patterns.toList()
        val regexList =
            patternList.map { pat ->
                RegexBuilder(pat)
                    .caseInsensitive(caseInsensitive)
                    .multiLine(multiLine)
                    .dotMatchesNewLine(dotMatchesNewLine)
                    .ignoreWhitespace(ignoreWhitespace)
                    .unicode(unicode)
                    .build()
            }
        return RegexSet(regexList, patternList)
    }
}
