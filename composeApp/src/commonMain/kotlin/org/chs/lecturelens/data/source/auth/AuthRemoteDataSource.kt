package org.chs.lecturelens.data.source.auth

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.util.Attributes
import org.chs.lecturelens.data.remote.model.request.EmailRequest
import org.chs.lecturelens.data.remote.model.request.EmailVerifyRequest
import org.chs.lecturelens.data.remote.model.request.LoginRequest
import org.chs.lecturelens.data.remote.model.request.RefreshTokenRequest
import org.chs.lecturelens.data.remote.model.request.SignUpRequest
import org.chs.lecturelens.data.remote.model.response.EmailVerifyResponse
import org.chs.lecturelens.data.remote.model.response.LoginResponse
import org.chs.lecturelens.data.remote.model.response.RefreshTokenResponse
import io.ktor.client.plugins.auth.*
import org.koin.core.component.getScopeName
import org.koin.core.qualifier.named

class AuthRemoteDataSource(private val client: HttpClient ){
    suspend fun sendEmail(dto: EmailRequest): HttpResponse = client.post("auth/email/send") {
        setBody(dto)
    }.body()

    suspend fun login(dto: LoginRequest): LoginResponse = client.post("auth/login") {
        setBody(dto)
    }.body()

    suspend fun sendEmailWithCode(dto: EmailVerifyRequest): EmailVerifyResponse = client.post("auth/email/verify") {
        setBody(dto)
    }.body()

    suspend fun signUp(dto: SignUpRequest): LoginResponse = client.post("auth/signup") {
        setBody(dto)
    }.body()

    suspend fun refreshToken(refreshToken: RefreshTokenRequest): RefreshTokenResponse = client.post("auth/refresh") {
        setBody(refreshToken)
    }.body()
}