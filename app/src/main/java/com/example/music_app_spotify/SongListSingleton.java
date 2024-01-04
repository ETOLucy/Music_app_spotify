package com.example.music_app_spotify;
// SongListSingleton.java

import java.util.ArrayList;

public class SongListSingleton {

    private static SongListSingleton instance;
    private ArrayList<Song> songList;

    private SongListSingleton() {
        // 私有构造函数，防止外部实例化
        songList = new ArrayList<>();
        // 初始化 songList，你可以在这里添加默认的歌曲列表
        songList.add(new Song("11", "鄧紫棋", R.drawable.ellipse_9, "spotify:track:4TJ4AEPBhqj9xYykVBU4GL"));
        songList.add(new Song("唯一", "鄧紫棋", R.drawable.ellipse_9, "spotify:track:6ENf77i5DmXDimXle5Ux3C"));
        songList.add(new Song("多遠都要在一起", "鄧紫棋", R.drawable.ellipse_9, "spotify:track:55d0jw269HE9pbOt5ntCo8"));
        songList.add(new Song("光年之外", "鄧紫棋", R.drawable.ellipse_9, "spotify:track:1bkvGbgK4HU8B7Ue4k7O7I"));
        songList.add(new Song("泡沫", "鄧紫棋", R.drawable.ellipse_9, "spotify:track:2IfSPLPDW6RGosdB5huwAA"));
        songList.add(new Song("手心的蔷薇", "林俊傑，鄧紫棋", R.drawable.ellipse_9, "spotify:track:07DWACsD58aEdq6XnDadLh"));
        songList.add(new Song("再見", "鄧紫棋", R.drawable.ellipse_9, "spotify:track:5UAGQJmL7Rn4gn7wlkMyqS"));

    }

    public static synchronized SongListSingleton getInstance() {
        // 提供获取单例实例的方法
        if (instance == null) {
            instance = new SongListSingleton();
        }
        return instance;
    }

    public ArrayList<Song> getSongList() {
        return songList;
    }

    public void setSongList(ArrayList<Song> songList) {
        this.songList = songList;
    }

    // 可以添加其他方法和属性
}
