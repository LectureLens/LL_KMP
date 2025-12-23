package org.chs.lecturelens.presentation.view.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Home : Screen("home", "홈", Icons.Default.Home)
    object Lectures : Screen("lectures", "강의", Icons.Default.List)
    object Quizzes : Screen("quizzes", "쪽지시험", Icons.Default.Edit)
    object Profile : Screen("profile", "프로필", Icons.Default.AccountCircle)
    object Email : Screen("email", "이메일 인증", Icons.Default.Email)


    companion object {
        val items = listOf(Home, Lectures, Quizzes, Profile)
    }
}