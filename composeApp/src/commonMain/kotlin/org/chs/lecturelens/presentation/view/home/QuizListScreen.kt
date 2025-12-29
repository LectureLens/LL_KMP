package org.chs.lecturelens.presentation.view.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.chs.lecturelens.presentation.view.Screen.Companion.items

@Composable
fun QuizListScreen() {
    LazyColumn {
        items(5) { index ->
            Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                ListItem(
                    headlineContent = { Text("퀴즈 세트 #$index") },
                    trailingContent = { Text("점수: 80/100") },
                    modifier = Modifier.clickable { /* 퀴즈 풀이 화면 이동 */ },
                )
            }
        }
    }
}
