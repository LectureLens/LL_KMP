package org.chs.lecturelens.presentation.view.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.chs.lecturelens.presentation.view.Screen.Companion.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description

@Composable
fun LectureListScreen() {
    // UI 로직: LazyColumn을 사용하여 서버에서 가져온 요약 리스트 표시
    LazyColumn {
        items(10) { index ->
            ListItem(
                headlineContent = { Text("강의 요약본 #$index") },
                supportingContent = { Text("강의 날짜: 2025-12-20") },
                leadingContent = { Icon(Icons.Default.Description, null) }
            )
        }
    }
}