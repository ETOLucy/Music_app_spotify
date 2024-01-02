package com.example.music_app_spotify;

// Song.java
public class Song {
    private String songName;
    private String artistName;
    private int imageResourceId;

    public Song(String songName, String artistName, int imageResourceId) {
        this.songName = songName;
        this.artistName = artistName;
        this.imageResourceId = imageResourceId;
    }

    public String getSongName() {
        return songName;
    }

    public String getArtistName() {
        return artistName;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}

