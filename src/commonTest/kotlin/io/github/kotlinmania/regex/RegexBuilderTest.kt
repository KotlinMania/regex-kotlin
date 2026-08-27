// port-lint: tests builders.rs
package io.github.kotlinmania.regex

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class RegexBuilderTest {
    @Test
    fun testCaseInsensitive() {
        val re =
            RegexBuilder("abc")
                .caseInsensitive(true)
                .build()
        assertTrue(re.isMatch("ABC"))
        assertTrue(re.isMatch("abc"))
        assertFalse(re.isMatch("def"))
    }

    @Test
    fun testMultiLine() {
        val re =
            RegexBuilder("^bar$")
                .multiLine(true)
                .build()
        assertTrue(re.isMatch("foo\nbar\nbaz"))
    }

    @Test
    fun testDotMatchesNewLine() {
        val re =
            RegexBuilder("foo.bar")
                .dotMatchesNewLine(true)
                .build()
        assertTrue(re.isMatch("foo\nbar"))
    }

    @Test
    fun testIgnoreWhitespace() {
        val re =
            RegexBuilder("(?x) a b c ")
                .ignoreWhitespace(true)
                .build()
        assertTrue(re.isMatch("abc"))
    }

    @Test
    fun testChainedBuilder() {
        val re =
            RegexBuilder("abc")
                .caseInsensitive(true)
                .multiLine(true)
                .dotMatchesNewLine(true)
                .crlf(true)
                .lineTerminator(10.toByte())
                .swapGreed(true)
                .ignoreWhitespace(false)
                .unicode(true)
                .octal(false)
                .sizeLimit(1024L)
                .dfaSizeLimit(512L)
                .nestLimit(100)
                .build()
        assertTrue(re.isMatch("AbC"))
        assertEquals("abc", re.asStr())
    }
}
