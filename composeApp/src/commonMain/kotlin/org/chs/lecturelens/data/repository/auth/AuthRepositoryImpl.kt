package org.chs.lecturelens.data.repository.auth

import io.ktor.client.statement.HttpResponse
import org.chs.lecturelens.data.mapper.auth.toDto
import org.chs.lecturelens.data.model.local.RefreshToken
import org.chs.lecturelens.data.remote.model.response.EmailVerifyResponse
import org.chs.lecturelens.data.remote.model.response.LoginResponse
import org.chs.lecturelens.data.remote.model.response.RefreshTokenResponse
import org.chs.lecturelens.data.source.auth.AuthRemoteDataSource
import org.chs.lecturelens.domain.entities.auth.Email
import org.chs.lecturelens.domain.entities.auth.EmailAndCodeEntity
import org.chs.lecturelens.domain.entities.auth.LoginRequestEntity
import org.chs.lecturelens.domain.entities.auth.SignUpEntity
import org.chs.lecturelens.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val authRemoteDataSource: AuthRemoteDataSource,
) : AuthRepository {
    override suspend fun sendEmail(email: Email): HttpResponse = authRemoteDataSource.sendEmail(email.toDto())

    override suspend fun login(loginEntity: LoginRequestEntity): LoginResponse = authRemoteDataSource.login(loginEntity.toDto())

    override suspend fun sendEmailWithCode(emailWithCode: EmailAndCodeEntity): EmailVerifyResponse =
        authRemoteDataSource.sendEmailWithCode(emailWithCode.toDto())

    override suspend fun signUp(signUpEntity: SignUpEntity): LoginResponse = authRemoteDataSource.signUp(signUpEntity.toDto())

    override suspend fun refreshToken(refreshToken: RefreshToken): RefreshTokenResponse =
        authRemoteDataSource.refreshToken(refreshToken.toDto())
}
