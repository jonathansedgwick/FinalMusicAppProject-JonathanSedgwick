package com.jonathansedgwick.finalproject_musicapp




import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class HomePage : AppCompatActivity() {

    lateinit var myRecyclerView: RecyclerView
    lateinit var myLayout: GridLayoutManager
    lateinit var myMusicList: ArrayList<MusicModel>
    lateinit var myAdapter: MusicAdapter

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