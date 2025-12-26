package org.chs.lecturelens.data.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.auth.AuthScheme
import io.ktor.http.auth.HttpAuthHeader
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.chs.lecturelens.data.model.local.RefreshToken
import org.chs.lecturelens.domain.repository.AuthRepository
import org.chs.lecturelens.domain.repository.TokenRepository
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module


val networkModule = module {
    single (named("public")){
        HttpClient {
            // 모든 API에 공통으로 적용될 설정
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                })
            }

            // 공통 타임아웃 등 설정
            install(HttpTimeout) {
                requestTimeoutMillis = 15000
            }

            install(Logging) {
                level = LogLevel.ALL
                logger = object : Logger {
                    override fun log(message: String) {
                        println("HTTP Client: $message")
                    }
                }
            }

            // 기본 Base URL 설정)
            defaultRequest {
                contentType(ContentType.Application.Json)
                url("https://lecture-lens.com/api/")
            }
        }
    }

    single (named("withAuth")){
        HttpClient {
            // 모든 API에 공통으로 적용될 설정
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                })
            }

            // 공통 타임아웃 등 설정
            install(HttpTimeout) {
                requestTimeoutMillis = 15000
            }

            // Auth 플러그인 설정
            install(Auth) {
                bearer {
                    loadTokens {
                        val repository = get<TokenRepository>()
                        val access = repository.getAccessToken()
                        val refresh = repository.getRefreshToken()
                        BearerTokens(access, refresh)
                    }

                    refreshTokens {
                        val repository = get<TokenRepository>()
                        val refresh = repository.getRefreshToken()
                        run {
                            val response = get<AuthRepository>().refreshToken(RefreshToken(refresh))
                            repository.saveAccessToken(response.accessToken)
                            repository.saveRefreshToken(response.refreshToken)
                            BearerTokens(repository.getAccessToken(), repository.getRefreshToken())
                        }
                    }
                }
            }

            install(Logging) {
                level = LogLevel.ALL
                logger = object : Logger {
                    override fun log(message: String) {
                        println("HTTP Client: $message")
                    }
                }
            }

            // 기본 Base URL 설정)
            defaultRequest {
                contentType(ContentType.Application.Json)
                url("https://lecture-lens.com/api/")
            }
        }
    }
}