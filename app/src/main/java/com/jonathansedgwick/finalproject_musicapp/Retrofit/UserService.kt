package com.jonathansedgwick.finalproject_musicapp.Retrofit


import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {

    @POST("/signup")
    fun signupUser(@Body user: UserModel) : Call<UserModel>

    @POST("/login")
    fun loginUser(@Body user: UserModel) : Call<Any?>



}