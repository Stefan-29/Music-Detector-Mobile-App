package com.stefan.musicdetectorapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.stefan.musicdetectorapp.adapter.SongAdapter
import com.stefan.musicdetectorapp.apiSearchEntities.HitX
import com.stefan.musicdetectorapp.apiSearchEntities.Tracks
import com.stefan.musicdetectorapp.databinding.FragmentSongListBinding
import com.stefan.musicdetectorapp.entity.MusicApi
import com.stefan.musicdetectorapp.entity.SongViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SongListFragment : Fragment(R.layout.fragment_song_list) {
    private lateinit var trackHits: ArrayList<HitX>
    private lateinit var songAdapter: SongAdapter
    private lateinit var viewModel: SongViewModel
    private lateinit var fragmentSongListBinding: FragmentSongListBinding
    private lateinit var auth: FirebaseAuth
     private lateinit var search: SearchView
    val retrofit = Retrofit.Builder()
        .baseUrl("https://shazam.p.rapidapi.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()


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
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        fragmentSongListBinding = FragmentSongListBinding.inflate(layoutInflater)
        fragmentSongListBinding.tvEmail.text = "John Doe"
        fragmentSongListBinding.btnLogout.setOnClickListener {
            auth.signOut()
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
        trackHits = ArrayList()
        songAdapter = SongAdapter(trackHits)
        songAdapter.setSongDataListener(object : SongAdapter.SongDataListener {
            override fun songItemClicked(trackHit: HitX) {
                val fragmentTransaction = activity?.supportFragmentManager?.beginTransaction()
                fragmentTransaction?.replace(R.id.fragment_container,
                    SongDetailFragment.newInstance(trackHit))
                fragmentTransaction?.addToBackStack(null)
                fragmentTransaction?.commit()

            }

        })

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        fragmentSongListBinding = FragmentSongListBinding.inflate(inflater, container, false)
        return fragmentSongListBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        viewModel = ViewModelProvider(this,
//            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application))
//            .get(SongViewModel::class.java)
//        viewModel.songs.observe(viewLifecycleOwner, Observer {
            fragmentSongListBinding.recyclerView.layoutManager = LinearLayoutManager(context)
            fragmentSongListBinding.recyclerView.adapter = songAdapter
//        })
    }

//    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
//        inflater?.inflate(R.menu.main_menu, menu)
//        menu?.let {
//            if (inflater != null) {
//                super.onCreateOptionsMenu(it,inflater)
//            }
//        }
//        val search = menu?.findItem(R.id.search)?.actionView as androidx.appcompat.widget.SearchView
//        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                if (query != null) {
//                    getCurrentSongRecommendations(query)
//                }
//                return true
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                return false
//            }
//        })
//        inflater?.let { super.onCreateOptionsMenu(menu, it) }
//    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId

        if (id==R.id.search) {
            search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    getCurrentSongRecommendations(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    getCurrentSongRecommendations(newText)
                }
                return false
            }
        })

        }
        return super.onOptionsItemSelected(item)

    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(): SongListFragment {
            return SongListFragment()
        }
    }

    override fun onStart() {
        super.onStart()
        val query : String = "kiss"
        getCurrentSongRecommendations(query)

    }


    private fun getCurrentSongRecommendations(query: String) {
        val musicApi = retrofit.create(MusicApi::class.java)
        val call = musicApi.getCurrentSongRecommendations(query, "en-US")
        call?.enqueue(object : Callback<Tracks?> {
            override fun onResponse(call: Call<Tracks?>, response: Response<Tracks?>) {
                if (response.isSuccessful && response!=null) {
                    response.body()!!.hits.let {
                        trackHits.addAll(it)
                    }

                    songAdapter.notifyItemChanged(0)
                } else {
                    Toast.makeText(activity, "Error Occured", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Tracks?>, t: Throwable) {
                Toast.makeText(activity, "Error Occured", Toast.LENGTH_SHORT).show()
            }
        })
    }
}


