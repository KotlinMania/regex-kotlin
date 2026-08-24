# port-lint Proposed Changes

**Generated:** 2026-08-24
**Source:** tmp/regex
**Target:** src

These are review proposals only. They are emitted when a Rust -> Kotlin pair matches only after fallback normalization, so the existing `port-lint` header is not an exact provenance match.

| Target file | Current header | Proposed header | Source path | Reason |
|-------------|----------------|-----------------|-------------|--------|
| `commonMain/kotlin/io/github/kotlinmania/regex/Match.kt` | `// port-lint: source regex/string.rs` | `// port-lint: source regex/string.rs` | `regex/string.rs` | `port-lint provenance header matched only after fallback normalization: 'regex/string.rs' vs expected 'regex/string.rs'` |
| `commonMain/kotlin/io/github/kotlinmania/regex/Captures.kt` | `// port-lint: source regex/string.rs` | `// port-lint: source regex/string.rs` | `regex/string.rs` | `port-lint provenance header matched only after fallback normalization: 'regex/string.rs' vs expected 'regex/string.rs'` |
| `commonMain/kotlin/io/github/kotlinmania/regex/Regex.kt` | `// port-lint: source regex/string.rs` | `// port-lint: source regex/string.rs` | `regex/string.rs` | `port-lint provenance header matched only after fallback normalization: 'regex/string.rs' vs expected 'regex/string.rs'` |
| `commonMain/kotlin/io/github/kotlinmania/regex/Error.kt` | `// port-lint: source error.rs` | `// port-lint: source error.rs` | `error.rs` | `port-lint provenance header matched only after fallback normalization: 'error.rs' vs expected 'error.rs'` |
| `commonMain/kotlin/io/github/kotlinmania/regex/RegexSet.kt` | `// port-lint: source regexset/string.rs` | `// port-lint: source regexset/string.rs` | `regexset/string.rs` | `port-lint provenance header matched only after fallback normalization: 'regexset/string.rs' vs expected 'regexset/string.rs'` |
| `commonMain/kotlin/io/github/kotlinmania/regex/RegexBuilder.kt` | `// port-lint: source builders.rs` | `// port-lint: source builders.rs` | `builders.rs` | `port-lint provenance header matched only after fallback normalization: 'builders.rs' vs expected 'builders.rs'` |
| `commonMain/kotlin/io/github/kotlinmania/regex/Pattern.kt` | `// port-lint: source pattern.rs` | `// port-lint: source pattern.rs` | `pattern.rs` | `port-lint provenance header matched only after fallback normalization: 'pattern.rs' vs expected 'pattern.rs'` |
| `commonMain/kotlin/io/github/kotlinmania/regex/FindByte.kt` | `// port-lint: source find_byte.rs` | `// port-lint: source find_byte.rs` | `find_byte.rs` | `port-lint provenance header matched only after fallback normalization: 'find_byte.rs' vs expected 'find_byte.rs'` |
| `commonMain/kotlin/io/github/kotlinmania/regex/regex/Mod.kt` | `// port-lint: source regex/mod.rs` | `// port-lint: source regex/mod.rs` | `regex/mod.rs` | `port-lint provenance header matched only after fallback normalization: 'regex/mod.rs' vs expected 'regex/mod.rs'` |
| `commonMain/kotlin/io/github/kotlinmania/regex/regexset/Mod.kt` | `// port-lint: source regexset/mod.rs` | `// port-lint: source regexset/mod.rs` | `regexset/mod.rs` | `port-lint provenance header matched only after fallback normalization: 'regexset/mod.rs' vs expected 'regexset/mod.rs'` |
| `commonMain/kotlin/io/github/kotlinmania/regex/Bytes.kt` | `// port-lint: source bytes.rs` | `// port-lint: source bytes.rs` | `bytes.rs` | `port-lint provenance header matched only after fallback normalization: 'bytes.rs' vs expected 'bytes.rs'` |
