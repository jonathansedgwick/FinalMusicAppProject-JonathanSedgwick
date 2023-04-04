package com.jonathansedgwick.finalproject_musicapp.Retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "http://24.199.84.205/"

    val okHttpClient = OkHttpClient()
        .newBuilder()
        .addInterceptor(ReequestInterceptor)
        .build()

     fun getClient() : Retrofit =
         Retrofit.Builder()
             .client(okHttpClient)
             .baseUrl(BASE_URL)
             .addConverterFactory(JacksonConverterFactory.create())
             .build()
}