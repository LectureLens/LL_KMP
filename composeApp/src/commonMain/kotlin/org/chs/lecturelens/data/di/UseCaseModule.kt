package org.chs.lecturelens.data.di

import org.chs.lecturelens.domain.usecase.AuthUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { AuthUseCase(get(),get()) } // Repository를 주입받는 UseCase
}