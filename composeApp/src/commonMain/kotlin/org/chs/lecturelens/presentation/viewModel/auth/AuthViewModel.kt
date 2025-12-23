package org.chs.lecturelens.presentation.viewModel.auth

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.chs.lecturelens.domain.entities.auth.EmailEntity
import org.chs.lecturelens.domain.usecase.auth.EmailUseCase

class AuthViewModel(private val emailUseCase: EmailUseCase) : ViewModel(){
    var email = mutableStateOf("")
    var password = mutableStateOf("")

    fun sendEmail() {
        viewModelScope.launch(Dispatchers.IO) {
            val email = email.value
            if (email.isNotEmpty()) {
                emailUseCase.sendEmail(EmailEntity(email))
            }
        }
    }

    fun updateEmail(newEmail: String) {
        email.value = newEmail
    }
}