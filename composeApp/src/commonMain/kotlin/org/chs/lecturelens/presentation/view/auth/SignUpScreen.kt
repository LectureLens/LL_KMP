package org.chs.lecturelens.presentation.view.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import org.chs.lecturelens.presentation.view.Screen
import org.chs.lecturelens.presentation.viewModel.auth.AuthViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SignUpScreen(
    viewModel: AuthViewModel = koinViewModel(),
    navController: NavController,
    snackBarHostState: SnackbarHostState,
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is AuthViewModel.AuthEffect.ShowSnackBar -> {
                    // 실제 UI 단에서 스낵바를 호출합니다.
                    snackBarHostState.showSnackbar(
                        message = effect.message,
                        withDismissAction = true,
                        duration = SnackbarDuration.Short,
                    )
                }

                is AuthViewModel.AuthEffect.NavigateToMain -> {
                    // 네비게이션 처리
                }
            }
        }
    }

    LazyColumn {
        item {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text("사용자 ID")
                TextField(
                    value = state.userId,
                    onValueChange = { viewModel.updateUserId(it) },
                    label = { Text("ID를 입력하세요") },
                )
                Text("이름")
                TextField(
                    value = state.name,
                    onValueChange = { viewModel.updateName(it) },
                    label = { Text("이름을 입력하세요") },
                )
                // TextField for email
                Text("이메일")
                Row {
                    TextField(
                        value = state.email,
                        onValueChange = { viewModel.updateEmail(it) },
                        label = { Text("이메일을 입력하세요") },
                    )
                    Button(onClick = {
                        viewModel.sendEmail()
                    }) {
                        Text("인증하기")
                    }
                }

                // TextField for email code
                if (state.codeTextFieldEnabled) {
                    Row {
                        TextField(
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                            value = state.emailCode,
                            onValueChange = { viewModel.updateEmailCode(it) },
                            label = { Text("인증 코드 6자리") },
                        )
                        Button(enabled = state.codeButtonEnabled, onClick = {
                            viewModel.sendEmailWithCode()
                        }) {
                            Text("확인")
                        }
                    }
                }

                Text("비밀번호")
                TextField(
                    value = state.password,
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
                    visualTransformation = if (state.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    onValueChange = { viewModel.updatePassword(it) },
                    label = { Text("비밀번호를 입력하세요 (최소 8자)") },
                    trailingIcon = {
                        IconButton(onClick = { viewModel.updateIsPasswordVisible() }) {
                            Icon(
                                imageVector = if (state.isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                contentDescription = if (state.isPasswordVisible) "비밀번호 숨기기" else "비밀번호 보기",
                            )
                        }
                    },
                )

                Text("비밀번호 확인")
                TextField(
                    value = state.passwordCheck,
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
                    visualTransformation = if (state.isPasswordCheckVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    onValueChange = { viewModel.updatePasswordCheck(it) },
                    label = { Text("비밀번호를 다시 입력하세요") },
                    trailingIcon = {
                        IconButton(onClick = { viewModel.updateIsPasswordCheckVisible() }) {
                            Icon(
                                imageVector = if (state.isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                contentDescription = if (state.isPasswordVisible) "비밀번호 숨기기" else "비밀번호 보기",
                            )
                        }
                    },
                )
                Text("전화번호")
                TextField(
                    value = state.phone,
                    onValueChange = { viewModel.updatePhone(it) },
                    label = { Text("휴대폰 번호를 입력하세요 (예: 01012345678)") },
                )
                Button(onClick = {
                    viewModel.signUp()
                }) {
                    Text("회원가입")
                }
                Text("또는")
                Button(onClick = {
                }) {
                    Text("google로 회원가입")
                }
                Row {
                    Text("이미 계정이 있으신가요?")
                    Button(onClick = {
                        navController.navigate(Screen.Login.route)
                    }) {
                        Text("로그인")
                    }
                }
            }
        }
    }
}
