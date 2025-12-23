package org.chs.lecturelens.data.di

import org.chs.lecturelens.domain.usecase.auth.EmailUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { EmailUseCase(get()) } // Repository를 주입받는 UseCase
}