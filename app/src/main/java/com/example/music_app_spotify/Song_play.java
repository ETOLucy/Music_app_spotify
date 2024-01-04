package com.example.music_app_spotify;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.spotify.android.appremote.api.ConnectionParams;
import com.spotify.android.appremote.api.Connector;
import com.spotify.android.appremote.api.SpotifyAppRemote;
import com.spotify.protocol.types.Track;

import java.util.ArrayList;

public class Song_play extends AppCompatActivity {

    private static final String CLIENT_ID = "cddd662ac8f84bec8534ae040056d2d5";
    private static final String REDIRECT_URI = "http://localhost:1111/callback";
    private SpotifyAppRemote mSpotifyAppRemote;
    String songTrack;

    ArrayList<Song> songList;

    TextView SongName;
    TextView ArtistName;
    String songName;
    String artistName;
    int i;

    ImageView pause;
    ImageView start;

    SeekBar seekBar;

    private boolean isUserSeeking = false; // 用于标记是否是用户在拖动

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_play);

        SongListSingleton songListSingleton = SongListSingleton.getInstance();
        songList = songListSingleton.getSongList();
//        seekBar = findViewById(R.id.seekBar);

//        OnClickSeekBar();

        // Retrieve data from Intent
        Intent intent = getIntent();
//        songName = intent.getStringExtra("songName");
//        artistName = intent.getStringExtra("artistName");
//        songTrack = intent.getStringExtra("songTrack");
        i = intent.getIntExtra("i", 0);

        SongName = findViewById(R.id.songName);
        ArtistName = findViewById(R.id.artistName);

        setSong(i);

        // 找到 ImageView 对应的 View 对象
        ImageView imageView = findViewById(R.id.Careleft);

        // 为 ImageView 设置点击事件监听器
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 在点击事件中添加返回主页的逻辑
                returnToHomePage();
            }
        });


        // Find the ImageViews by their IDs
        pause = findViewById(R.id.pause);
        start = findViewById(R.id.start);
        ImageView pre_song = findViewById(R.id.pre_song);
        ImageView next_song = findViewById(R.id.next_song);


        // Set onClickListener for the pause ImageView
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hide pause and show start
                pause.setVisibility(View.INVISIBLE);
                start.setVisibility(View.VISIBLE);

                // 在这里处理按钮点击事件，停止音乐播放
                if (mSpotifyAppRemote != null && mSpotifyAppRemote.isConnected()) {
                    // 如果 SpotifyAppRemote 已连接，则停止音乐播放
                    mSpotifyAppRemote.getPlayerApi().pause();
                }
            }
        });

        // Set onClickListener for the start ImageView
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hide start and show pause
                start.setVisibility(View.INVISIBLE);
                pause.setVisibility(View.VISIBLE);
                playMusic();
            }
        });

        pre_song.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (i == 0) {
                    i = 6;
                }
                else {
                    i--;
                }
                setSong(i);
                playMusic();
            }
        });

        next_song.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (i == 6) {
                    i = 0;
                }
                else {
                    i ++;
                }
                setSong(i);
                playMusic();
            }
        });


    }

    private void setSong(int i) {
        Song now_Song = songList.get(i);
        songName = now_Song.getSongName();
        artistName = now_Song.getArtistName();
        songTrack = now_Song.getSongTrack();
//        SongName.setText(songName);
//        ArtistName.setText(artistName);
//        TextView ii = findViewById(R.id.i);
//        ii.setText(songTrack);
    }

    private void OnClickSeekBar() {


        // 设置 SeekBar 的监听器
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // 当拖动进度条时更新音乐的播放位置
                if (fromUser) {
                    isUserSeeking = true;
                    if (mSpotifyAppRemote != null && mSpotifyAppRemote.isConnected()) {
                        long duration = seekBar.getMax(); // 获取 SeekBar 的最大值，即音乐的总时长
                        long newPosition = duration * progress / 100; // 计算新的播放位置
                        mSpotifyAppRemote.getPlayerApi().seekTo(newPosition);
                    }
                }
                runOnUiThread(() -> {
                    if (!isUserSeeking) {
                        seekBar.setProgress(progress);
                    }
                });
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // 当开始拖动时执行的操作
                isUserSeeking = true;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // 当停止拖动时执行的操作
                isUserSeeking = false;
            }
        });
    }

    // 返回主页的方法
    private void returnToHomePage() {
        // 创建 Intent 实例，用于启动主页的 Activity
        Intent intent = new Intent(this, MainActivity.class);

        // 设置标志位，清除之前的所有 Activity 栈，确保主页成为新的栈顶
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // 启动主页的 Activity
        startActivity(intent);

        // 结束当前的 Activity，以确保返回到主页后，点击返回按钮不会再回到当前页面
        finish();
    }


    protected void playMusic(){

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
                        connected();

                    }

                    public void onFailure(Throwable throwable) {
                        Log.e("MyActivity", throwable.getMessage(), throwable);

                        // Something went wrong when attempting to connect! Handle errors here
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        SpotifyAppRemote.disconnect(mSpotifyAppRemote);
    }

    private void connected() {
        // Play a playlist
        mSpotifyAppRemote.getPlayerApi().play(songTrack);
        if (pause.getVisibility() == View.INVISIBLE) {
            start.setVisibility(View.INVISIBLE);
            pause.setVisibility(View.VISIBLE);
        }
        // Subscribe to PlayerState
        mSpotifyAppRemote.getPlayerApi()
                .subscribeToPlayerState()
                .setEventCallback(playerState -> {
                    final Track track = playerState.track;
                    final int duration = (int) track.duration; // 获取音乐的总时长
                    final int playbackPosition = (int) playerState.playbackPosition; // 获取当前播放位置
                    // 将当前播放位置更新到 SeekBar
//                    seekBar.setMax(duration);
//                    if (!isUserSeeking) seekBar.setProgress(playbackPosition);

                    SongName.setText(track.name);
                    ArtistName.setText(track.artist.name);


                        if (track != null) {
                        Log.d("MainActivity", track.name + " by " + track.artist.name);
                    }
                });


    }
}