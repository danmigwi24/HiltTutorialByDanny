package com.example.hilttutorialbydanny.data.network

import android.content.Context
import android.util.Log
import com.example.hilttutorialbydanny.data.UserPreferences
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class TokenAuthenticator(
    context: Context
) {
    private val appContext = context.applicationContext
    private val userPreferences = UserPreferences(appContext)

    init {
        val api = runBlocking { userPreferences.authToken.first() }
    }
    val api = runBlocking { userPreferences.authToken.first() }

/*
    override fun authenticate(route: Route?, response: Response): Request? {
        Log.e("TOKENAUTHENTICATOR", api.toString())
        return response.request.newBuilder()
            .header("Authorization", "Bearer ${api}")
            .build()
    }*/
}