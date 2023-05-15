package com.jonathansedgwick.finalproject_musicapp




import android.content.Context
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class HomePage : AppCompatActivity() {


    lateinit var tokenText: TextView
    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "dataStore")
    val token_key = stringPreferencesKey("token")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)



        val loginToken = intent.getStringExtra("Token")

        MainScope().launch {
            withContext(Dispatchers.IO) {

                dataStore.edit {
                        dataStore ->
                    dataStore[token_key] = loginToken?: ""
                }
            }

        }






    }

}