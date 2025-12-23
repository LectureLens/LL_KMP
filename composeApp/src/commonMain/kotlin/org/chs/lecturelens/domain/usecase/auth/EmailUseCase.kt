package org.chs.lecturelens.domain.usecase.auth

import io.ktor.client.statement.HttpResponse
import io.ktor.utils.io.ByteReadChannel
import org.chs.lecturelens.domain.entities.auth.EmailEntity
import org.chs.lecturelens.domain.repository.AuthRepository
import kotlin.math.log

class EmailUseCase(private val authRepository: AuthRepository) {
    suspend fun sendEmail(email: EmailEntity): HttpResponse {
        return  try {
            val response = authRepository.sendEmail(email)
            println("sendEmail: $response")
            response
        } catch (e: Exception) {
            println("sendEmail: $e")
            throw e
        }
    }
}