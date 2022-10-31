import Foundation

@objc public class W3aCustom: NSObject {
    @objc public func echo(_ value: String) -> String {
        print(value)
        return value
    }
}
