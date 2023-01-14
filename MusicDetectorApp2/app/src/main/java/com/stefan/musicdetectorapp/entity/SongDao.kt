package com.stefan.musicdetectorapp.entity

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import androidx.room.Update
import androidx.room.Delete

@Dao
interface SongDao {
    @Query("SELECT * FROM songs")
    fun getAllSongs(): LiveData<List<Song>>

    @Query("SELECT * FROM songs WHERE songId = :songId")
    fun getSongById(songId: Long): Song

    @Insert
     fun insert(song: Song)

    @Update
     fun update(song: Song)

    @Delete
     fun delete(song: Song)

    @Query("DELETE FROM songs WHERE songId = :songId")
    suspend fun deleteById(songId: Long)

    @Query("DELETE FROM songs")
    suspend fun deleteAllSongs()
}


