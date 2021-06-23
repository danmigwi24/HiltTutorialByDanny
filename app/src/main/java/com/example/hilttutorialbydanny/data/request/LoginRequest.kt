package com.example.hilttutorialbydanny.data.request


import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("phone_number")
    val phoneNumber: String,
    @SerializedName("password")
    val password: String
)