package com.jonathansedgwick.finalproject_musicapp

class MusicModel {
    var musicImage: Int?
    var musicName: String?
    var musicArtist: String?
    var musicLength: String?

    constructor(musicImage: Int?, musicName: String?, musicArtist: String?, musicLength: String?) {
        this.musicImage = musicImage
        this.musicArtist = musicArtist
        this.musicLength = musicLength
        this.musicName = musicName

    }

}
