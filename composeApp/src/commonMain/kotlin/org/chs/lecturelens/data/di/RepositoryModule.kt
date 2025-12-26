package org.chs.lecturelens.data.di

import org.chs.lecturelens.data.repository.auth.AuthRepositoryImpl
import org.chs.lecturelens.data.repository.auth.TokenRepositoryImpl
import org.chs.lecturelens.domain.repository.AuthRepository
import org.chs.lecturelens.domain.repository.TokenRepository
import org.koin.dsl.module

val repositoryModule =
    module {
        single<AuthRepository> {
            AuthRepositoryImpl(
                get(),
            )
        }

        single<TokenRepository> {
            TokenRepositoryImpl(
                get(),
            )
        }
    }
