package org.chs.lecturelens // commonMain과 동일해야 함

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import okio.Path.Companion.toPath
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

actual fun provideDataStore(context: Any?): DataStore<Preferences> {
    val appContext = context as Context
    return PreferenceDataStoreFactory.createWithPath(
        produceFile = {
            appContext.filesDir.resolve(DATA_STORE_FILE_NAME).absolutePath.toPath()
        }
    )
}

actual val platformModule = module {
    single { provideDataStore(androidContext()) }
}