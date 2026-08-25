// port-lint: tests regexset/bytes.rs
package io.github.kotlinmania.regex.regexset

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ByteRegexSetTest {
    @Test
    fun testByteRegexSetBasic() {
        val set = ByteRegexSet.new(listOf("foo", "bar", "baz"))
        assertEquals(3, set.len())
        assertFalse(set.isEmpty())

        val hay = "foobar".encodeToByteArray()
        assertTrue(set.isMatch(hay))

        val matches = set.matches(hay)
        assertTrue(matches.matched(0))
        assertTrue(matches.matched(1))
        assertFalse(matches.matched(2))
    }

    @Test
    fun testEmptyByteRegexSet() {
        val set = ByteRegexSet.empty()
        assertEquals(0, set.len())
        assertTrue(set.isEmpty())
        assertFalse(set.isMatch("test".encodeToByteArray()))
    }
}
