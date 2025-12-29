package org.chs.lecturelens.domain.repository

interface GoogleAuthService {
    suspend fun signIn(): String?

    suspend fun signOut()
}
