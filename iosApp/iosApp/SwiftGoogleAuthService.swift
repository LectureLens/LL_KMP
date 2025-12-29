import Foundation
import composeApp
import GoogleSignIn
import FirebaseAuth

class SwiftGoogleAuthService: NSObject, GoogleAuthService {
    
    private func getRootViewController() -> UIViewController? {
        guard let scene = UIApplication.shared.connectedScenes.first as? UIWindowScene else { return nil }
        return scene.windows.first?.rootViewController
    }

    // [수정] 반환 타입을 String?로 일치시킵니다.
    // Kotlin의 suspend는 Swift의 async throws와 대응됩니다.
    func __signIn() async throws -> String? {
        return try await withCheckedThrowingContinuation { continuation in
            guard let rootVC = self.getRootViewController() else {
                continuation.resume(throwing: NSError(domain: "Auth", code: 0))
                return
            }

            GIDSignIn.sharedInstance.signIn(withPresenting: rootVC) { result, error in
                if let error = error {
                    continuation.resume(throwing: error)
                    return
                }
                
                let idToken = result?.user.idToken?.tokenString
                continuation.resume(returning: idToken)
            }
        }
    }

    func __signOut() async throws {
        GIDSignIn.sharedInstance.signOut()
    }
}
