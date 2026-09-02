// port-lint: tests regex/string.rs
package io.github.kotlinmania.regex

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

class RegexTest {
    @Test
    fun unclosedGroupError() {
        assertFailsWith<Throwable> {
            Regex.new("(")
        }
    }

    @Test
    fun regexString() {
        assertEquals("""[a-zA-Z0-9]+""", Regex.new("""[a-zA-Z0-9]+""").asStr())
        assertEquals("""[a-zA-Z0-9]+""", Regex.new("""[a-zA-Z0-9]+""").toString())
    }

    @Test
    fun captureNames() {
        val re = Regex.new("""(.)(?P<a>.)""")
        assertEquals(3, re.capturesLen())
        assertEquals(listOf(null, null, "a"), re.captureNames().toList())
    }

    @Test
    fun captureIndex() {
        val re = Regex.new("""^(?P<name>.+)$""")
        val cap = re.captures("abc")
        assertNotNull(cap)
        assertEquals("abc", cap[0]?.asStr)
        assertEquals("abc", cap[1]?.asStr)
        assertEquals("abc", cap["name"]?.asStr)
    }

    @Test
    fun captureMisc() {
        val re = Regex.new("""(.)(?P<a>a)?(.)(?P<b>.)""")
        val cap = re.captures("abc")
        assertNotNull(cap)
        assertEquals(5, cap.len())
        assertEquals(0, cap[0]?.start)
        assertEquals(3, cap[0]?.end)
        assertEquals("abc", cap[0]?.asStr)
        assertNull(cap[2])
        assertEquals(2, cap[4]?.start)
        assertEquals(3, cap[4]?.end)
        assertEquals("c", cap[4]?.asStr)
        assertNull(cap.name("a"))
        assertEquals("c", cap.name("b")?.asStr)
    }

    @Test
    fun subCaptureMatches() {
        val re = Regex.new("""([a-z])(([a-z])|([0-9]))""")
        val cap = re.captures("a5")
        assertNotNull(cap)
        val subs = cap.iter().toList()
        assertEquals(5, subs.size)
        assertNotNull(subs[0])
        assertNotNull(subs[1])
        assertNotNull(subs[2])
        assertNull(subs[3])
        assertNotNull(subs[4])

        assertEquals("a5", subs[0]?.asStr)
        assertEquals("a", subs[1]?.asStr)
        assertEquals("5", subs[2]?.asStr)
        assertEquals("5", subs[4]?.asStr)
    }

    @Test
    fun replacenNoCaptures() {
        val re = Regex.new("""[0-9]""")
        assertEquals("age: ZZ34", re.replacen("age: 1234", 2, "Z"))
    }

    @Test
    fun replacenWithCaptures() {
        val re = Regex.new("""([0-9])""")
        assertEquals("age: 1Z2Z34", re.replacen("age: 1234", 2, "\${1}Z"))
    }

    @Test
    fun testBasicMatchAndFind() {
        val re = Regex.new("""Homer (.)\. Simpson""")
        val hay = "Homer J. Simpson"
        assertTrue(re.isMatch(hay))

        val m = re.find(hay)
        assertNotNull(m)
        assertEquals(0, m.start)
        assertEquals(hay.length, m.end)
        assertEquals(0..15, m.range)
        assertEquals("Homer J. Simpson", m.asStr)

        val caps = re.captures(hay)
        assertNotNull(caps)
        assertEquals(2, caps.len())
        assertEquals("Homer J. Simpson", caps[0]?.asStr)
        assertEquals("J", caps[1]?.asStr)
    }

    @Test
    fun testNamedCaptures() {
        val re = Regex.new("""Homer (?<middle>.)\. Simpson""")
        val hay = "Homer J. Simpson"
        val caps = re.captures(hay)
        assertNotNull(caps)
        assertEquals("J", caps.name("middle")?.asStr)
        assertEquals("J", caps["middle"]?.asStr)

        val (full, subs) = caps.extract()
        assertEquals("Homer J. Simpson", full)
        assertEquals(listOf("J"), subs)
    }

    @Test
    fun testFindIterAndCapturesIter() {
        val re = Regex.new("""[0-9]{4}-[0-9]{2}-[0-9]{2}""")
        val hay = "What do 1865-04-14, 1881-07-02, 1901-09-06 and 1963-11-22 have in common?"
        val dates = re.findIter(hay).map { it.asStr }.toList()
        assertEquals(
            listOf("1865-04-14", "1881-07-02", "1901-09-06", "1963-11-22"),
            dates,
        )

        val reCaps = Regex.new("""(?<y>[0-9]{4})-(?<m>[0-9]{2})-(?<d>[0-9]{2})""")
        val extracted =
            reCaps
                .capturesIter(hay)
                .map { c ->
                    Triple(c["y"]?.asStr.orEmpty(), c["m"]?.asStr.orEmpty(), c["d"]?.asStr.orEmpty())
                }.toList()
        assertEquals(
            listOf(
                Triple("1865", "04", "14"),
                Triple("1881", "07", "02"),
                Triple("1901", "09", "06"),
                Triple("1963", "11", "22"),
            ),
            extracted,
        )
    }

    @Test
    fun testSplitAndSplitn() {
        val re = Regex.new("""[ \t]+""")
        val hay = "a b \t c   d"
        val parts = re.split(hay).toList()
        assertEquals(listOf("a", "b", "c", "d"), parts)

        val limited = re.splitn(hay, 3).toList()
        assertEquals(listOf("a", "b", "c   d"), limited)
    }

    @Test
    fun testReplaceAndReplaceAll() {
        val re = Regex.new("""[0-9]+""")
        val hay = "abc 123 def 456"
        assertEquals("abc N def 456", re.replace(hay, "N"))
        assertEquals("abc N def N", re.replaceAll(hay, "N"))

        val replacedWithFn =
            re.replaceAll(hay) { caps ->
                "(${caps[0]?.asStr})"
            }
        assertEquals("abc (123) def (456)", replacedWithFn)
    }

    @Test
    fun testNoMatch() {
        val re = Regex.new("""foo""")
        val hay = "bar"
        assertFalse(re.isMatch(hay))
        assertNull(re.find(hay))
        assertNull(re.captures(hay))
    }
}
