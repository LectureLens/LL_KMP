package org.chs.lecturelens.data.di

import org.chs.lecturelens.presentation.viewModel.auth.AuthViewModel
import org.koin.dsl.module

val viewModelModule =
    module {
        factory { AuthViewModel(get()) }
    }
