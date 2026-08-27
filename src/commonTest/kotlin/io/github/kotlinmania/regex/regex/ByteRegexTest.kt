// port-lint: tests regex/bytes.rs
package io.github.kotlinmania.regex.regex

import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class ByteRegexTest {
    @Test
    fun testByteRegexBasic() {
        val re = ByteRegex.new("""[0-9]{3}-[0-9]{3}-[0-9]{4}""")
        val hay = "phone: 111-222-3333".encodeToByteArray()
        assertTrue(re.isMatch(hay))

        val mat = re.find(hay)
        assertNotNull(mat)
        assertEquals("111-222-3333", mat.asStr)
        assertEquals(7, mat.start)
        assertEquals(19, mat.end)
    }

    @Test
    fun testByteRegexCaptures() {
        val re = ByteRegex.new("""(?<area>[0-9]{3})-(?<num>[0-9]{3}-[0-9]{4})""")
        val hay = "phone: 111-222-3333".encodeToByteArray()
        val caps = re.captures(hay)
        assertNotNull(caps)
        assertEquals("111-222-3333", caps[0]?.asStr)
        assertEquals("111", caps["area"]?.asStr)
        assertEquals("222-3333", caps["num"]?.asStr)
    }

    @Test
    fun testByteRegexReplace() {
        val re = ByteRegex.new("""world""")
        val hay = "hello world".encodeToByteArray()
        val replaced = re.replace(hay, "Kotlin".encodeToByteArray())
        assertEquals("hello Kotlin", replaced.decodeToString())
    }

    @Test
    fun testByteRegexSplit() {
        val re = ByteRegex.new("""\s+""")
        val hay = "a  b   c".encodeToByteArray()
        val parts = re.split(hay).map { it.decodeToString() }.toList()
        assertEquals(listOf("a", "b", "c"), parts)
    }
}
