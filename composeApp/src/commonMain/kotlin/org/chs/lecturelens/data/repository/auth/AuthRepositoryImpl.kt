package org.chs.lecturelens.data.repository.auth

import io.ktor.client.statement.HttpResponse
import io.ktor.utils.io.ByteReadChannel
import org.chs.lecturelens.data.mapper.auth.toDto
import org.chs.lecturelens.data.source.auth.AuthRemoteDataSource
import org.chs.lecturelens.domain.entities.auth.EmailEntity
import org.chs.lecturelens.domain.repository.AuthRepository

class AuthRepositoryImpl(private val authRemoteDataSource: AuthRemoteDataSource) : AuthRepository {
    override suspend fun sendEmail(email: EmailEntity): HttpResponse {
        return authRemoteDataSource.sendEmail(email.toDto())
    }
}