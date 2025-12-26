package org.chs.lecturelens.presentation.viewModel.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.chs.lecturelens.data.mapper.auth.toEntity
import org.chs.lecturelens.domain.entities.auth.Code
import org.chs.lecturelens.domain.entities.auth.Email
import org.chs.lecturelens.domain.entities.auth.EmailAndCodeEntity
import org.chs.lecturelens.domain.entities.auth.LoginRequestEntity
import org.chs.lecturelens.domain.entities.auth.Name
import org.chs.lecturelens.domain.entities.auth.Password
import org.chs.lecturelens.domain.entities.auth.PhoneNumber
import org.chs.lecturelens.domain.entities.auth.SignUpEntity
import org.chs.lecturelens.domain.entities.auth.UserId
import org.chs.lecturelens.domain.usecase.AuthUseCase

data class AuthUiState(
    var userId: String = "",
    var name: String = "",
    var email: String = "",
    var emailCode: String = "",
    var password: String = "",
    var passwordCheck: String = "",
    var phone: String = "",
    var codeTextFieldEnabled: Boolean = false,
    var codeButtonEnabled: Boolean = true,
    var isPasswordVisible: Boolean = false,
    var isPasswordCheckVisible: Boolean = false
)

class AuthViewModel(private val authUseCase: AuthUseCase) : ViewModel(){
    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState = _uiState.asStateFlow()

    // UI에 보낼 일회성 이벤트를 위한 채널
    private val _effect = Channel<AuthEffect>()
    val effect = _effect.receiveAsFlow()

    // UI에서 보여줄 효과들을 정의 (Sealed Interface)
    sealed interface AuthEffect {
        data class ShowSnackBar(val message: String) : AuthEffect
        object NavigateToMain : AuthEffect // 네비게이션도 이런 식으로 처리 가능합니다
    }
    fun login() {
        viewModelScope.launch(Dispatchers.IO) {
            val userId = _uiState.value.userId
            val password = _uiState.value.password
            try {
                val response = authUseCase.login(LoginRequestEntity(UserId(userId), Password(password))).toEntity()
                _effect.send(AuthEffect.ShowSnackBar(response.toString()))
                println(response.toString())
            } catch (e: Exception) {
                _effect.send(AuthEffect.ShowSnackBar(e.toString()))
                println(e.toString())
            }
        }
    }

    fun signUp() {
        viewModelScope.launch(Dispatchers.IO) {
            val userId = _uiState.value.userId
            val name = _uiState.value.name
            val email = _uiState.value.email
            val password = _uiState.value.password
            val passwordCheck = _uiState.value.passwordCheck
            val phone = _uiState.value.phone
            if (password == passwordCheck) {
                try {
                    authUseCase.signUp(SignUpEntity(
                        UserId(userId),
                        Email(email),
                        Name(name),
                        Password(password),
                        PhoneNumber(phone)
                    ))
                } catch (e: Exception) {
                    _effect.send(AuthEffect.ShowSnackBar(e.toString()))
                }

            } else {
                println("비밀번호가 일치하지 않습니다.")
            }
        }
    }

    fun sendEmail() {
        _uiState.update { it.copy(codeTextFieldEnabled = true) }
        viewModelScope.launch(Dispatchers.IO) {
            val email = _uiState.value.email
            if (email.isNotEmpty()) {
                try {
                    authUseCase.sendEmail(Email(email))
                    _effect.send(AuthEffect.ShowSnackBar("이메일 전송 성공"))
                } catch (e: Exception) {
                    _effect.send(AuthEffect.ShowSnackBar(e.toString()))
                }
            }
        }
    }

    fun sendEmailWithCode() {
        viewModelScope.launch(Dispatchers.IO) {
            val email = _uiState.value.email
            val emailCode = _uiState.value.emailCode
            try {
                val response = authUseCase.sendEmailWithCode(EmailAndCodeEntity(
                    Email(email),
                    Code(emailCode)
                ))
                if (response.verified) {
                    _uiState.update { it.copy(codeButtonEnabled = false) }
                    _effect.send(AuthEffect.ShowSnackBar("이메일 인증 성공"))
                    println("이메일 인증 성공")
                }
            }catch (e: Exception) {
                _effect.send(AuthEffect.ShowSnackBar(e.toString()))
            }
        }
    }

    fun updateIsPasswordVisible() {
        _uiState.update { currentState ->
            currentState.copy(
                isPasswordVisible = !currentState.isPasswordVisible
            )
        }
    }

    fun updateIsPasswordCheckVisible() {
        _uiState.update { currentState ->
            currentState.copy(
                isPasswordCheckVisible = !currentState.isPasswordCheckVisible
            )
        }
    }
    fun updateUserId(newUserId: String) {
        _uiState.update { currentState ->
            currentState.copy(userId = newUserId)
        }
    }

    fun updateName(newName: String) {
        _uiState.update { currentState ->
            currentState.copy(
                name = newName
            )
        }
    }

    fun updateEmail(newEmail: String) {
        _uiState.update { currentState ->
            currentState.copy(
                email = newEmail
            )
        }
    }

    fun updateEmailCode(newEmailCode: String) {
        _uiState.update { currentState ->
            currentState.copy(
                emailCode = newEmailCode
            )
        }
    }

    fun updatePassword(newPassword: String) {
        _uiState.update { currentState ->
            currentState.copy(
                password = newPassword
            )
        }
    }

    fun updatePasswordCheck(newPasswordCheck: String) {
        _uiState.update { currentState ->
            currentState.copy(
                passwordCheck = newPasswordCheck
            )
        }
    }

    fun updatePhone(newPhone: String) {
        _uiState.update { currentState ->
            currentState.copy(
                phone = newPhone
            )
        }
    }


}