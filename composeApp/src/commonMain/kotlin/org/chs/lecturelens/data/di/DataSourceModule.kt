package org.chs.lecturelens.data.di

import org.chs.lecturelens.data.repository.auth.AuthRepositoryImpl
import org.chs.lecturelens.data.source.auth.AuthRemoteDataSource
import org.chs.lecturelens.domain.repository.AuthRepository
import org.koin.dsl.module

val dataSourceModule = module {
    single { AuthRemoteDataSource(get()) }
}

