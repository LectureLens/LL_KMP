package org.chs.lecturelens.data.di

import org.chs.lecturelens.domain.repository.GoogleAuthService
import org.chs.lecturelens.platformModule // 이제 정확히 임포트됩니다.
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration) =
    startKoin {
        appDeclaration()
        // 여기에 platformModule이 반드시 포함되어야 DataStore를 찾을 수 있습니다.
        modules(
            platformModule,
            networkModule,
            dataSourceModule,
            repositoryModule,
            useCaseModule,
            viewModelModule,
        )
    }

fun initKoinIos(
    googleAuthService: GoogleAuthService, // [수정] Swift에서 구현체를 받기 위한 인자 추가
) = initKoin {
    modules(
        module {
            single { googleAuthService } // [추가] 외부에서 주입된 서비스를 Koin에 등록
        },
    )
}
