// port-lint: source src/error.rs
package io.github.kotlinmania.regex

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
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is Syntax) return false
            return err == other.err
        }

        override fun hashCode(): Int = err.hashCode()
    }

    /** The compiled program exceeded the set size limit. */
    public class CompiledTooBig(
        public val limit: Long,
    ) : RegexError("Compiled regex exceeds size limit of $limit bytes.") {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is CompiledTooBig) return false
            return limit == other.limit
        }

        override fun hashCode(): Int = limit.hashCode()
    }
}
