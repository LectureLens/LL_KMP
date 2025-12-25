package org.chs.lecturelens.presentation.view.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
fun LoginScreen(viewModel: AuthViewModel = koinViewModel(), navController: NavController) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("사용자 ID")
        TextField(
            value = state.userId,
            onValueChange = { viewModel.updateUserId(it) },
            label = { Text("ID를 입력하세요") }
        )
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
                        contentDescription = if (state.isPasswordVisible) "비밀번호 숨기기" else "비밀번호 보기"
                    )
                }
            }
        )
        Button(onClick = {
            viewModel.login()
        }) {
            Text("로그인")
        }
        Text("또는")
        Button(onClick = {
        }) {
            Text("google로 로그인")
        }
        Row {
            Text("계정이 없으신가요?")
            Button(onClick = {
                navController.navigate(Screen.SignUp.route)
            }) {
                Text("회원 가입")
            }
        }
    }
}