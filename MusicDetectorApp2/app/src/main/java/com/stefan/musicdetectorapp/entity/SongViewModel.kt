package com.stefan.musicdetectorapp.entity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.stefan.musicdetectorapp.apiSearchEntities.SearchResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SongViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: SongRepository
    val allSongs: LiveData<List<SearchResult>>

    init {
        val songsDao = SongDatabase.getDatabase(application).songDao()
        repository = SongRepository(songsDao)
        allSongs = repository.allSongs
    }



