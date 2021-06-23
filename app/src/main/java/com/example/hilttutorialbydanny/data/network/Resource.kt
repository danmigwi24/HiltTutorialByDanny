package com.example.hilttutorialbydanny.data.network

import okhttp3.ResponseBody

sealed class Resource<out T> {
    data class Success<out T>(val value: T) : Resource<T>()
    data class Failure(
        val isNetWorkError: Boolean,
        val errorCode: Int?,
        val errBody: ResponseBody?
    ) : Resource<Nothing>()

     object Loading:Resource<Nothing>()

}