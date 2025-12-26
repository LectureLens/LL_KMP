package org.chs.lecturelens.domain.repository

interface TokenRepository {
    suspend fun saveAccessToken(token: String)

    suspend fun saveRefreshToken(token: String)

    suspend fun getAccessToken(): String

    suspend fun getRefreshToken(): String

    suspend fun clearToken()
}
