package org.chs.lecturelens.data.source.auth

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class TokenLocalDataSource(
    private val dataSource: DataStore<Preferences>,
) {
    companion object {
        private val ACCESS_TOKEN = stringPreferencesKey("access_token")
        private val REFRESH_TOKEN = stringPreferencesKey("refresh_token")
    }

    suspend fun saveAccessToken(token: String) {
        dataSource.edit { preferences ->
            preferences[ACCESS_TOKEN] = token
        }
    }

    suspend fun saveRefreshToken(token: String) {
        dataSource.edit { preferences ->
            preferences[REFRESH_TOKEN] = token
        }
    }

    suspend fun getAccessToken(): String? =
        dataSource.data
            .map { preferences ->
                preferences[ACCESS_TOKEN]
            }.first()

    suspend fun getRefreshToken(): String? =
        dataSource.data
            .map { preferences ->
                preferences[REFRESH_TOKEN]
            }.first()

    suspend fun clearToken() {
        dataSource.edit {
            it.clear()
        }
    }
}
