package com.jonathansedgwick.finalproject_musicapp.Fragments

import android.os.Bundle
import android.provider.SyncStateContract
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jonathansedgwick.finalproject_musicapp.HomePage
import com.jonathansedgwick.finalproject_musicapp.MusicAdapter
import com.jonathansedgwick.finalproject_musicapp.MusicModel
import com.jonathansedgwick.finalproject_musicapp.R



class HomeFragment : Fragment(R.layout.home_fragment_activity){

//    var myRecyclerView: RecyclerView = R.id.
//    var myLayout: GridLayoutManager
//    var myMusicList: ArrayList<MusicModel>
//    var myAdapter: MusicAdapter
//
//    myRecyclerView = findViewById(R.id.music_card_rv)
//    myLayout = GridLayoutManager(applicationContext, 1, LinearLayoutManager.VERTICAL, false)
//    myRecyclerView.layoutManager = myLayout
//    myRecyclerView.setHasFixedSize(true)
//
//    myMusicList =setUpData()
//
//    myAdapter = MusicAdapter(applicationContext, myMusicList)
//    myRecyclerView.adapter = myAdapter
override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val musicList= HomePage().setUpData()

    val recyclerView:RecyclerView=view.findViewById(R.id.music_card_rv)
    recyclerView.layoutManager = LinearLayoutManager(context)
    var itemAdapter = MusicAdapter(context, musicList)
    recyclerView.adapter = itemAdapter

}



}