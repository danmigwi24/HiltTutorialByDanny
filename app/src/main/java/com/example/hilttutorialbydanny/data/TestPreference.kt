package com.example.hilttutorialbydanny.data

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException


class TestPreference(context: Context) {

    private val applicationContex = context.applicationContext
    private val dataStore: DataStore<Preferences> = applicationContex.createDataStore(
        name = "My"
    )

    companion object {
        private val KEY = preferencesKey<String>("token")
    }

    suspend fun saveToken(token: String) {
        dataStore.edit { preferences ->
            preferences[KEY] = token
        }
    }


    val fetchToken: Flow<String?>
        get() = dataStore.data.catch { err ->
            if (err is IOException) emit(emptyPreferences()) else throw err
        }.map { preferences ->
            preferences[KEY] ?: "none"
        }

    /*
    private val applicationContext = context.applicationContext
    private val dataStore: DataStore<Preferences> = applicationContext.createDataStore(
        name = "MY_STORE"
    )

    //first Method
    private object PREFERENCE_KEY {
        val AUTH_KEY = preferencesKey<String>("token")
    }

    //Second Method
    companion object {
        private val KEY_AUTH = preferencesKey<String>("bearerToken")
    }


    suspend fun saveAuthToken(token: String) {
        dataStore.edit {
            it[PREFERENCE_KEY.AUTH_KEY] = token
        }
    }

    val authToken: Flow<String?>
        get() = dataStore.data.catch { exception ->
            if (exception is IOException) {
                Log.e("DATASTORE ERROR", exception.message.toString())
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map {
            it[PREFERENCE_KEY.AUTH_KEY]
        }


    */
}