package com.jonathansedgwick.finalproject_musicapp

import android.util.Log
import com.fasterxml.jackson.core.JsonParseException

class UserService {
    private val retrofit = RetrofitClient.getClient()
    private val userApi = retrofit.create(UserApi::class.java)

    fun successfulUsersResponse(): String {
        try {
            val usersResponse = userApi.getUsers()
                .execute()
            val successful = usersResponse.isSuccessful
            val httpStatusCode = usersResponse.code()
            val httpStatusMessage = usersResponse.message()

            val body: List<User>? = usersResponse.body()
            return body.toString()
        } catch (e: JsonParseException) {
            Log.e("Error", "unrecognized token-getusers")
            return "Error with token"
        }
        return "Couldn't connect"
    }

    fun addUser(user: User) {
        try {
            val add = userApi.addUser(user).execute()
        } catch (e: JsonParseException) {
            Log.e("Error", "unrecognized token-adduser")
        }

    }

}