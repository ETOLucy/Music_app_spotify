package com.example.music_app_spotify;

// SongAdapter.java
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {
    private List<Song> songs;

    public SongAdapter(List<Song> songs) {
        this.songs = songs;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView songNameTextView;
        public TextView artistNameTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            songNameTextView = itemView.findViewById(R.id.textViewSongName);
            artistNameTextView = itemView.findViewById(R.id.textViewArtistName);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Song song = songs.get(position);

        holder.songNameTextView.setText(song.getName());
        holder.artistNameTextView.setText(song.getArtist());
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }
}
