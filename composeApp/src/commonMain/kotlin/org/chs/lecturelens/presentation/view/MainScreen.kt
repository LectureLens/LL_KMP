package org.chs.lecturelens.presentation.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.chs.lecturelens.presentation.view.auth.LoginScreen
import org.chs.lecturelens.presentation.view.auth.SignUpScreen
import org.chs.lecturelens.presentation.view.home.HomeScreen
import org.chs.lecturelens.presentation.view.home.LectureListScreen
import org.chs.lecturelens.presentation.view.home.ProfileSettingsScreen
import org.chs.lecturelens.presentation.view.home.QuizListScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val snackBarHostState = remember { SnackbarHostState() }



    Scaffold(
        snackbarHost = { SnackbarHost(snackBarHostState) },
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Lecture Lens") },
                actions = {
                    IconButton(onClick = { /* 알림 이동 로직 */ }) {
                        Icon(imageVector = Icons.Default.Notifications, contentDescription = "알림")
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                Screen.items.forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = screen.title) },
                        label = { Text(screen.title) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) { HomeScreen(navController) }
            composable(Screen.Lectures.route) { LectureListScreen() }
            composable(Screen.Quizzes.route) { QuizListScreen() }
            composable(Screen.Profile.route) { ProfileSettingsScreen() }
            composable(Screen.SignUp.route) {
                SignUpScreen(
                    navController = navController,
                    snackBarHostState = snackBarHostState)
            }
            composable(Screen.Login.route) { LoginScreen(navController = navController) }
        }
    }
}