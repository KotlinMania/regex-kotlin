# Immediate Actions - High-Value Files

Based on AST analysis, here are the concrete next steps.

## Summary

- **Files Present:** 9/22 (40.9%)
- **Function parity:** 45/264 matched (target 69) — 17.0%
- **Class/type parity:** 7/60 matched (target 18) — 11.7%
- **Combined symbol parity:** 52/324 matched (target 87) — 16.0%
- **Average inline-code cosine:** 0.22 (function body across 6 matched files)
- **Average documentation cosine:** 0.41 (doc text across 6 matched files)
- **Cheat-zeroed Files:** 2
- **Critical Issues:** 9 files with <0.60 function similarity

## Priority 1: Fix Incomplete High-Dependency Files

No incomplete high-dependency files detected.

## Priority 2: Port Missing High-Value Files

Critical missing files (>10 dependencies):

No missing high-value files detected.

## Detailed Work Items

Every matched file is listed below with function and type symbol parity.

### 1. regex.string

- **Target:** `regex.Match [PROVENANCE-FALLBACK]`
- **Similarity:** 0.10
- **Dependents:** 2
- **Priority Score:** 2516909.0
- **Functions:** 15/48 matched (target 22)
- **Missing functions:** `fmt`, `from_str`, `try_from`, `replacen`, `shortest_match`, `shortest_match_at`, `is_match_at`, `find_at`, `captures_at`, `captures_read`, `captures_read_at`, `read_captures_at`, `capture_names`, `captures_len`, `static_captures_len`, `capture_locations`, `locations`, `start`, `end`, `is_empty`, `range`, `from`, `get_match`, `expand`, `iter`, `index`, `pos`, `next`, `count`, `size_hint`, `no_expansion`, `by_ref`, `replace_append`
- **Types:** 3/21 matched (target 3)
- **Missing types:** `Err`, `Error`, `CapturesDebugMap`, `Key`, `Value`, `Output`, `CaptureLocations`, `Locations`, `Matches`, `Item`, `CaptureMatches`, `Split`, `SplitN`, `CaptureNames`, `SubCaptureMatches`, `Replacer`, `ReplacerRef`, `NoExpand`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `regex/string.rs` vs expected `regex/string.rs`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `regex/string.rs` vs expected `regex/string.rs`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `regex/string.rs` vs expected `regex/string.rs`
- **Proposed provenance header:** `// port-lint: source regex/string.rs` (current: `// port-lint: source regex/string.rs`)
- **Proposed provenance header:** `// port-lint: source regex/string.rs` (current: `// port-lint: source regex/string.rs`)
- **Proposed provenance header:** `// port-lint: source regex/string.rs` (current: `// port-lint: source regex/string.rs`)
- **Lint issues:** 3

### 2. error

- **Target:** `regex.Error [PROVENANCE-FALLBACK]`
- **Similarity:** 0.15
- **Dependents:** 2
- **Priority Score:** 2030408.5
- **Functions:** 1/3 matched (target 8)
- **Missing functions:** `from_meta_build_error`, `fmt`
- **Types:** 0/1 matched (target 3)
- **Missing types:** `Error`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `error.rs` vs expected `error.rs`
- **Proposed provenance header:** `// port-lint: source error.rs` (current: `// port-lint: source error.rs`)
- **Lint issues:** 1

### 3. regexset.string

- **Target:** `regex.RegexSet [PROVENANCE-FALLBACK]`
- **Similarity:** 0.26
- **Dependents:** 0
- **Priority Score:** 122707.4
- **Functions:** 13/21 matched (target 23)
- **Missing functions:** `matches_read_at`, `read_matches_at`, `default`, `into_iter`, `next`, `size_hint`, `next_back`, `fmt`
- **Types:** 2/6 matched (target 3)
- **Missing types:** `IntoIter`, `Item`, `SetMatchesIntoIter`, `SetMatchesIter`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `regexset/string.rs` vs expected `regexset/string.rs`
- **Proposed provenance header:** `// port-lint: source regexset/string.rs` (current: `// port-lint: source regexset/string.rs`)
- **Lint issues:** 1

### 4. builders

- **Target:** `regex.RegexBuilder [PROVENANCE-FALLBACK]`
- **Similarity:** 0.13
- **Dependents:** 0
- **Priority Score:** 82208.7
- **Functions:** 13/19 matched (target 13)
- **Missing functions:** `default`, `new`, `build_one_string`, `build_one_bytes`, `build_many_string`, `build_many_bytes`
- **Types:** 1/3 matched (target 1)
- **Missing types:** `Builder`, `RegexSetBuilder`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `builders.rs` vs expected `builders.rs`
- **Proposed provenance header:** `// port-lint: source builders.rs` (current: `// port-lint: source builders.rs`)
- **Lint issues:** 1

### 5. pattern

- **Target:** `regex.Pattern [PROVENANCE-FALLBACK]`
- **Similarity:** 0.21
- **Dependents:** 0
- **Priority Score:** 40607.9
- **Functions:** 1/4 matched (target 1)
- **Missing functions:** `into_searcher`, `as_utf8_pattern`, `haystack`
- **Types:** 1/2 matched (target 5)
- **Missing types:** `Searcher`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `pattern.rs` vs expected `pattern.rs`
- **Proposed provenance header:** `// port-lint: source pattern.rs` (current: `// port-lint: source pattern.rs`)
- **Lint issues:** 1

### 6. find_byte

- **Target:** `regex.FindByte [PROVENANCE-FALLBACK]`
- **Similarity:** 0.45
- **Dependents:** 0
- **Priority Score:** 205.5
- **Functions:** 2/2 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched
- **Missing types:** _none_
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `find_byte.rs` vs expected `find_byte.rs`
- **Proposed provenance header:** `// port-lint: source find_byte.rs` (current: `// port-lint: source find_byte.rs`)
- **Lint issues:** 1

### 7. regex.mod

- **Target:** `regex.Mod [STUB] [PROVENANCE-FALLBACK]`
- **Similarity:** 0.00
- **Dependents:** 0
- **Priority Score:** 10.0
- **Functions:** 0/0 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `regex/mod.rs` vs expected `regex/mod.rs`
- **Proposed provenance header:** `// port-lint: source regex/mod.rs` (current: `// port-lint: source regex/mod.rs`)
- **Lint issues:** 1

### 8. regexset.mod

- **Target:** `regexset.Mod [STUB] [PROVENANCE-FALLBACK]`
- **Similarity:** 0.00
- **Dependents:** 0
- **Priority Score:** 10.0
- **Functions:** 0/0 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `regexset/mod.rs` vs expected `regexset/mod.rs`
- **Proposed provenance header:** `// port-lint: source regexset/mod.rs` (current: `// port-lint: source regexset/mod.rs`)
- **Lint issues:** 1

### 9. bytes

- **Target:** `regex.Bytes [STUB] [PROVENANCE-FALLBACK]`
- **Similarity:** 1.00
- **Dependents:** 0
- **Priority Score:** 0.0
- **Functions:** 0/0 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `bytes.rs` vs expected `bytes.rs`
- **Proposed provenance header:** `// port-lint: source bytes.rs` (current: `// port-lint: source bytes.rs`)
- **Lint issues:** 1

## Success Criteria

For each file to be considered "complete":
- **Similarity ≥ 0.85** (Excellent threshold)
- All public APIs ported
- All tests ported
- Documentation ported
- port-lint header present

## Reexport / Wiring Modules

These files match `reexport_modules` patterns in `.ast_distance_config.json`. They are filtered out of
normal priority and missing-file ladders because they are wiring
modules, not direct logic ports. Consult them for call-site routing;
do not treat them as the next implementation target by default.

### Missing

| Source | Expected target | Deps | Source path | Expected path |
|--------|-----------------|------|-------------|---------------|
| `lib` | `Lib` | 0 | `src/lib.rs` | `Lib.kt` |
| `tests.lib` | `tests.Lib` | 0 | `tests/lib.rs` | `tests/Lib.kt` |

