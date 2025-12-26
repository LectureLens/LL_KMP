package org.chs.lecturelens.domain.usecase

import io.ktor.client.statement.HttpResponse
import org.chs.lecturelens.data.remote.model.response.EmailVerifyResponse
import org.chs.lecturelens.data.remote.model.response.LoginResponse
import org.chs.lecturelens.domain.entities.auth.Email
import org.chs.lecturelens.domain.entities.auth.EmailAndCodeEntity
import org.chs.lecturelens.domain.entities.auth.LoginRequestEntity
import org.chs.lecturelens.domain.entities.auth.SignUpEntity
import org.chs.lecturelens.domain.repository.AuthRepository
import org.chs.lecturelens.domain.repository.TokenRepository

class AuthUseCase(private val authRepository: AuthRepository,
    private val tokenRepository: TokenRepository
) {
    suspend fun sendEmail(email: Email): HttpResponse {
        return try {
            val response = authRepository.sendEmail(email)
            println("sendEmail: $response")
            response
        } catch (e: Exception) {
            println("sendEmail: $e")
            throw e
        }
    }

    suspend fun login(loginEntity: LoginRequestEntity): LoginResponse {
        return try {
            tokenRepository.clearToken()
            val response = authRepository.login(loginEntity)
            tokenRepository.saveAccessToken(response.accessToken)
            tokenRepository.saveRefreshToken(response.refreshToken)
            println("login: $response")
            response
        } catch (e: Exception) {
            println("login: $e")
            throw e
        }
    }
    suspend fun signUp(signUpEntity: SignUpEntity): LoginResponse {
        return try {
            tokenRepository.clearToken()
            val response = authRepository.signUp(signUpEntity)
            tokenRepository.saveAccessToken(response.accessToken)
            tokenRepository.saveRefreshToken(response.refreshToken)
            println("signUp: $response")
            response
        } catch (e: Exception) {
            println("signUp: $e")
            throw e
        }
    }

    suspend fun sendEmailWithCode(emailWithCode: EmailAndCodeEntity): EmailVerifyResponse {
        return try {
            val response = authRepository.sendEmailWithCode(emailWithCode)
            println("sendEmailWithCode: $response")
            response
        } catch (e: Exception) {
            println("sendEmailWithCode: $e")
            throw e
        }
    }
}