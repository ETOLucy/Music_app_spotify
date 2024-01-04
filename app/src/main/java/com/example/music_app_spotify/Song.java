package com.example.music_app_spotify;

// Song.java
public class Song {
    private String songName;
    private String artistName;
    private int imageResourceId;

    private String songTrack;

    public Song(String songName, String artistName, int imageResourceId, String songTrack) {
        this.songName = songName;
        this.artistName = artistName;
        this.imageResourceId = imageResourceId;
        this.songTrack = songTrack;

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

    public String getSongTrack() { return songTrack; }
}

