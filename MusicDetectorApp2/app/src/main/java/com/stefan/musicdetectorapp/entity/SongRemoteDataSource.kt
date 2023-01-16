package com.stefan.musicdetectorapp.entity

class SongRemoteDataSource internal constructor() : SongDataSource {
    override suspend fun getAllSongs(): Result<List<Song>> {
        return try {
            val response = MusicApi.getCurrentSongRecommendations()
            val songs = response.body()
            Result.Success(songs)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }


    override suspend fun getSong(songId: Long): Result<Song> {
        return try { // make network request to fetch song with id from a remote server
            val response = api.getSong(songId)
            val song = response.body() Result . Success (song)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun saveSong(song: Song) {
        // not implemented since we are only reading from the remote server
    }

    override suspend fun deleteAllSongs() {
        TODO("Not yet implemented")
    }

    override suspend fun deleteSongById(songId: Long) {
        TODO("Not yet implemented")
    }
}

override suspend fun deleteAllSongs() { // not implemented since we are only reading from the remote server
}

