# Immediate Actions - High-Value Files

Based on AST analysis, here are the concrete next steps.

## Summary

- **Files Present:** 11/22 (50.0%)
- **Function parity:** 53/140 matched (target 103) — 37.9%
- **Class/type parity:** 9/33 matched (target 23) — 27.3%
- **Combined symbol parity:** 62/173 matched (target 126) — 35.8%
- **Average inline-code cosine:** 0.23 (function body across 8 matched files)
- **Average documentation cosine:** 0.33 (doc text across 8 matched files)
- **Cheat-zeroed Files:** 0
- **Critical Issues:** 11 files with <0.60 function similarity

## Priority 1: Fix Incomplete High-Dependency Files

No incomplete high-dependency files detected.

## Priority 2: Port Missing High-Value Files

Critical missing files (>10 dependencies):

No missing high-value files detected.

## Detailed Work Items

Every matched file is listed below with function and type symbol parity.

### 1. regex.string

- **Target:** `regex.Regex`
- **Similarity:** 0.10
- **Dependents:** 2
- **Priority Score:** 2516909.0
- **Functions:** 15/48 matched (target 28)
- **Missing functions:** `fmt`, `from_str`, `try_from`, `replacen`, `shortest_match`, `shortest_match_at`, `is_match_at`, `find_at`, `captures_at`, `captures_read`, `captures_read_at`, `read_captures_at`, `capture_names`, `captures_len`, `static_captures_len`, `capture_locations`, `locations`, `start`, `end`, `is_empty`, `range`, `from`, `get_match`, `expand`, `iter`, `index`, `pos`, `next`, `count`, `size_hint`, `no_expansion`, `by_ref`, `replace_append`
- **Types:** 3/21 matched (target 4)
- **Missing types:** `Err`, `Error`, `CapturesDebugMap`, `Key`, `Value`, `Output`, `CaptureLocations`, `Locations`, `Matches`, `Item`, `CaptureMatches`, `Split`, `SplitN`, `CaptureNames`, `SubCaptureMatches`, `Replacer`, `ReplacerRef`, `NoExpand`

### 2. regex.error

- **Target:** `regex.Error`
- **Similarity:** 0.15
- **Dependents:** 2
- **Priority Score:** 2030408.5
- **Functions:** 1/3 matched (target 10)
- **Missing functions:** `from_meta_build_error`, `fmt`
- **Types:** 0/1 matched (target 4)
- **Missing types:** `Error`

### 3. regexset.string

- **Target:** `regex.RegexSet`
- **Similarity:** 0.26
- **Dependents:** 0
- **Priority Score:** 122707.4
- **Functions:** 13/21 matched (target 26)
- **Missing functions:** `matches_read_at`, `read_matches_at`, `default`, `into_iter`, `next`, `size_hint`, `next_back`, `fmt`
- **Types:** 2/6 matched (target 4)
- **Missing types:** `IntoIter`, `Item`, `SetMatchesIntoIter`, `SetMatchesIter`

### 4. regex.builders

- **Target:** `regex.RegexBuilder`
- **Similarity:** 0.16
- **Dependents:** 0
- **Priority Score:** 12208.4
- **Functions:** 19/19 matched (target 24)
- **Missing functions:** _none_
- **Types:** 2/3 matched
- **Missing types:** `RegexSetBuilder`

### 5. regex.pattern

- **Target:** `regex.Pattern`
- **Similarity:** 0.48
- **Dependents:** 0
- **Priority Score:** 10605.2
- **Functions:** 3/4 matched (target 10)
- **Missing functions:** `haystack`
- **Types:** 2/2 matched (target 7)
- **Missing types:** _none_

### 6. regex.find_byte

- **Target:** `regex.FindByte`
- **Similarity:** 0.45
- **Dependents:** 0
- **Priority Score:** 205.5
- **Functions:** 2/2 matched (target 5)
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_

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

### Matched

| Source | Target | Path |
|--------|--------|------|
| `regex.regex.bytes` | `commonMain.kotlin.io.github.kotlinmania.regex.regex.Bytes` | `regex/src/regex/bytes` |
| `regexset.bytes` | `regexset.Bytes` | `regex/src/regexset/bytes` |
| `regex.mod` | `regex.Mod` | `regex/src/regex/mod` |
| `regexset.mod` | `regexset.Mod` | `regex/src/regexset/mod` |
| `regex.bytes` | `regex.Bytes` | `regex/src/bytes` |

### Missing

| Source | Expected target | Deps | Source path | Expected path |
|--------|-----------------|------|-------------|---------------|
| `regex.lib` | `regex.src.Lib` | 0 | `regex/src/lib.rs` | `regex/src/Lib.kt` |
| `tests.lib` | `regex.tests.Lib` | 0 | `regex/tests/lib.rs` | `regex/tests/Lib.kt` |

