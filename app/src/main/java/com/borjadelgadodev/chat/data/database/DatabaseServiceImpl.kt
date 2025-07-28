package com.borjadelgadodev.chat.data.database

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.borjadelgadodev.chat.domain.DatabaseService
import javax.inject.Inject

class DatabaseServiceImpl @Inject constructor(private val context: Context): DatabaseService {

    companion object Companion {
        private val USER_NAME = stringPreferencesKey("user_name")
    }

    private val Context.userPrefrencesDataStore: DataStore<Preferences> by preferencesDataStore(
        name = "user_preferences"
    )

    override suspend fun saveUserName(nickName: String) {
        context.userPrefrencesDataStore.edit { preferences ->
            preferences[USER_NAME] = nickName
        }
    }
}
