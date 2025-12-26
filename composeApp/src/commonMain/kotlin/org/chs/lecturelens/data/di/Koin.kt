package org.chs.lecturelens.data.di

import org.chs.lecturelens.platformModule // 이제 정확히 임포트됩니다.
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration) = startKoin {
    appDeclaration()
    // 여기에 platformModule이 반드시 포함되어야 DataStore를 찾을 수 있습니다.
    modules(
        platformModule,
        networkModule,
        dataSourceModule,
        repositoryModule,
        useCaseModule,
        viewModelModule
    )
}

fun initKoinIos() = initKoin { }