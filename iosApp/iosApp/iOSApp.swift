import SwiftUI
import composeApp
import FirebaseCore

@main
struct iOSApp: App {
    init() {
        // 1. [Official] Firebase 초기화
        FirebaseApp.configure()
        
        KoinKt.doInitKoinIos(googleAuthService: SwiftGoogleAuthService())
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
