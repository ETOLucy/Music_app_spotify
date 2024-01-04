package com.example.music_app_spotify;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.spotify.android.appremote.api.ConnectionParams;
import com.spotify.android.appremote.api.Connector;
import com.spotify.android.appremote.api.SpotifyAppRemote;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String CLIENT_ID = "cddd662ac8f84bec8534ae040056d2d5";
    private static final String REDIRECT_URI = "http://localhost:1111/callback";
    private SpotifyAppRemote mSpotifyAppRemote;

    ArrayList<Song> songList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        connect();

        SongListSingleton songListSingleton = SongListSingleton.getInstance();
        songList = songListSingleton.getSongList();
        SetSongs();



        
    }

    private void SetSongs() {
        // 在你的 Activity 类中定义一个列表，包含邓紫棋歌曲的 URI
        List<String> gummySongs = Arrays.asList(
                "spotify:track:4TJ4AEPBhqj9xYykVBU4GL",
                "spotify:track:6ENf77i5DmXDimXle5Ux3C",
                "spotify:track:55d0jw269HE9pbOt5ntCo8",
                "spotify:track:1bkvGbgK4HU8B7Ue4k7O7I",
                "spotify:track:2IfSPLPDW6RGosdB5huwAA",
                "spotify:track:07DWACsD58aEdq6XnDadLh",
                "spotify:track:5UAGQJmL7Rn4gn7wlkMyqS"
        );


        List<Map<String, Object>> songDataList = new ArrayList<>();

        for (Song song : songList) {
            Map<String, Object> songData = new HashMap<>();
            songData.put("songName", song.getSongName());
            songData.put("artistName", song.getArtistName());
            songData.put("imageResourceId", song.getImageResourceId());
            songDataList.add(songData);
        }

        String[] from = {"songName", "artistName", "imageResourceId"};
        int[] to = {R.id.textViewSongName, R.id.textViewArtistName, R.id.imageViewSong};

        SimpleAdapter songAdapter = new SimpleAdapter(this, songDataList, R.layout.song_item_layout, from, to);

        ListView listView = findViewById(R.id.recyclerViewSongs);
        listView.setAdapter(songAdapter);

        // Set item click listener for ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Get the selected song
                Song selectedSong = songList.get(i);

                // Create an Intent to start the Song_play activity
                Intent intent = new Intent(MainActivity.this, Song_play.class);

                // Pass data to the Song_play activity
                intent.putExtra("songName", selectedSong.getSongName());
                intent.putExtra("artistName", selectedSong.getArtistName());
                intent.putExtra("songTrack", selectedSong.getSongTrack());
                intent.putExtra("i", i);
                // Add any other data you want to pass

                // Start the activity
                startActivity(intent);
            }

        });

    }

    private void connect() {
        ConnectionParams connectionParams =
                new ConnectionParams.Builder(CLIENT_ID)
                        .setRedirectUri(REDIRECT_URI)
                        .showAuthView(true)
                        .build();

        SpotifyAppRemote.connect(this, connectionParams,
                new Connector.ConnectionListener() {

                    public void onConnected(SpotifyAppRemote spotifyAppRemote) {
                        mSpotifyAppRemote = spotifyAppRemote;
                        Log.d("MyActivity", "Connected! Yay!");

                        // Now you can start interacting with App Remote
//                        connected();

                    }



                    public void onFailure(Throwable throwable) {
                        Log.e("MyActivity", throwable.getMessage(), throwable);

                        // Something went wrong when attempting to connect! Handle errors here
                    }
                });
    }






}
