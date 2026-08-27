// port-lint: source regex/src/pattern.rs
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

/**
 * SearchStep represents a step in searching a pattern across a haystack.
 */
public sealed class SearchStep {
    public data class Match(
        val start: Int,
        val end: Int,
    ) : SearchStep()

    public data class Reject(
        val start: Int,
        val end: Int,
    ) : SearchStep()

    public data object Done : SearchStep()
}

/**
 * RegexSearcher implements stepping through regex matches and rejections in a string.
 */
public class RegexSearcher(
    public val haystack: String,
    private val regex: Regex,
) {
    private val iterator: Iterator<Match> = regex.findIter(haystack).iterator()
    private var lastStepEnd: Int = 0
    private var nextMatch: Pair<Int, Int>? = null

    public fun next(): SearchStep {
        val cached = nextMatch
        if (cached != null) {
            nextMatch = null
            lastStepEnd = cached.second
            return SearchStep.Match(cached.first, cached.second)
        }
        if (!iterator.hasNext()) {
            return if (lastStepEnd < haystack.length) {
                val last = lastStepEnd
                lastStepEnd = haystack.length
                SearchStep.Reject(last, haystack.length)
            } else {
                SearchStep.Done
            }
        }
        val item = iterator.next()
        val s = item.start
        val e = item.end
        return if (s == lastStepEnd) {
            lastStepEnd = e
            SearchStep.Match(s, e)
        } else {
            nextMatch = Pair(s, e)
            val last = lastStepEnd
            lastStepEnd = s
            SearchStep.Reject(last, s)
        }
    }
}
