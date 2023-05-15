package com.jonathansedgwick.finalproject_musicapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.JsonReader
import android.util.JsonWriter
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.jonathansedgwick.finalproject_musicapp.Retrofit.ServiceBuilder
import com.jonathansedgwick.finalproject_musicapp.Retrofit.TokenModel
import com.jonathansedgwick.finalproject_musicapp.Retrofit.UserModel
import com.jonathansedgwick.finalproject_musicapp.Retrofit.UserService
import kotlinx.coroutines.*
import retrofit2.Response



class MainActivity : AppCompatActivity() {

    lateinit var loginButton: Button
    lateinit var signupButton: Button
    lateinit var loginImage: ImageView
    lateinit var view: View
    lateinit var relativeLayout: RelativeLayout
    lateinit var scrollText: TextView
    var loginEmail: String = ""
    var loginPassword: String = ""
    var signupName: String = ""
    var signupEmail: String = ""
    var signupPassword: String = ""




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        loginButton = findViewById(R.id.login_button)
        signupButton = findViewById(R.id.signup_button)

        loginButton.setOnClickListener {
            handleLoginDialogue()
        }
//
        signupButton.setOnClickListener {
            nextActivity()
            handleSignUpDialogue()
        }





    }


    private fun handleLoginDialogue() {
        val inflater: LayoutInflater =
            this@MainActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val viewGroup: ViewGroup = findViewById(R.id.login_linear_layout)
        viewGroup.removeAllViews()
        val view: ViewGroup =
            inflater.inflate(R.layout.login_dialogue, viewGroup, false) as ViewGroup
        signupButton.visibility = View.GONE
        loginButton.visibility = View.GONE
        viewGroup.addView(view)

        var loginBtn: Button = view.findViewById(R.id.login)
        var emailEdit: EditText = view.findViewById(R.id.loginEmailEdit)
        var passwordEdit: EditText = view.findViewById(R.id.loginPasswordEdit)
        val backButton: Button = view.findViewById(R.id.login_back_button)

        loginBtn.setOnClickListener {


                if (emailEdit.text.isNotEmpty() && passwordEdit.text.isNotEmpty()) {

                    MainScope().launch {
                        withContext(Dispatchers.IO) {
                            try {
                                val apiService =
                                    ServiceBuilder.buildService(UserService::class.java)
                                val loginUser =
                                    UserModel(
                                        emailEdit.text.toString(),
                                        passwordEdit.text.toString()
                                    )
                                val call = apiService.loginUser(loginUser)
                                val result: Response<Any?> = call.execute()
                                val data = result.body()
                                val gson = Gson()
                                val jsonStringData = gson.toJson(data)
                                Log.d("String", jsonStringData)
                                val token = gson.fromJson(jsonStringData, TokenModel::class.java)
                                val loginToken = token.token
                                Log.d("Key", loginToken)
                                if (result.code() == 200) {
                                    nextActivity(loginToken)
                                } else {
                                    Log.d("Response", "Wrong username or password")
                                }

                            }catch (e: java.lang.NullPointerException) {
                                Log.d("Response", "Crash")
                            }
                        }
                    }


                }


        }

        backButton.setOnClickListener {
            viewGroup.removeAllViews()
            signupButton.visibility = View.VISIBLE
            loginButton.visibility = View.VISIBLE
        }

    }

    private fun handleSignUpDialogue() {


        val inflater: LayoutInflater =
            this@MainActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val viewGroup: ViewGroup = findViewById(R.id.login_linear_layout)
        viewGroup.removeAllViews()
        val view: ViewGroup =
            inflater.inflate(R.layout.signup_dialogue, viewGroup, false) as ViewGroup
        signupButton.visibility = View.GONE
        loginButton.visibility = View.GONE
        viewGroup.addView(view)


        val signupBtn: Button = view.findViewById(R.id.signup)
        val emailEdit: EditText = view.findViewById(R.id.signupEmailEdit)
        val passwordEdit: EditText = view.findViewById(R.id.signupPasswordEdit)
        val backButton: Button = view.findViewById(R.id.signup_back_button)

        signupBtn.setOnClickListener {


                if (emailEdit.text.isNotEmpty() && passwordEdit.text.isNotEmpty()) {

                    MainScope().launch {
                        withContext(Dispatchers.IO) {
                            try {
                                val apiService =
                                    ServiceBuilder.buildService(UserService::class.java)
                                val newUser =
                                    UserModel(
                                        emailEdit.text.toString(),
                                        passwordEdit.text.toString()
                                    )
                                val call = apiService.signupUser(newUser)
                                val result: Response<UserModel> = call.execute()
                                if (result.code() == 201) {
                                    nextActivity()
                                } else {
                                    Log.d("Response", "User not added")
                                }
                            } catch(e: java.lang.NullPointerException) {
                                Log.d("Response", "Crash")
                            }
                        }
                    }


                }


        }



        backButton.setOnClickListener {
            viewGroup.removeAllViews()
            signupButton.visibility = View.VISIBLE
            loginButton.visibility = View.VISIBLE
        }

//
        }



    private fun nextActivity(token: String) {
        val intent = Intent(this@MainActivity, HomePage::class.java)
        intent.putExtra("Token", token)
        startActivity(intent)
    }

    private fun nextActivity() {
        val intent = Intent(this@MainActivity, HomePage::class.java)
        startActivity(intent)
    }



}


