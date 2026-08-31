// port-lint: tests regex/src/find_byte.rs
package io.github.kotlinmania.regex

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class FindByteTest {
    @Test
    fun testFindBytePresent() {
        val hay = byteArrayOf(10, 20, 30, 40)
        assertEquals(2, findByte(30.toByte(), hay))
        assertEquals(0, findByte(10.toByte(), hay))
        assertEquals(3, findByte(40.toByte(), hay))
    }

    @Test
    fun testFindByteAbsent() {
        val hay = byteArrayOf(10, 20, 30, 40)
        assertNull(findByte(99.toByte(), hay))
    }

    @Test
    fun testFindByteEmpty() {
        val hay = byteArrayOf()
        assertNull(findByte(10.toByte(), hay))
    }
}
