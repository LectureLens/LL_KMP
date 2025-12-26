package org.chs.lecturelens.domain.repository

import io.ktor.client.statement.HttpResponse
import org.chs.lecturelens.data.model.local.RefreshToken
import org.chs.lecturelens.data.remote.model.response.EmailVerifyResponse
import org.chs.lecturelens.data.remote.model.response.LoginResponse
import org.chs.lecturelens.data.remote.model.response.RefreshTokenResponse
import org.chs.lecturelens.domain.entities.auth.Email
import org.chs.lecturelens.domain.entities.auth.EmailAndCodeEntity
import org.chs.lecturelens.domain.entities.auth.LoginRequestEntity
import org.chs.lecturelens.domain.entities.auth.SignUpEntity

interface AuthRepository {
    suspend fun sendEmail(email: Email): HttpResponse

    suspend fun login(loginEntity: LoginRequestEntity): LoginResponse

    suspend fun sendEmailWithCode(emailWithCode: EmailAndCodeEntity): EmailVerifyResponse

    suspend fun signUp(signUpEntity: SignUpEntity): LoginResponse

    suspend fun refreshToken(refreshToken: RefreshToken): RefreshTokenResponse
}
