// port-lint: tests lib.rs
package io.github.kotlinmania.regex

import io.github.kotlinmania.regex.regex.RegexMod
import io.github.kotlinmania.regex.regexset.RegexSetMod
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class ModTest {
    @Test
    fun testModuleMetadata() {
        assertEquals("regex", RegexMod.MODULE_NAME)
        assertEquals("regexset", RegexSetMod.MODULE_NAME)
    }

    @Test
    fun testBytesObject() {
        assertNotNull(Bytes)
    }
}
