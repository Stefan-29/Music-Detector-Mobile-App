package com.stefan.musicdetectorapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stefan.musicdetectorapp.adapter.SongAdapter
import com.stefan.musicdetectorapp.entity.Song
import com.stefan.musicdetectorapp.entity.SongViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SongListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SongListFragment : Fragment() {

    private lateinit var songAdapter: SongAdapter
    private lateinit var viewModel: SongViewModel

    companion object {
        fun newInstance(songs: List<Song>): SongListFragment {
            val fragment = SongListFragment()
            val args = Bundle()
            args.putParcelableArrayList("songs", ArrayList(songs))
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_song_list, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        songAdapter = SongAdapter()
        recyclerView.adapter = songAdapter
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SongViewModel::class.java)
        arguments?.getParcelableArrayList<Song>("songs")?.let {
            songAdapter.setSongs(it)
        }
    }
}
