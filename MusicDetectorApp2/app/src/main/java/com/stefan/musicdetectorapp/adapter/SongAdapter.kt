package com.stefan.musicdetectorapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.stefan.musicdetectorapp.R
import com.stefan.musicdetectorapp.apiSearchEntities.Hit
import com.stefan.musicdetectorapp.apiSearchEntities.HitX
import com.stefan.musicdetectorapp.apiSearchEntities.SearchResult

class SongAdapter (var trackHits: ArrayList<HitX>)
    : RecyclerView.Adapter<SongAdapter.ViewHolder>() {

    private lateinit var songDataListener: SongDataListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_song, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return trackHits.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(trackHits[position])
        holder.itemView.setOnClickListener {
            songDataListener.songItemClicked(trackHits[position])
        }
    }

    fun setSongDataListener(songDataListener: SongDataListener){
        this.songDataListener =songDataListener
    }

    interface SongDataListener {
        fun songItemClicked(trackHit: HitX)
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView = itemView.findViewById<TextView>(R.id.song_title_text_view)
        private val subTextView = itemView.findViewById<TextView>(R.id.song_subtitle_text_view)

        fun bind(trackHit: HitX) {
            titleTextView.text = trackHit.track.title
            subTextView.text = trackHit.track.subtitle
        }
    }


}

