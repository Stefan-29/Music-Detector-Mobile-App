package com.stefan.musicdetectorapp.entity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SongViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: SongRepository
    val allSongs: LiveData<List<Song>>

    init {
        val songsDao = SongDatabase.getDatabase(application).songDao()
        repository = SongRepository(songsDao)
        allSongs = repository.allSongs
    }



    fun insert(song: Song) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(song)
    }

    fun update(song: Song) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(song)
    }

    fun delete(song: Song) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(song)
    }}
