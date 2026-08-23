// port-lint: source src/builders.rs
package io.github.kotlinmania.regex

import kotlin.text.RegexOption

/**
 * A configurable builder for a [Regex].
 */
public class RegexBuilder(
    public val pattern: String,
) {
    private var caseInsensitive: Boolean = false
    private var multiLine: Boolean = false
    private var dotMatchesNewLine: Boolean = false
    private var crlf: Boolean = false
    private var lineTerminator: Byte = '\n'.code.toByte()
    private var swapGreed: Boolean = false
    private var ignoreWhitespace: Boolean = false
    private var unicode: Boolean = true
    private var octal: Boolean = false
    private var sizeLimit: Long = 10 * 1024 * 1024L
    private var dfaSizeLimit: Long = 2 * 1024 * 1024L
    private var nestLimit: Int = 250

    public fun caseInsensitive(yes: Boolean): RegexBuilder = apply { this.caseInsensitive = yes }

    public fun multiLine(yes: Boolean): RegexBuilder = apply { this.multiLine = yes }

    public fun dotMatchesNewLine(yes: Boolean): RegexBuilder = apply { this.dotMatchesNewLine = yes }

    public fun crlf(yes: Boolean): RegexBuilder = apply { this.crlf = yes }

    public fun lineTerminator(byte: Byte): RegexBuilder = apply { this.lineTerminator = byte }

    public fun swapGreed(yes: Boolean): RegexBuilder = apply { this.swapGreed = yes }

    public fun ignoreWhitespace(yes: Boolean): RegexBuilder = apply { this.ignoreWhitespace = yes }

    public fun unicode(yes: Boolean): RegexBuilder = apply { this.unicode = yes }

    public fun octal(yes: Boolean): RegexBuilder = apply { this.octal = yes }

    public fun sizeLimit(limit: Long): RegexBuilder = apply { this.sizeLimit = limit }

    public fun dfaSizeLimit(limit: Long): RegexBuilder = apply { this.dfaSizeLimit = limit }

    public fun nestLimit(limit: Int): RegexBuilder = apply { this.nestLimit = limit }

    /** Builds and compiles the regular expression. */
    public fun build(): Regex {
        val options = mutableSetOf<RegexOption>()
        if (caseInsensitive || pattern.contains("(?i)")) options.add(RegexOption.IGNORE_CASE)
        if (multiLine || pattern.contains("(?m)")) options.add(RegexOption.MULTILINE)

        val (normalizedPattern, names) = Regex.normalizePattern(pattern, dotMatchesNewLine, ignoreWhitespace)
        try {
            val internal = kotlin.text.Regex(normalizedPattern, options)
            return Regex(pattern, internal, names)
        } catch (e: Exception) {
            throw RegexError.Syntax(e.message ?: "Invalid regular expression")
        }
    }
}
