package com.example.hilttutorialbydanny.data

import android.content.Context
import android.util.Log
import androidx.datastore.DataStore
import androidx.datastore.preferences.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException


class UserPreferences(context: Context) {
    private val applicationContext = context.applicationContext
    private val dataStore: DataStore<Preferences> = applicationContext.createDataStore(
        name = "MY_STORE"
    )
    companion object{
        private val AUTH_KEY= preferencesKey<String>("token")
    }

    suspend fun saveAuthToken(token: String) {
        dataStore.edit { preferences ->
            preferences[AUTH_KEY] = token
        }
    }

    val authToken: Flow<String?>
        get() = dataStore.data.map { preferences ->
            preferences[AUTH_KEY]
        }


}