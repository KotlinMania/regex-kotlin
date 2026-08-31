# port-lint Proposed Changes

**Generated:** 2026-08-31
**Source:** tmp/regex/src
**Target:** src/commonMain/kotlin/io/github/kotlinmania/regex

These are review proposals only. They are emitted when a Rust -> Kotlin pair matches only after fallback normalization, so the existing `port-lint` header is not an exact provenance match.

| Target file | Current header | Proposed header | Source path | Reason |
|-------------|----------------|-----------------|-------------|--------|
| `src/commonMain/kotlin/io/github/kotlinmania/regex/Regex.kt` | `// port-lint: source regex/src/regex/string.rs` | `// port-lint: source regex/string.rs` | `regex/string.rs` | `port-lint provenance header matched only after fallback normalization: 'regex/src/regex/string.rs' vs expected 'regex/string.rs'` |
| `src/commonMain/kotlin/io/github/kotlinmania/regex/Captures.kt` | `// port-lint: source regex/src/regex/string.rs` | `// port-lint: source regex/string.rs` | `regex/string.rs` | `port-lint provenance header matched only after fallback normalization: 'regex/src/regex/string.rs' vs expected 'regex/string.rs'` |
| `src/commonMain/kotlin/io/github/kotlinmania/regex/Match.kt` | `// port-lint: source regex/src/regex/string.rs` | `// port-lint: source regex/string.rs` | `regex/string.rs` | `port-lint provenance header matched only after fallback normalization: 'regex/src/regex/string.rs' vs expected 'regex/string.rs'` |
| `src/commonTest/kotlin/io/github/kotlinmania/regex/RegexTest.kt` | `// port-lint: tests regex/src/regex/string.rs` | `// port-lint: tests regex/string.rs` | `regex/string.rs` | `port-lint provenance header matched only after fallback normalization: 'tests:regex/src/regex/string.rs' vs expected 'regex/string.rs'` |
| `src/commonMain/kotlin/io/github/kotlinmania/regex/Error.kt` | `// port-lint: source regex/src/error.rs` | `// port-lint: source error.rs` | `error.rs` | `port-lint provenance header matched only after fallback normalization: 'regex/src/error.rs' vs expected 'error.rs'` |
| `src/commonTest/kotlin/io/github/kotlinmania/regex/ErrorTest.kt` | `// port-lint: tests regex/src/error.rs` | `// port-lint: tests error.rs` | `error.rs` | `port-lint provenance header matched only after fallback normalization: 'tests:regex/src/error.rs' vs expected 'error.rs'` |
| `src/commonMain/kotlin/io/github/kotlinmania/regex/RegexSet.kt` | `// port-lint: source regex/src/regexset/string.rs` | `// port-lint: source regexset/string.rs` | `regexset/string.rs` | `port-lint provenance header matched only after fallback normalization: 'regex/src/regexset/string.rs' vs expected 'regexset/string.rs'` |
| `src/commonTest/kotlin/io/github/kotlinmania/regex/RegexSetTest.kt` | `// port-lint: tests regex/src/regexset/string.rs` | `// port-lint: tests regexset/string.rs` | `regexset/string.rs` | `port-lint provenance header matched only after fallback normalization: 'tests:regex/src/regexset/string.rs' vs expected 'regexset/string.rs'` |
| `src/commonMain/kotlin/io/github/kotlinmania/regex/RegexBuilder.kt` | `// port-lint: source regex/src/builders.rs` | `// port-lint: source builders.rs` | `builders.rs` | `port-lint provenance header matched only after fallback normalization: 'regex/src/builders.rs' vs expected 'builders.rs'` |
| `src/commonTest/kotlin/io/github/kotlinmania/regex/RegexBuilderTest.kt` | `// port-lint: tests regex/src/builders.rs` | `// port-lint: tests builders.rs` | `builders.rs` | `port-lint provenance header matched only after fallback normalization: 'tests:regex/src/builders.rs' vs expected 'builders.rs'` |
| `src/commonMain/kotlin/io/github/kotlinmania/regex/Pattern.kt` | `// port-lint: source regex/src/pattern.rs` | `// port-lint: source pattern.rs` | `pattern.rs` | `port-lint provenance header matched only after fallback normalization: 'regex/src/pattern.rs' vs expected 'pattern.rs'` |
| `src/commonTest/kotlin/io/github/kotlinmania/regex/PatternTest.kt` | `// port-lint: tests regex/src/pattern.rs` | `// port-lint: tests pattern.rs` | `pattern.rs` | `port-lint provenance header matched only after fallback normalization: 'tests:regex/src/pattern.rs' vs expected 'pattern.rs'` |
| `src/commonMain/kotlin/io/github/kotlinmania/regex/FindByte.kt` | `// port-lint: source regex/src/find_byte.rs` | `// port-lint: source find_byte.rs` | `find_byte.rs` | `port-lint provenance header matched only after fallback normalization: 'regex/src/find_byte.rs' vs expected 'find_byte.rs'` |
| `src/commonTest/kotlin/io/github/kotlinmania/regex/FindByteTest.kt` | `// port-lint: tests regex/src/find_byte.rs` | `// port-lint: tests find_byte.rs` | `find_byte.rs` | `port-lint provenance header matched only after fallback normalization: 'tests:regex/src/find_byte.rs' vs expected 'find_byte.rs'` |
