package com.jonathansedgwick.finalproject_musicapp.Retrofit

import com.google.gson.annotations.SerializedName


data class TokenModel(
    @SerializedName("token")
    var token: String
)
