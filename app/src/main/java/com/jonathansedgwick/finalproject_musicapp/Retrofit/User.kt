package com.jonathansedgwick.finalproject_musicapp.Retrofit

import com.fasterxml.jackson.annotation.JsonProperty

data class User(
    @JsonProperty("id") val id: String,
    @JsonProperty("username") val username: String,
    @JsonProperty("password") val password: String
 )
