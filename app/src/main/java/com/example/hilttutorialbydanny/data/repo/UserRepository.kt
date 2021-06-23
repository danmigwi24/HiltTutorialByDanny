package com.example.hilttutorialbydanny.data.repo

import com.example.hilttutorialbydanny.data.network.UserApi

class UserRepository(private val api: UserApi) :
    SafeApiCall {

    suspend fun getUser(id: Int) = safeApiCall {
        api.getUser(id)
    }

}