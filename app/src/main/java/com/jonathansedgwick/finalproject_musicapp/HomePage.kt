package com.jonathansedgwick.finalproject_musicapp




import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jonathansedgwick.finalproject_musicapp.Fragments.HomeFragment
import com.jonathansedgwick.finalproject_musicapp.Fragments.LibraryFragment
import com.jonathansedgwick.finalproject_musicapp.Fragments.PlayerFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class HomePage : AppCompatActivity() {


    lateinit var tokenText: TextView
    lateinit var homeBar: BottomNavigationView
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

        homeBar = findViewById(R.id.bottom_bar)
        loadFragment(HomeFragment())
        homeBar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home_item -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.player_item -> {
                    loadFragment(PlayerFragment())
                    true
                }
                R.id.library_item -> {
                    loadFragment(LibraryFragment())
                    true
                }
                else -> {
                    false
                }
            }
        }




    }

    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainerView3,fragment)
        transaction.commit()
    }

}