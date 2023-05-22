package com.jonathansedgwick.finalproject_musicapp




import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
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

    lateinit var playPauseButton: ImageButton
    lateinit var songText: TextView
    lateinit var artistText: TextView
    lateinit var tokenText: TextView
    lateinit var fragmentView: FragmentContainerView
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

        playPauseButton = findViewById(R.id.play_pause_button)
        songText = findViewById(R.id.song_text)
        artistText = findViewById(R.id.artist_text)

        fragmentView = findViewById(R.id.fragmentContainerView)




        playPauseButton.setOnClickListener {
            val mp = MediaPlayer.create(this, R.raw.disco)
            mp.start()
            playPauseButton.setBackgroundResource(R.drawable.pause_icon)
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
        transaction.replace(R.id.fragmentContainerView,fragment)
        transaction.commit()
    }

    fun setUpData(): ArrayList<MusicModel> {
        var musicList = ArrayList<MusicModel>()

        val nameList = arrayOf(
            "Disco",
            "Forest Fire",
            "Peter",
        )
        val lengthList = arrayOf(
            "1:47",
            "1:28",
            "1:05",
        )
        val artistList = arrayOf(
            "Jonathan",
            "Jonathan",
            "Jonathan"
        )
        val imageList = intArrayOf(
            R.drawable.disco,
            R.drawable.forestfire,
            R.drawable.goat,

        )

        val rawList = arrayOf(
            R.raw.disco,
            R.raw.forestfire,
            R.raw.peter
        )

        for (i in nameList.indices)
            musicList.add(MusicModel(imageList[i],nameList[i],artistList[i],lengthList[i], rawList[i]))

        return musicList
    }


}