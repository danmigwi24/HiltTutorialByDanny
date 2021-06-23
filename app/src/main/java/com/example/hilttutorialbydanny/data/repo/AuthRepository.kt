package com.example.hilttutorialbydanny.data.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.hilttutorialbydanny.data.UserPreferences
import com.example.hilttutorialbydanny.data.network.AuthApi
import com.example.hilttutorialbydanny.data.request.LoginRequest

class AuthRepository(private val api: AuthApi, private val userPreferences: UserPreferences) :
    SafeApiCall {

    suspend fun login(phone_number: String, password: String) = safeApiCall {
        api.login(LoginRequest(phone_number, password))
    }

    suspend fun saveAuthToken(token: String) {
        userPreferences.saveAuthToken(token)
    }

    suspend fun fetchToken(): LiveData<String?> {
        return userPreferences.authToken.asLiveData()
    }
}