package com.example.music_app_spotify;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 创建歌曲列表数据
        ArrayList<Song> songList = new ArrayList<>();
        songList.add(new Song("Song 1", "Artist 1", R.drawable.frame));
        songList.add(new Song("Song 2", "Artist 2", R.drawable.frame));
        // 添加更多歌曲...


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


    }


}
