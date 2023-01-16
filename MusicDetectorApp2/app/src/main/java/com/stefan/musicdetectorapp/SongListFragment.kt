package com.stefan.musicdetectorapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stefan.musicdetectorapp.adapter.SongAdapter
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
    private lateinit var songs: ArrayList<SearchResult>
    private lateinit var songAdapter: SongAdapter
    private lateinit var viewModel: SongViewModel
    private lateinit var fragmentSongListBinding: FragmentSongListBinding

    val retrofit = Retrofit.Builder()
        .baseUrl("https://shazam.p.rapidapi.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    companion object {
        fun newInstance(songs: List<Song>): SongListFragment {
            val fragment = SongListFragment()
            val args = Bundle()
            args.putParcelableArrayList("songs", ArrayList(songs))
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        songs = ArrayList()
        songAdapter = SongAdapter(songs)
        //click to view more data
        songAdapter.setSongDataListener(object : SongAdapter.SongDataListener {
            override fun songItemClicked(song: SearchResult) {
                val fragmentTransaction = activity?.supportFragmentManager?.beginTransaction()
                fragmentTransaction?.replace(R.id.container,
                    SongDetailFragment.newInstance(song))
                fragmentTransaction?.addToBackStack(null)
                fragmentTransaction?.commit()

            }


        }
        )

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        fragmentSongListBinding = FragmentSongListBinding.inflate(inflater, container, false)
        fragmentSongListBinding.recyclerView.layoutManager = LinearLayoutManager(activity)
        fragmentSongListBinding.recyclerView.adapter = songAdapter
        return view
    }



    override fun onStart() {
        super.onStart()
        fetchMatchedInputTerm()
    }

    private fun fetchMatchedInputTerm() {
        val musicApi = retrofit.create(MusicApi::class.java)
        val call = musicApi.getCurrentSongRecommendations("484129036",
            "en-US")
        call?.enqueue(object : Callback<SearchResult?> {
            override fun onResponse(call: Call<SearchResult?>, response: Response<SearchResult?>) {
                response.body()!!.let {
                    songs.add(it)
                }
                songAdapter.notifyItemChanged(0)
                }

            override fun onFailure(call: Call<SearchResult?>, t: Throwable) {
                Toast.makeText(activity, t.message, Toast.LENGTH_SHORT).show()

            }

        }


        )

    }

}
