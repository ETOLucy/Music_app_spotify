package com.example.music_app_spotify;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewHolder> {

    private List<Song> songList;
    private LayoutInflater inflater;

    public SongAdapter(Context context, List<Song> songs) {
        inflater = LayoutInflater.from(context);
        this.songList = songs;
    }

    @NonNull
    @Override
    public SongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.song_item_layout, parent, false);
        Log.d("SongAdapter", "onCreateViewHolder called");
        return new SongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SongViewHolder holder, int position) {
        Song currentSong = songList.get(position);
        Log.d("SongAdapter", "onBindViewHolder called for position: " + position);
        holder.songName.setText(currentSong.getSongName());
        holder.artistName.setText(currentSong.getArtistName());
        holder.songImage.setImageResource(currentSong.getImageResourceId());
    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    static class SongViewHolder extends RecyclerView.ViewHolder {
        TextView songName;
        TextView artistName;
        ImageView songImage;

        public SongViewHolder(View itemView) {
            super(itemView);
            songName = itemView.findViewById(R.id.textViewSongName);
            artistName = itemView.findViewById(R.id.textViewArtistName);
            songImage = itemView.findViewById(R.id.imageViewSong);
        }
    }
}
