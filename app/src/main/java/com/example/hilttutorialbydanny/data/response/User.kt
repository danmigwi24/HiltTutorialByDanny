package com.example.hilttutorialbydanny.data.response


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    val id: Int,
    @SerializedName("profile")
    val profile: String,
    @SerializedName("firstname")
    val firstname: String,
    @SerializedName("middlename")
    val middlename: String,
    @SerializedName("lastname")
    val lastname: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("phonenumber")
    val phonenumber: String,
    @SerializedName("role")
    val role: String,
    @SerializedName("description")
    val description: String
)