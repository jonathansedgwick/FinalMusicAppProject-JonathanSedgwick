package com.jonathansedgwick.finalproject_musicapp




import android.content.Context
import android.os.Bundle
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

    lateinit var myRecyclerView: RecyclerView
    lateinit var myLayout: GridLayoutManager
    lateinit var myMusicList: ArrayList<MusicModel>
    lateinit var myAdapter: MusicAdapter
    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "dataStore")
    val token_key = stringPreferencesKey("token")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        myRecyclerView = findViewById(R.id.music_card_rv)
        myLayout = GridLayoutManager(applicationContext, 1, LinearLayoutManager.VERTICAL, false)
        myRecyclerView.layoutManager = myLayout
        myRecyclerView.setHasFixedSize(true)

        myMusicList =setUpData()

        myAdapter = MusicAdapter(applicationContext, myMusicList)
        myRecyclerView.adapter = myAdapter

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
    private fun setUpData(): ArrayList<MusicModel> {
        var musicList = ArrayList<MusicModel>()

        val nameList = arrayOf(
            "Rock and Roll",
            "Hip Hop",
            "Smooth Jazz",
            "Classical"
        )
        val lengthList = arrayOf(
            "3:34",
            "2:21",
            "10:56",
            "5:48"
        )
        val artistList = arrayOf(
            "Beatles",
            "Jay Z",
            "Herbie Hancock",
            "Mozart"
        )
        val imageList = intArrayOf(
            R.drawable.rockandroll,
            R.drawable.rockandroll,
            R.drawable.rockandroll,
            R.drawable.rockandroll
        )

        for (i in nameList.indices)
            musicList.add(MusicModel(imageList[i],nameList[i],artistList[i],lengthList[i]))

        return musicList
    }
}