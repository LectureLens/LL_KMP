package org.chs.lecturelens.presentation.view.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ProfileSettingsScreen() {
    Column {
        // Notion 스타일의 프로필 섹션
        ListItem(
            headlineContent = { Text("사용자 이름") },
            supportingContent = { Text("user@example.com") },
            leadingContent = { Icon(Icons.Default.Person, null) }
        )
        Divider()
        // 사이드바 하단에 있던 설정 목록들
        val settings = listOf("계정 설정", "알림 설정", "구독 정보", "로그아웃")
        settings.forEach { setting ->
            ListItem(
                headlineContent = { Text(setting) },
                modifier = Modifier.clickable { /* 설정 세부 로직 */ }
            )
        }
    }
}