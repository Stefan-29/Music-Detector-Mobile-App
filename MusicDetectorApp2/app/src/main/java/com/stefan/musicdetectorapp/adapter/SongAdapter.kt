package com.stefan.musicdetectorapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.stefan.musicdetectorapp.R
import com.stefan.musicdetectorapp.apiSearchEntities.HitX
import com.stefan.musicdetectorapp.apiSearchEntities.SearchResult
import com.stefan.musicdetectorapp.apiSearchEntities.Track

class SongAdapter (val songs: ArrayList<SearchResult>) : RecyclerView.Adapter<SongAdapter.ViewHolder>() {

    private lateinit var songDataListener: SongDataListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_song, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return songs.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(songs[position])
        holder.itemView.setOnClickListener {
            songDataListener.songItemClicked(songs[position])
        }
    }

//    fun setSongs(songs: List<Song>) {
//        this.songs = songs
//        notifyDataSetChanged()
//    }

    fun setSongDataListener(songDataListener: SongDataListener){
        this.songDataListener = songDataListener
    }


    interface SongDataListener {
        fun songItemClicked(song: SearchResult)

    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView = itemView.findViewById<TextView>(R.id.song_title_text_view)
        private val artistTextView = itemView.findViewById<TextView>(R.id.song_artist_text_view)

        fun bind(song: SearchResult) {
            titleTextView.text = song.tracks.hits.get(1).track.title
            artistTextView.text = song.artists.hits.get(0).artist.name
        }
    }
}

