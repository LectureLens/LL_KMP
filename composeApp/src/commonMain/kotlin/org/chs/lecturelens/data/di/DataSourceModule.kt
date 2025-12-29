package org.chs.lecturelens.data.di

import org.chs.lecturelens.data.source.auth.AuthRemoteDataSource
import org.chs.lecturelens.data.source.auth.TokenLocalDataSource
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataSourceModule =
    module {
        single { AuthRemoteDataSource(get(named("public"))) }
        single { TokenLocalDataSource(get()) }
    }
