package org.chs.lecturelens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.chs.lecturelens.data.di.Koin
import org.chs.lecturelens.presentation.view.MainScreen
import org.koin.android.ext.koin.androidContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        Koin().initKoin{
            androidContext(applicationContext)
        }

        setContent {
            MainScreen()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    MainScreen()
}