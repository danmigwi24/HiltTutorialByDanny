package com.example.hilttutorialbydanny.data.network

import com.example.hilttutorialbydanny.data.response.LoginResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApi {

    @GET("users/{id}")
    suspend fun getUser(
        @Path("id") id: Int
    ): LoginResponse
}