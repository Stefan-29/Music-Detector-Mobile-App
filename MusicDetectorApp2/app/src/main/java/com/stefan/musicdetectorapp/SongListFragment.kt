package com.stefan.musicdetectorapp

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stefan.musicdetectorapp.adapter.SongAdapter
import com.stefan.musicdetectorapp.apiSearchEntities.Hit
import com.stefan.musicdetectorapp.apiSearchEntities.HitX
import com.stefan.musicdetectorapp.apiSearchEntities.SearchResult
import com.stefan.musicdetectorapp.databinding.FragmentSongListBinding
import com.stefan.musicdetectorapp.entity.MusicApi
import com.stefan.musicdetectorapp.entity.Song
import com.stefan.musicdetectorapp.entity.SongViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory




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
    private lateinit var songs: ArrayList<HitX>
    private lateinit var trackHits: ArrayList<HitX>
    private lateinit var songAdapter: SongAdapter
    private lateinit var viewModel: SongViewModel
    private lateinit var fragmentSongListBinding: FragmentSongListBinding


//    companion object {
//        fun newInstance(songs: List<Song>): SongListFragment {
//            val fragment = SongListFragment()
//            val args = Bundle()
//            args.putParcelableArrayList("songs", ArrayList(songs))
//            fragment.arguments = args
//            return fragment
//        }
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentSongListBinding = FragmentSongListBinding.inflate(inflater, container, false)
        return fragmentSongListBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application))
            .get(SongViewModel::class.java)
        viewModel.songs.observe(viewLifecycleOwner, Observer {
            songAdapter = SongAdapter(trackHits)
            fragmentSongListBinding.recyclerView.layoutManager = LinearLayoutManager(context)
            fragmentSongListBinding.recyclerView.adapter = songAdapter
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        val search = menu.findItem(R.id.search).actionView as SearchView
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    viewModel.querySongs(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

//    private fun getCurrentSongRecommendations(query: String) {
//        val musicApi = retrofit.create(MusicApi::class.java)
//        val call = musicApi.getCurrentSongRecommendations(query, "en-US")
//        call?.enqueue(object : Callback<SearchResult?> {
//            override fun onResponse(call: Call<SearchResult?>, response: Response<SearchResult?>) {
//                if (response.isSuccessful) {
//                    val searchResult = response.body()
//                    val artistHits = searchResult?.artists?.hits
//                    val trackHits = searchResult?.tracks?.hits
//                    songAdapter.updateData(searchResult, artistHits, trackHits)
//                } else {
//                    Toast.makeText(requireContext(), "Error Occured", Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            override fun onFailure(call: Call<SearchResult?>, t: Throwable) {
//                Toast.makeText(requireContext(), "Error Occured", Toast.LENGTH_SHORT).show()
//            }
//        })
//    }
}


