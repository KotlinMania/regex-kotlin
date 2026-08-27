// port-lint: tests regex/src/error.rs
package io.github.kotlinmania.regex

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ErrorTest {
    @Test
    fun testSyntaxError() {
        val err1 = RegexError.Syntax("unclosed group")
        val err2 = RegexError.Syntax("unclosed group")
        assertEquals(err1, err2)
        assertEquals(err1.hashCode(), err2.hashCode())
        assertTrue(err1.message?.contains("unclosed group") == true)
        assertEquals("unclosed group", err1.err)
    }

    @Test
    fun testCompiledTooBigError() {
        val err1 = RegexError.CompiledTooBig(1024L)
        val err2 = RegexError.CompiledTooBig(1024L)
        assertEquals(err1, err2)
        assertEquals(err1.hashCode(), err2.hashCode())
        assertTrue(err1.message?.contains("1024") == true)
        assertEquals(1024L, err1.limit)
    }
}
