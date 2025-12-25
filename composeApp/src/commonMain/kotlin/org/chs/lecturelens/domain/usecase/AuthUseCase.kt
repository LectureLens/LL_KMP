package org.chs.lecturelens.domain.usecase

import io.ktor.client.statement.HttpResponse
import org.chs.lecturelens.data.remote.model.response.EmailVerifyResponse
import org.chs.lecturelens.data.remote.model.response.LoginResponse
import org.chs.lecturelens.domain.entities.auth.Email
import org.chs.lecturelens.domain.entities.auth.EmailAndCode
import org.chs.lecturelens.domain.entities.auth.LoginRequestEntity
import org.chs.lecturelens.domain.entities.auth.SignUpEntity
import org.chs.lecturelens.domain.repository.AuthRepository

class AuthUseCase(private val authRepository: AuthRepository) {
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
            val response = authRepository.login(loginEntity)
            println("login: $response")
            response
        } catch (e: Exception) {
            println("login: $e")
            throw e
        }
    }
    suspend fun signUp(signUpEntity: SignUpEntity): LoginResponse {
        return try {
            val response = authRepository.signUp(signUpEntity)
            println("signUp: $response")
            response
        } catch (e: Exception) {
            println("signUp: $e")
            throw e
        }
    }

    suspend fun sendEmailWithCode(emailWithCode: EmailAndCode): EmailVerifyResponse {
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