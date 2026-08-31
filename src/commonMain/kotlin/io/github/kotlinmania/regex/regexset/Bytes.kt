// port-lint: source regex/src/regexset/bytes.rs
package io.github.kotlinmania.regex.regexset

import io.github.kotlinmania.regex.RegexSet
import io.github.kotlinmania.regex.SetMatches

/**
 * Match multiple regexes against byte haystacks.
 */
public class ByteRegexSet internal constructor(
    private val set: RegexSet,
) {
    public companion object {
        public fun new(patterns: Iterable<String>): ByteRegexSet =
            ByteRegexSet(RegexSet.new(patterns.toList()))

        public fun empty(): ByteRegexSet =
            ByteRegexSet(RegexSet.empty())
    }

    public fun isMatch(haystack: ByteArray): Boolean =
        set.isMatch(haystack.decodeToString())

    public fun matches(haystack: ByteArray): SetMatches =
        set.matches(haystack.decodeToString())

    public fun patterns(): List<String> = set.patterns()

    public fun len(): Int = set.len()

    public fun isEmpty(): Boolean = set.isEmpty()
}
