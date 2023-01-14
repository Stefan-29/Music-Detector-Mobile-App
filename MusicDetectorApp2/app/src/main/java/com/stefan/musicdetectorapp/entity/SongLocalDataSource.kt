package com.stefan.musicdetectorapp.entity

import com.stefan.musicdetectorapp.entity.SongDao


class SongLocalDataSource(private val songDao: SongDao) : SongDataSource {
    override suspend fun getAllSongs(): Result<List<Song>> {
        return try {
            songDao.getAllSongs().value?.let {
                Result.Success(it)
            } ?: Result.Error(Exception("Songs not found"))
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getSong(songId: Long): Result<Song> {
        return try {
            val song = songDao.getSongById(songId)
            Result.Success(song)
        } catch (e: Exception) {
            Result.Error(e)
        }    }

    override suspend fun saveSong(song: Song) {
        songDao.insert(song)
    }

    override suspend fun deleteAllSongs() {
        songDao.deleteAllSongs()
    }

    override suspend fun deleteSongById(songId: Long) {
        songDao.deleteById(songId)
    }

}




