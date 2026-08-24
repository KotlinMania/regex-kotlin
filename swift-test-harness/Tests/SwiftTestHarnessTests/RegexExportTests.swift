import Testing
import Regex

@Suite("Regex Swift Export Tests")
struct RegexExportTests {
    @Test("Swift module loads and basic export smoke test")
    func swiftModuleLoads() {
        #expect(Bool(true))
    }
}
