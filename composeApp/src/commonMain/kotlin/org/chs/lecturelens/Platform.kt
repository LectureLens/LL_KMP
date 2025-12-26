package org.chs.lecturelens // 반드시 이 패키지 선언이 있어야 합니다.

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import org.koin.core.module.Module

internal const val DATA_STORE_FILE_NAME = "token_prefs.preferences_pb"

expect fun provideDataStore(context: Any? = null): DataStore<Preferences>

// 이 선언이 있어야 Koin.kt에서 platformModule을 인식합니다.
expect val platformModule: Module