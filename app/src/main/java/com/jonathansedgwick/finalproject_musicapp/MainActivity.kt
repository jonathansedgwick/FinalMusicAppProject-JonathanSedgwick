package com.jonathansedgwick.finalproject_musicapp

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*
import org.json.JSONObject
import org.json.JSONStringer
import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Executors


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
            MainScope().launch {
                var result = ""
                withContext(Dispatchers.IO) {
                    result = UserService().successfulUsersResponse()
                    val user: User = User("555","Megan","Megan123")
                    UserService().addUser(user)
                }
                textText.text = result
            }
        }
//
        signupButton.setOnClickListener {
            handleSignUpDialogue()
        }


        textText = findViewById(R.id.testText)


        MainScope().launch {
            var result = ""
            withContext(Dispatchers.IO) {
               result = UserService().successfulUsersResponse()
            }
            textText.text = result
        }





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

                createId()




                viewGroup.removeAllViews()
                signupButton.visibility = View.VISIBLE
                loginButton.visibility = View.VISIBLE

            }


        }

//
    }

    private fun createId(): String {

        var id = ""

        MainScope().launch {
            var result = ""
            withContext(Dispatchers.IO) {
                result = UserService().successfulUsersResponse()
            }

            val jsonResult = JSONObject(result)

            val jsonArray = jsonResult.getJSONArray("")



        }




return id
    }




}

