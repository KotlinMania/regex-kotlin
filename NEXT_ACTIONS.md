# Immediate Actions - High-Value Files

Based on AST analysis, here are the concrete next steps.

## Summary

- **Files Present:** 11/12 (91.7%)
- **Function parity:** 63/174 matched (target 113) — 36.2%
- **Class/type parity:** 7/60 matched (target 27) — 11.7%
- **Combined symbol parity:** 70/234 matched (target 140) — 29.9%
- **Average inline-code cosine:** 0.19 (function body across 8 matched files)
- **Average documentation cosine:** 0.33 (doc text across 8 matched files)
- **Cheat-zeroed Files:** 2
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

### 2. error

- **Target:** `regex.Error`
- **Similarity:** 0.15
- **Dependents:** 2
- **Priority Score:** 2030408.5
- **Functions:** 1/3 matched (target 10)
- **Missing functions:** `from_meta_build_error`, `fmt`
- **Types:** 0/1 matched (target 4)
- **Missing types:** `Error`

### 3. regex.bytes

- **Target:** `commonMain.kotlin.io.github.kotlinmania.regex.regex.Bytes`
- **Similarity:** 0.07
- **Dependents:** 0
- **Priority Score:** 667709.3
- **Functions:** 11/56 matched (target 16)
- **Missing functions:** `fmt`, `from_str`, `try_from`, `replacen`, `shortest_match`, `shortest_match_at`, `is_match_at`, `find_at`, `captures_at`, `captures_read`, `captures_read_at`, `read_captures_at`, `capture_names`, `captures_len`, `static_captures_len`, `capture_locations`, `locations`, `start`, `end`, `is_empty`, `len`, `range`, `as_bytes`, `from`, `get`, `get_match`, `name`, `extract`, `expand`, `iter`, `index`, `pos`, `next`, `count`, `size_hint`, `no_expansion`, `by_ref`, `replace_append`, `test_match_properties`, `test_empty_match`, `test_debug_output_valid_utf8`, `test_debug_output_invalid_utf8`, `test_debug_output_various_unicode`, `test_debug_output_ascii_escape`, `test_debug_output_match_in_middle`
- **Types:** 0/21 matched (target 2)
- **Missing types:** `Regex`, `Err`, `Error`, `Match`, `Captures`, `CapturesDebugMap`, `Key`, `Value`, `Output`, `CaptureLocations`, `Locations`, `Matches`, `Item`, `CaptureMatches`, `Split`, `SplitN`, `CaptureNames`, `SubCaptureMatches`, `Replacer`, `ReplacerRef`, `NoExpand`
- **Tests:** 0/7 matched

### 4. regexset.bytes

- **Target:** `regexset.Bytes`
- **Similarity:** 0.13
- **Dependents:** 0
- **Priority Score:** 202708.7
- **Functions:** 7/21 matched (target 9)
- **Missing functions:** `is_match_at`, `matches_at`, `matches_read_at`, `read_matches_at`, `default`, `matched_any`, `matched_all`, `matched`, `iter`, `into_iter`, `next`, `size_hint`, `next_back`, `fmt`
- **Types:** 0/6 matched (target 2)
- **Missing types:** `RegexSet`, `SetMatches`, `IntoIter`, `Item`, `SetMatchesIntoIter`, `SetMatchesIter`

### 5. regexset.string

- **Target:** `regex.RegexSet`
- **Similarity:** 0.26
- **Dependents:** 0
- **Priority Score:** 122707.4
- **Functions:** 13/21 matched (target 26)
- **Missing functions:** `matches_read_at`, `read_matches_at`, `default`, `into_iter`, `next`, `size_hint`, `next_back`, `fmt`
- **Types:** 2/6 matched (target 4)
- **Missing types:** `IntoIter`, `Item`, `SetMatchesIntoIter`, `SetMatchesIter`

### 6. builders

- **Target:** `regex.RegexBuilder`
- **Similarity:** 0.13
- **Dependents:** 0
- **Priority Score:** 82208.7
- **Functions:** 13/19 matched (target 18)
- **Missing functions:** `default`, `new`, `build_one_string`, `build_one_bytes`, `build_many_string`, `build_many_bytes`
- **Types:** 1/3 matched (target 2)
- **Missing types:** `Builder`, `RegexSetBuilder`

### 7. pattern

- **Target:** `regex.Pattern`
- **Similarity:** 0.21
- **Dependents:** 0
- **Priority Score:** 40607.9
- **Functions:** 1/4 matched (target 1)
- **Missing functions:** `into_searcher`, `as_utf8_pattern`, `haystack`
- **Types:** 1/2 matched (target 5)
- **Missing types:** `Searcher`

### 8. find_byte

- **Target:** `regex.FindByte`
- **Similarity:** 0.45
- **Dependents:** 0
- **Priority Score:** 205.5
- **Functions:** 2/2 matched (target 5)
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_

### 9. regex.mod

- **Target:** `regex.Mod [STUB]`
- **Similarity:** 0.00
- **Dependents:** 0
- **Priority Score:** 10.0
- **Functions:** 0/0 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_

### 10. regexset.mod

- **Target:** `regexset.Mod [STUB]`
- **Similarity:** 0.00
- **Dependents:** 0
- **Priority Score:** 10.0
- **Functions:** 0/0 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_

### 11. bytes

- **Target:** `regex.Bytes [STUB]`
- **Similarity:** 1.00
- **Dependents:** 0
- **Priority Score:** 0.0
- **Functions:** 0/0 matched
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

### Missing

| Source | Expected target | Deps | Source path | Expected path |
|--------|-----------------|------|-------------|---------------|
| `lib` | `Lib` | 0 | `lib.rs` | `Lib.kt` |

