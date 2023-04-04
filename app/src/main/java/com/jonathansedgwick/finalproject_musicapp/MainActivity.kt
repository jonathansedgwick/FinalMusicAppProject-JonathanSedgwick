package com.jonathansedgwick.finalproject_musicapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.jonathansedgwick.finalproject_musicapp.DataStore.DataStoreManager
import com.jonathansedgwick.finalproject_musicapp.Retrofit.User
import com.jonathansedgwick.finalproject_musicapp.Retrofit.UserService
import kotlinx.coroutines.*
import org.json.JSONObject
import java.io.*
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    lateinit var loginButton: Button
    lateinit var signupButton: Button
    lateinit var loginImage: ImageView
    lateinit var view: View
    lateinit var relativeLayout: RelativeLayout
    lateinit var scrollText: TextView
    lateinit var textText: TextView
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
            handleSignUpDialogue()
        }


        textText = findViewById(R.id.testText)



    }


    private fun handleLoginDialogue() {
        print("Inflater")
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

        loginBtn.setOnClickListener {
            loginEmail = emailEdit.text.toString()
            loginPassword = passwordEdit.text.toString()
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


        var signupBtn: Button = view.findViewById(R.id.signup)
        var emailEdit: EditText = view.findViewById(R.id.signupEmailEdit)
        var passwordEdit: EditText = view.findViewById(R.id.signupPasswordEdit)

        signupBtn.setOnClickListener {


            if (emailEdit.text.isNotEmpty() && passwordEdit.text.isNotEmpty()) {
                signupEmail = emailEdit.text.toString()
                signupPassword = passwordEdit.text.toString()



                MainScope().launch {
                    withContext(Dispatchers.IO) {
                        val user: User = User(createId(), signupEmail, signupPassword)
                        UserService().addUser(user)
                    }
                }


                viewGroup.removeAllViews()
                signupButton.visibility = View.VISIBLE
                loginButton.visibility = View.VISIBLE

            }


        }

//
    }

    private suspend fun createId(): String {

        var data = ""
        data = UserService().successfulUsersResponse()

       Log.d("Server Data", data)

        val jsonResult = JSONObject(data)
        val jsonArray = jsonResult.getJSONArray("User")
        var potentialId = 0
        var validId = false
        while (validId == false) {

            potentialId = Random.nextInt(10000,99999)

            for (i in 0 until jsonArray.length()) {
                val userArray = jsonArray.getJSONArray(i)
                validId = potentialId.toString() != userArray.getString(1)
            }
        }
        return potentialId.toString()


    }




}

