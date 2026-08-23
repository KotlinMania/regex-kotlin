# Immediate Actions - High-Value Files

Based on AST analysis, here are the concrete next steps.

## Summary

- **Files Present:** 9/12 (75.0%)
- **Function parity:** 48/221 matched (target 72) — 21.7%
- **Class/type parity:** 7/60 matched (target 18) — 11.7%
- **Combined symbol parity:** 55/281 matched (target 90) — 19.6%
- **Average inline-code cosine:** 0.17 (function body across 6 matched files)
- **Average documentation cosine:** 0.40 (doc text across 6 matched files)
- **Cheat-zeroed Files:** 4
- **Critical Issues:** 9 files with <0.60 function similarity

## Priority 1: Fix Incomplete High-Dependency Files

No incomplete high-dependency files detected.

## Priority 2: Port Missing High-Value Files

Critical missing files (>10 dependencies):

No missing high-value files detected.

## Detailed Work Items

Every matched file is listed below with function and type symbol parity.

### 1. regex.string

- **Target:** `regex.Match [ZERO]`
- **Similarity:** 0.00
- **Dependents:** 2
- **Priority Score:** 2496910.0
- **Functions:** 17/48 matched (target 25)
- **Missing functions:** `fmt`, `from_str`, `try_from`, `replacen`, `shortest_match`, `shortest_match_at`, `is_match_at`, `find_at`, `captures_at`, `captures_read`, `captures_read_at`, `read_captures_at`, `capture_names`, `captures_len`, `static_captures_len`, `capture_locations`, `locations`, `is_empty`, `range`, `from`, `get_match`, `expand`, `iter`, `index`, `pos`, `next`, `count`, `size_hint`, `no_expansion`, `by_ref`, `replace_append`
- **Types:** 3/21 matched (target 3)
- **Missing types:** `Err`, `Error`, `CapturesDebugMap`, `Key`, `Value`, `Output`, `CaptureLocations`, `Locations`, `Matches`, `Item`, `CaptureMatches`, `Split`, `SplitN`, `CaptureNames`, `SubCaptureMatches`, `Replacer`, `ReplacerRef`, `NoExpand`

### 2. error

- **Target:** `regex.Error`
- **Similarity:** 0.15
- **Dependents:** 2
- **Priority Score:** 2030408.5
- **Functions:** 1/3 matched (target 8)
- **Missing functions:** `from_meta_build_error`, `fmt`
- **Types:** 0/1 matched (target 3)
- **Missing types:** `Error`

### 3. regexset.string

- **Target:** `regex.RegexSet`
- **Similarity:** 0.26
- **Dependents:** 0
- **Priority Score:** 122707.4
- **Functions:** 13/21 matched (target 22)
- **Missing functions:** `matches_read_at`, `read_matches_at`, `default`, `into_iter`, `next`, `size_hint`, `next_back`, `fmt`
- **Types:** 2/6 matched (target 3)
- **Missing types:** `IntoIter`, `Item`, `SetMatchesIntoIter`, `SetMatchesIter`

### 4. builders

- **Target:** `regex.RegexBuilder`
- **Similarity:** 0.13
- **Dependents:** 0
- **Priority Score:** 82208.7
- **Functions:** 13/19 matched (target 13)
- **Missing functions:** `default`, `new`, `build_one_string`, `build_one_bytes`, `build_many_string`, `build_many_bytes`
- **Types:** 1/3 matched (target 1)
- **Missing types:** `Builder`, `RegexSetBuilder`

### 5. pattern

- **Target:** `regex.Pattern [ZERO]`
- **Similarity:** 0.00
- **Dependents:** 0
- **Priority Score:** 30610.0
- **Functions:** 2/4 matched (target 2)
- **Missing functions:** `into_searcher`, `as_utf8_pattern`
- **Types:** 1/2 matched (target 5)
- **Missing types:** `Searcher`

### 6. find_byte

- **Target:** `regex.FindByte`
- **Similarity:** 0.45
- **Dependents:** 0
- **Priority Score:** 205.5
- **Functions:** 2/2 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched
- **Missing types:** _none_

### 7. regex.mod

- **Target:** `regex.Mod [STUB]`
- **Similarity:** 0.00
- **Dependents:** 0
- **Priority Score:** 10.0
- **Functions:** 0/0 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_

### 8. regexset.mod

- **Target:** `regexset.Mod [STUB]`
- **Similarity:** 0.00
- **Dependents:** 0
- **Priority Score:** 10.0
- **Functions:** 0/0 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_

### 9. bytes

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

