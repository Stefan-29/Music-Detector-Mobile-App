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
import com.stefan.musicdetectorapp.apiSearchEntities.HitX
import com.stefan.musicdetectorapp.apiSearchEntities.SearchResult
import com.stefan.musicdetectorapp.entity.SongViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"

/**
 * A simple [Fragment] subclass.
 * Use the [SongDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SongDetailFragment : Fragment() {
    private var trackHit: HitX? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            trackHit = it.getParcelable(ARG_PARAM1)!!
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_song_detail, container, false)
        val titleTextView = view.findViewById<TextView>(R.id.song_title_text_view)
        val subTextView = view.findViewById<TextView>(R.id.song_subtitle_text_view)
        titleTextView.text = trackHit?.track?.title
        subTextView.text = trackHit?.track?.subtitle
        val openUrlButton = view.findViewById<Button>(R.id.open_url_button)
        val songUrl = trackHit?.track?.url
        openUrlButton.setOnClickListener {
            val webUrl = Uri.parse(songUrl);
            val webIntent = Intent(Intent.ACTION_VIEW, webUrl);
            startActivity(webIntent)
        }


        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(trackHitX: HitX) =
            SongDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PARAM1, trackHitX)
                }
            }
    }

}
