package com.example.hilttutorialbydanny.data.network

import android.content.Context
import android.util.Log
import com.example.hilttutorialbydanny.BuildConfig
import com.example.hilttutorialbydanny.data.UserPreferences
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RemoteDataSource {
    companion object {
        const val BASE_URL = "http://192.168.8.102:3000/v1/"
    }


    fun <Api> buildApi(api: Class<Api>, context: Context): Api {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getRetrofitClient(context))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }

    private fun getRetrofitClient(
        context: Context
    ): OkHttpClient {
        val tokenAuthenticator = TokenAuthenticator(context)
        Log.e("Token", tokenAuthenticator.api.toString())
        return OkHttpClient.Builder().also { client ->
            client.addInterceptor { chain ->
                chain.proceed(chain.request().newBuilder().also {
                    it.addHeader("Accept", "application/json")
                    it.addHeader("Authorization", "Bearer ${tokenAuthenticator.api.toString()}")
                }.build())
            }

            if (BuildConfig.DEBUG) {
                val logging = HttpLoggingInterceptor()
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                client.addInterceptor(logging)
            }
        }.build()

    }
}