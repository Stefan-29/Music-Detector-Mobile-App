package com.stefan.musicdetectorapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.stefan.musicdetectorapp.R
import com.stefan.musicdetectorapp.entity.Song

class SongAdapter : RecyclerView.Adapter<SongAdapter.ViewHolder>() {
    private var songs = listOf<Song>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_song, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return songs.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val song = songs[position]
        holder.bind(song)
    }

    fun setSongs(songs: List<Song>) {
        this.songs = songs
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView = itemView.findViewById<TextView>(R.id.song_title_text_view)
        private val artistTextView = itemView.findViewById<TextView>(R.id.song_artist_text_view)
        private val albumTextView = itemView.findViewById<TextView>(R.id.song_album_text_view)

        fun bind(song: Song) {
            titleTextView.text = song.title
            artistTextView.text = song.artist
            albumTextView.text = song.album
        }
    }
}
