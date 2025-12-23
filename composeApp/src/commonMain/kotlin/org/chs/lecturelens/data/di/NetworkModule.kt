package org.chs.lecturelens.data.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module


val networkModule = module {
    single {
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

            // 기본 Base URL 설정)
            defaultRequest {
                contentType(ContentType.Application.Json)
                url("https://lecture-lens.com/api/")
            }
        }
    }
}