package com.stefan.musicdetectorapp.entity

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.*
import com.stefan.musicdetectorapp.apiSearchEntities.Hit
import com.stefan.musicdetectorapp.apiSearchEntities.HitX
import com.stefan.musicdetectorapp.apiSearchEntities.SearchResult
import com.stefan.musicdetectorapp.apiSearchEntities.Tracks
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SongViewModel(val context: Context) : ViewModel() {
    val songs: LiveData<ArrayList<Tracks>>
        get() = songs
    val retrofit = Retrofit.Builder()
        .baseUrl("https://shazam.p.rapidapi.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private lateinit var trackHits: ArrayList<HitX>



    fun querySongs(query: String) {
        val musicApi = retrofit.create(MusicApi::class.java)
        val call = musicApi.getCurrentSongRecommendations(query, "en-US")
        call?.enqueue(object : Callback<Tracks?> {
            override fun onResponse(call: Call<Tracks?>, response: Response<Tracks?>) {
                if (response.isSuccessful) {
                    val searchResult = response.body()?.hits
                    searchResult?.addAll(trackHits)
                    Toast.makeText(context, "Error Occured", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Tracks?>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

}




