import SwiftUI

@main
struct iOSApp: App {
    init() {
        Koinkt.doInitKoinIos()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}