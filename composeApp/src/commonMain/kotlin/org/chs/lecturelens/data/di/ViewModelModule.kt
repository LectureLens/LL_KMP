package org.chs.lecturelens.data.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import org.chs.lecturelens.presentation.viewModel.auth.AuthViewModel
import org.koin.dsl.module

val viewModelModule = module {
    factory { AuthViewModel(get()) }

}