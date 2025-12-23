package org.chs.lecturelens.domain.repository

import io.ktor.client.statement.HttpResponse
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.KtorDsl
import org.chs.lecturelens.data.remote.model.EmailRequest
import org.chs.lecturelens.domain.entities.auth.EmailEntity

interface AuthRepository {
    suspend fun sendEmail(email: EmailEntity): HttpResponse
}
