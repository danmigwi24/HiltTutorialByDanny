package com.example.hilttutorialbydanny.data.network

import com.example.hilttutorialbydanny.data.request.LoginRequest
import com.example.hilttutorialbydanny.data.response.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("users/login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): LoginResponse

}