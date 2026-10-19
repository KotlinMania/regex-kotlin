# Immediate Actions - High-Value Files

Based on AST analysis, here are the concrete next steps.

## Summary

- **Files Present:** 9/12 (75.0%)
- **Function parity:** 79/97 matched (target 166) — 81.4%
- **Class/type parity:** 18/33 matched (target 32) — 54.5%
- **Combined symbol parity:** 97/130 matched (target 198) — 74.6%
- **Average inline-code cosine:** 0.27 (function body across 8 matched files)
- **Average documentation cosine:** 0.34 (doc text across 8 matched files)
- **Cheat-zeroed Files:** 0
- **Critical Issues:** 9 files with <0.60 function similarity

## Priority 1: Fix Incomplete High-Dependency Files

No incomplete high-dependency files detected.

## Priority 2: Port Missing High-Value Files

Critical missing files (>10 dependencies):

No missing high-value files detected.

## Detailed Work Items

Every matched file is listed below with function and type symbol parity.

### 1. regex.string

- **Target:** `regex.Match`
- **Similarity:** 0.35
- **Dependents:** 2
- **Priority Score:** 2176906.5
- **Functions:** 40/48 matched (target 90)
- **Missing functions:** `fmt`, `start`, `end`, `range`, `from`, `get_match`, `index`, `next`
- **Types:** 12/21 matched (target 13)
- **Missing types:** `Err`, `Error`, `CapturesDebugMap`, `Key`, `Value`, `Output`, `Item`, `Replacer`, `ReplacerRef`

### 2. error

- **Target:** `regex.Error`
- **Similarity:** 0.28
- **Dependents:** 2
- **Priority Score:** 2020407.1
- **Functions:** 2/3 matched (target 11)
- **Missing functions:** `fmt`
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

### 4. builders

- **Target:** `regex.RegexBuilder`
- **Similarity:** 0.16
- **Dependents:** 0
- **Priority Score:** 12208.4
- **Functions:** 19/19 matched (target 24)
- **Missing functions:** _none_
- **Types:** 2/3 matched
- **Missing types:** `RegexSetBuilder`

### 5. pattern

- **Target:** `regex.Pattern`
- **Similarity:** 0.48
- **Dependents:** 0
- **Priority Score:** 10605.2
- **Functions:** 3/4 matched (target 10)
- **Missing functions:** `haystack`
- **Types:** 2/2 matched (target 7)
- **Missing types:** _none_

### 6. find_byte

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
| `regex.bytes` | `commonMain.kotlin.io.github.kotlinmania.regex.regex.Bytes` | `regex/bytes` |
| `regexset.bytes` | `regexset.Bytes` | `regexset/bytes` |
| `bytes` | `regex.Bytes` | `bytes` |

### Missing

| Source | Expected target | Deps | Source path | Expected path |
|--------|-----------------|------|-------------|---------------|
| `lib` | `Lib` | 0 | `lib.rs` | `Lib.kt` |
| `regex.mod` | `regex.Mod` | 0 | `regex/mod.rs` | `regex/Mod.kt` |
| `regexset.mod` | `regexset.Mod` | 0 | `regexset/mod.rs` | `regexset/Mod.kt` |

