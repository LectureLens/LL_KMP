package org.chs.lecturelens.presentation.view

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.SignLanguage
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String,
    val title: String,
    val icon: ImageVector,
) {
    object Home : Screen("home", "홈", Icons.Default.Home)

    object Lectures : Screen("lectures", "강의", Icons.AutoMirrored.Filled.List)

    object Quizzes : Screen("quizzes", "쪽지시험", Icons.Default.Edit)

    object Profile : Screen("profile", "프로필", Icons.Default.AccountCircle)

    // Auth
    object SignUp : Screen("signUp", "회원 가입", Icons.Default.SignLanguage)

    object Login : Screen("login", "로그인", Icons.Default.Email)

    companion object {
        val items = listOf(Home, Lectures, Quizzes, Profile)
    }
}
