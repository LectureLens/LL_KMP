package org.chs.lecturelens.data.source.auth

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.KtorDsl
import org.chs.lecturelens.data.remote.model.EmailRequest
import org.chs.lecturelens.domain.entities.auth.EmailEntity

class AuthRemoteDataSource(private val client: HttpClient) {
    // 'class io.ktor.utils.io.SourceByteReadChannel
    suspend fun sendEmail(dto: EmailRequest): HttpResponse = client.post("auth/email/send") {
        println("dto: $dto")
        setBody(dto)
    }.body()
}