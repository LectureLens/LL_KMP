import SwiftUI
import composeApp

@main
struct iOSApp: App {
    init() {
        KoinKt.doInitKoinIos()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
