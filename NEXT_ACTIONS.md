# Immediate Actions - High-Value Files

Based on AST analysis, here are the concrete next steps.

## Summary

- **Files Present:** 3/12 (25.0%)
- **Function parity:** 2/321 matched (target 2) — 0.6%
- **Class/type parity:** 0/60 matched — 0.0%
- **Combined symbol parity:** 2/381 matched (target 2) — 0.5%
- **Average inline-code cosine:** 0.45 (function body across 1 matched files)
- **Average documentation cosine:** 0.70 (doc text across 1 matched files)
- **Cheat-zeroed Files:** 2
- **Critical Issues:** 3 files with <0.60 function similarity

## Priority 1: Fix Incomplete High-Dependency Files

No incomplete high-dependency files detected.

## Priority 2: Port Missing High-Value Files

Critical missing files (>10 dependencies):

No missing high-value files detected.

## Detailed Work Items

Every matched file is listed below with function and type symbol parity.

### 1. find_byte

- **Target:** `regex.FindByte`
- **Similarity:** 0.45
- **Dependents:** 0
- **Priority Score:** 205.5
- **Functions:** 2/2 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched
- **Missing types:** _none_

### 2. regex.mod

- **Target:** `regex.Mod [STUB] [PROVENANCE-FALLBACK]`
- **Similarity:** 0.00
- **Dependents:** 0
- **Priority Score:** 10.0
- **Functions:** 0/0 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched
- **Missing types:** _none_
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `src/regex/mod.rs` vs expected `regex/mod.rs`
- **Proposed provenance header:** `// port-lint: source regex/mod.rs` (current: `// port-lint: source src/regex/mod.rs`)
- **Lint issues:** 1

### 3. regexset.mod

- **Target:** `regexset.Mod [STUB] [PROVENANCE-FALLBACK]`
- **Similarity:** 0.00
- **Dependents:** 0
- **Priority Score:** 10.0
- **Functions:** 0/0 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched
- **Missing types:** _none_
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `src/regexset/mod.rs` vs expected `regexset/mod.rs`
- **Proposed provenance header:** `// port-lint: source regexset/mod.rs` (current: `// port-lint: source src/regexset/mod.rs`)
- **Lint issues:** 1

## Success Criteria

For each file to be considered "complete":
- **Similarity ≥ 0.85** (Excellent threshold)
- All public APIs ported
- All tests ported
- Documentation ported
- port-lint header present

## Next Commands

```bash
# Initialize task queue for systematic porting
cd tools/ast_distance
./ast_distance --init-tasks ../../tmp/regex/src rust ../../src/commonMain/kotlin/io/github/kotlinmania/regex kotlin tasks.json ../../AGENTS.md

# Get next high-priority task
./ast_distance --assign tasks.json <agent-id>
```
## Reexport / Wiring Modules

These files match `reexport_modules` patterns in `.ast_distance_config.json`. They are filtered out of
normal priority and missing-file ladders because they are wiring
modules, not direct logic ports. Consult them for call-site routing;
do not treat them as the next implementation target by default.

### Missing

| Source | Expected target | Deps | Source path | Expected path |
|--------|-----------------|------|-------------|---------------|
| `lib` | `Lib` | 0 | `lib.rs` | `Lib.kt` |

