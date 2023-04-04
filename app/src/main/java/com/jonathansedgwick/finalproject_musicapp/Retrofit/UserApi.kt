package com.jonathansedgwick.finalproject_musicapp.Retrofit

import com.jonathansedgwick.finalproject_musicapp.Retrofit.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST


interface UserApi {

    @GET("customer")
    fun getUsers(): Call<List<User>>

    @Headers("Content-Type: application/json")
    @POST("customer")
    fun addUser(@Body userData: User): Call<User>


}