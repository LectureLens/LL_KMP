package org.chs.lecturelens.data.repository.auth

import io.ktor.client.statement.HttpResponse
import org.chs.lecturelens.data.mapper.auth.toDto
import org.chs.lecturelens.data.remote.model.response.EmailVerifyResponse
import org.chs.lecturelens.data.remote.model.response.LoginResponse
import org.chs.lecturelens.data.source.auth.AuthRemoteDataSource
import org.chs.lecturelens.domain.entities.auth.Email
import org.chs.lecturelens.domain.entities.auth.EmailAndCode
import org.chs.lecturelens.domain.entities.auth.LoginRequestEntity
import org.chs.lecturelens.domain.entities.auth.SignUpEntity
import org.chs.lecturelens.domain.repository.AuthRepository

class AuthRepositoryImpl(private val authRemoteDataSource: AuthRemoteDataSource) : AuthRepository {
    override suspend fun sendEmail(email: Email): HttpResponse {
        return authRemoteDataSource.sendEmail(email.toDto())
    }

    override suspend fun login(loginEntity: LoginRequestEntity): LoginResponse {
        return authRemoteDataSource.login(loginEntity.toDto())
    }

    override suspend fun sendEmailWithCode(emailWithCode: EmailAndCode): EmailVerifyResponse {
        return authRemoteDataSource.sendEmailWithCode(emailWithCode.toDto())
    }

    override suspend fun signUp(signUpEntity: SignUpEntity): LoginResponse {
        println("signUp: ${signUpEntity.toDto()}")
        return authRemoteDataSource.signUp(signUpEntity.toDto())
    }
}