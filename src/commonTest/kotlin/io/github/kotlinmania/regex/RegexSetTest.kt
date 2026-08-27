// port-lint: tests regex/src/regexset/string.rs
package io.github.kotlinmania.regex

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class RegexSetTest {
    @Test
    fun testRegexSetBasic() {
        val set =
            RegexSet.new(
                listOf(
                    """\w+""",
                    """\d+""",
                    """foo""",
                    """bar""",
                    """barfoo""",
                    """foobar""",
                ),
            )

        assertTrue(set.isMatch("foobar"))
        assertEquals(6, set.len())
        assertFalse(set.isEmpty())
        assertEquals(
            listOf("""\w+""", """\d+""", """foo""", """bar""", """barfoo""", """foobar"""),
            set.patterns(),
        )

        val matches = set.matches("foobar")
        assertTrue(matches.matchedAny())
        assertFalse(matches.matchedAll())
        assertTrue(matches.matched(0)) // \w+
        assertFalse(matches.matched(1)) // \d+
        assertTrue(matches.matched(2)) // foo
        assertTrue(matches.matched(3)) // bar
        assertFalse(matches.matched(4)) // barfoo
        assertTrue(matches.matched(5)) // foobar
        assertEquals(6, matches.len())

        val matchedList = matches.toList()
        assertEquals(listOf(0, 2, 3, 5), matchedList)
    }

    @Test
    fun testRegexSetEmpty() {
        val empty = RegexSet.empty()
        assertTrue(empty.isEmpty())
        assertEquals(0, empty.len())
        assertFalse(empty.isMatch("test"))

        val matches = empty.matches("test")
        assertFalse(matches.matchedAny())
        assertFalse(matches.matchedAll())
        assertEquals(0, matches.len())
        assertEquals(emptyList<Int>(), matches.toList())
    }

    @Test
    fun testRegexSetBuilder() {
        val set =
            RegexSetBuilder(listOf("foo", "bar"))
                .caseInsensitive(true)
                .build()
        assertTrue(set.isMatch("FOO"))
        assertTrue(set.isMatch("BAR"))
        assertFalse(set.isMatch("BAZ"))
    }
}
