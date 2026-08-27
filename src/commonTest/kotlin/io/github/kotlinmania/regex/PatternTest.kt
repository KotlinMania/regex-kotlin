// port-lint: tests tests/searcher.rs
package io.github.kotlinmania.regex

/*
 * Copyright (c) 2014 The Rust Project Developers
 * Copyright (c) 2025 Sydney Renee, The Solace Project
 *
 * This source code is dual-licensed under either the MIT license found in the
 * LICENSE-MIT file in the root directory of this source tree or the Apache
 * License, Version 2.0 found in the LICENSE-APACHE file in the root directory
 * of this source tree. You may select, at your option, one of the
 * above-listed licenses.
 */

import kotlin.test.Test
import kotlin.test.assertEquals

class PatternTest {
    private fun collectSteps(reStr: String, haystack: String): List<SearchStep> {
        val re = Regex.new(reStr)
        val searcher = RegexSearcher(haystack, re)
        val steps = mutableListOf<SearchStep>()
        while (true) {
            when (val step = searcher.next()) {
                is SearchStep.Done -> break
                else -> steps.add(step)
            }
        }
        return steps
    }

    @Test
    fun testEmptyHaystack() {
        val steps = collectSteps("""\d""", "")
        assertEquals(emptyList(), steps)
    }

    @Test
    fun testOneMatch() {
        val steps = collectSteps("""\d""", "5")
        assertEquals(listOf(SearchStep.Match(0, 1)), steps)
    }

    @Test
    fun testNoMatch() {
        val steps = collectSteps("""\d""", "a")
        assertEquals(listOf(SearchStep.Reject(0, 1)), steps)
    }

    @Test
    fun testTwoAdjacentMatches() {
        val steps = collectSteps("""\d""", "56")
        assertEquals(listOf(SearchStep.Match(0, 1), SearchStep.Match(1, 2)), steps)
    }

    @Test
    fun testTwoNonAdjacentMatches() {
        val steps = collectSteps("""\d""", "5a6")
        assertEquals(listOf(SearchStep.Match(0, 1), SearchStep.Reject(1, 2), SearchStep.Match(2, 3)), steps)
    }

    @Test
    fun testRejectFirst() {
        val steps = collectSteps("""\d""", "a6")
        assertEquals(listOf(SearchStep.Reject(0, 1), SearchStep.Match(1, 2)), steps)
    }
}
