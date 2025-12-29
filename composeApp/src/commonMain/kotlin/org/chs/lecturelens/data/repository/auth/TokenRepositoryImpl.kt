package org.chs.lecturelens.data.repository.auth

import org.chs.lecturelens.data.source.auth.TokenLocalDataSource
import org.chs.lecturelens.domain.repository.TokenRepository

class TokenRepositoryImpl(
    private val tokenLocalDataSource: TokenLocalDataSource,
) : TokenRepository {
    override suspend fun saveAccessToken(token: String) = tokenLocalDataSource.saveAccessToken(token)

    override suspend fun saveRefreshToken(token: String) = tokenLocalDataSource.saveRefreshToken(token)

    override suspend fun getAccessToken(): String = tokenLocalDataSource.getAccessToken() ?: ""

    override suspend fun getRefreshToken(): String = tokenLocalDataSource.getRefreshToken() ?: ""

    override suspend fun clearToken() = tokenLocalDataSource.clearToken()
}
