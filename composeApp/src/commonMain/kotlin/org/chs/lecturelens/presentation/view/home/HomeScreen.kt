package org.chs.lecturelens.presentation.view.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    // UI 로직: 파일 선택 버튼, 업로드 상태 표시 등
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = { /* 녹음/텍스트 파일 선택 로직 */ }) {
            Text("강의 생성하기 (파일 선택)")
        }

        Button(onClick = {
            navController.navigate("email")
        }) {
            Text("이메일 보내기")
        }
    }
}