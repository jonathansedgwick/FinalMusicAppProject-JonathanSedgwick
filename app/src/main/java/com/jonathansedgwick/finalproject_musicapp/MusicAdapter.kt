package com.jonathansedgwick.finalproject_musicapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView


class MusicAdapter(var context: Context, var musicList: ArrayList<MusicModel>):
    RecyclerView.Adapter<MusicAdapter.MusicCardViewHolder>() {


    class MusicCardViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var musicImage = itemView.findViewById<ImageView>(R.id.music_picture)
        var artistText = itemView.findViewById<TextView>(R.id.artist_text)
        var lengthText = itemView.findViewById<TextView>(R.id.length_text)
        var nameText = itemView.findViewById<TextView>(R.id.name_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicCardViewHolder {
        val newView = LayoutInflater.from(parent.context).inflate(R.layout.music_card, parent, false)

        return MusicCardViewHolder(newView)
    }

    override fun onBindViewHolder(holder: MusicCardViewHolder, position: Int) {
        var myMusicCard: MusicModel = musicList.get(position)

        holder.musicImage.setImageResource(myMusicCard.musicImage!!)
        holder.artistText.text = myMusicCard.musicArtist
        holder.lengthText.text = myMusicCard.musicLength
        holder.nameText.text = myMusicCard.musicName

        holder.musicImage.setOnClickListener {
            Toast.makeText(context, "Music", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return musicList.size
    }
}