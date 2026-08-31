// port-lint: source regex/bytes.rs
package io.github.kotlinmania.regex.regex

import io.github.kotlinmania.regex.Captures
import io.github.kotlinmania.regex.Match
import io.github.kotlinmania.regex.Regex

/**
 * Byte-oriented regular expression matcher.
 */
public class ByteRegex internal constructor(
    private val regex: Regex,
) {
    public companion object {
        public fun new(pattern: String): ByteRegex = ByteRegex(Regex.new(pattern))
    }

    public fun isMatch(haystack: ByteArray): Boolean =
        regex.isMatch(haystack.decodeToString())

    public fun find(haystack: ByteArray): Match? =
        regex.find(haystack.decodeToString())

    public fun findIter(haystack: ByteArray): Sequence<Match> =
        regex.findIter(haystack.decodeToString())

    public fun captures(haystack: ByteArray): Captures? =
        regex.captures(haystack.decodeToString())

    public fun capturesIter(haystack: ByteArray): Sequence<Captures> =
        regex.capturesIter(haystack.decodeToString())

    public fun split(haystack: ByteArray): Sequence<ByteArray> =
        regex.split(haystack.decodeToString()).map { it.encodeToByteArray() }

    public fun splitn(haystack: ByteArray, limit: Int): Sequence<ByteArray> =
        regex.splitn(haystack.decodeToString(), limit).map { it.encodeToByteArray() }

    public fun replace(haystack: ByteArray, rep: ByteArray): ByteArray =
        regex.replace(haystack.decodeToString(), rep.decodeToString()).encodeToByteArray()

    public fun replaceAll(haystack: ByteArray, rep: ByteArray): ByteArray =
        regex.replaceAll(haystack.decodeToString(), rep.decodeToString()).encodeToByteArray()

    public fun asStr(): String = regex.asStr()

    override fun toString(): String = regex.toString()
}
