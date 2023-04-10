package com.jonathansedgwick.finalproject_musicapp.Retrofit

import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName


data class UserModel(
    @SerializedName("username") val username: String,
    @SerializedName("password") val password: String
)
