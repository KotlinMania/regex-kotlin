// port-lint: source error.rs
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
 * An error that occurred during parsing or compiling a regular expression.
 */
public sealed class RegexError(
    message: String,
) : Exception(message) {
    /** A syntax error. */
    public class Syntax(
        public val err: String,
    ) : RegexError("Syntax error: $err") {
        public fun description(): String = err

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is Syntax) return false
            return err == other.err
        }

        override fun hashCode(): Int = err.hashCode()

        override fun toString(): String =
            buildString {
                val hr = "~".repeat(79)
                appendLine("Syntax(")
                appendLine(hr)
                appendLine(err)
                appendLine(hr)
                append(")")
            }
    }

    /** The compiled program exceeded the set size limit. */
    public class CompiledTooBig(
        public val limit: Long,
    ) : RegexError("Compiled regex exceeds size limit of $limit bytes.") {
        public fun description(): String = "compiled program too big"

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is CompiledTooBig) return false
            return limit == other.limit
        }

        override fun hashCode(): Int = limit.hashCode()

        override fun toString(): String = "CompiledTooBig($limit)"
    }
}
