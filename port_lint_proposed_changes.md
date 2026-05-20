# port-lint Proposed Changes

**Generated:** 2026-05-19
**Source:** tmp/regex/src
**Target:** src/commonMain/kotlin/io/github/kotlinmania/regex

These are review proposals only. They are emitted when a Rust -> Kotlin pair matches only after fallback normalization, so the existing `port-lint` header is not an exact provenance match.

| Target file | Current header | Proposed header | Source path | Reason |
|-------------|----------------|-----------------|-------------|--------|
| `src/commonMain/kotlin/io/github/kotlinmania/regex/regex/Mod.kt` | `// port-lint: source src/regex/mod.rs` | `// port-lint: source regex/mod.rs` | `regex/mod.rs` | `port-lint provenance header matched only after fallback normalization: 'src/regex/mod.rs' vs expected 'regex/mod.rs'` |
| `src/commonMain/kotlin/io/github/kotlinmania/regex/regexset/Mod.kt` | `// port-lint: source src/regexset/mod.rs` | `// port-lint: source regexset/mod.rs` | `regexset/mod.rs` | `port-lint provenance header matched only after fallback normalization: 'src/regexset/mod.rs' vs expected 'regexset/mod.rs'` |
