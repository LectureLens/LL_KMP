package org.chs.lecturelens.data.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

class Koin {
    fun initKoin(appDeclaration: KoinAppDeclaration) = startKoin {
        appDeclaration()
        modules(networkModule, dataSourceModule, repositoryModule, useCaseModule, viewModelModule)
    }

    fun initKoinIos() = initKoin { }

}