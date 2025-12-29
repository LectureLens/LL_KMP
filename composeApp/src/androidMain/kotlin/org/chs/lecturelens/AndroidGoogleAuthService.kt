package org.chs.lecturelens

import android.content.Context
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.GoogleAuthProvider
import dev.gitlive.firebase.auth.auth
import org.chs.lecturelens.domain.repository.GoogleAuthService

class AndroidGoogleAuthService(
    private val context: Context,
    private val webClientId: String,
) : GoogleAuthService {
    private val auth = Firebase.auth
    private val credentialManager = CredentialManager.create(context)

    override suspend fun signIn(): String? =
        try {
            val googleIdOption =
                GetGoogleIdOption
                    .Builder()
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId(webClientId)
                    .build()

            val request =
                GetCredentialRequest
                    .Builder()
                    .addCredentialOption(googleIdOption)
                    .build()

            val result = credentialManager.getCredential(context, request)
            val googleCredential = GoogleIdTokenCredential.createFrom(result.credential.data)

            // Firebase 로그인 수행
            val firebaseCredential = GoogleAuthProvider.credential(googleCredential.idToken, null)
            val authResult = auth.signInWithCredential(firebaseCredential)

            // [수정 핵심] FirebaseUser 객체가 아닌, 구글 ID 토큰(String)을 반환해야 합니다.
            googleCredential.idToken
        } catch (e: Exception) {
            println("로그인 실패: ${e.message}")
            null // 에러 발생 시 명시적으로 null 반환
        }

    override suspend fun signOut() {
        auth.signOut()
        credentialManager.clearCredentialState(ClearCredentialStateRequest())
    }
}
