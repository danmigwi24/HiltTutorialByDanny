package com.example.hilttutorialbydanny.data.repo

import android.util.Log
import com.example.hilttutorialbydanny.data.network.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

interface SafeApiCall {

    suspend fun <T> safeApiCall(api: suspend () -> T): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.Success(api.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                        Log.e("catch",throwable.toString())
                        Resource.Failure(
                            false,
                            throwable.code(),
                            throwable.response()?.errorBody()
                        )
                    }
                    else -> {
                        Log.e("catch",throwable.toString())
                        Resource.Failure(true, null, null)
                    }
                }
            }
        }
    }
}