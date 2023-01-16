package com.stefan.musicdetectorapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.stefan.musicdetectorapp.apiSearchEntities.SearchResult
import com.stefan.musicdetectorapp.entity.Song
import com.stefan.musicdetectorapp.entity.SongViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SongDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SongDetailFragment : Fragment() {
    private lateinit var viewModel: SongViewModel
    private lateinit var song: SearchResult


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            song = it.getParcelable(ARG_PARAM1)!!
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_song_detail, container, false)
        val titleTextView = view.findViewById<TextView>(R.id.song_title_text_view)
        val artistTextView = view.findViewById<TextView>(R.id.song_artist_text_view)
        val openUrlButton = view.findViewById<Button>(R.id.open_url_button)
        val songUrl = song.tracks.hits.get(0).track.url
        openUrlButton.setOnClickListener {
            val webUrl = Uri.parse(songUrl);
            val webIntent = Intent(Intent.ACTION_VIEW, webUrl);
            startActivity(webIntent)
        }


        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(song: SearchResult) =
            SongDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PARAM1, song)
                }
            }
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider (this).get(
//            SongViewModel::class.java) arguments ?. getParcelable < Song >("song")?.let {
//            song = it
//            val titleTextView = view?.findViewById<TextView>(R.id.song_title_text_view)
//            val artistTextView = view?.findViewById<TextView>(R.id.song_artist_text_view)
//            val albumTextView = view?.findViewById<TextView>(R.id.song_album_text_view)
//            val durationTextView =
//                view?.findViewById<TextView>(R.id.song_duration_text_view) titleTextView ?. text = song . title artistTextView?.text =
//                    song.artist albumTextView ?. text = song . album durationTextView?.text =
//                    song.duration
//        }
//    }
}
