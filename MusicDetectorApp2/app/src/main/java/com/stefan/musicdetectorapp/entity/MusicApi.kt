package com.stefan.musicdetectorapp.entity

import com.stefan.musicdetectorapp.apiSearchEntities.HitX
import com.stefan.musicdetectorapp.apiSearchEntities.SearchResult
import com.stefan.musicdetectorapp.apiSearchEntities.Tracks
import retrofit2.Call
import retrofit2.http.*

//https://shazam.p.rapidapi.com/search?term=kiss%20the%20rain&locale=en-US&offset=0&limit=5

interface MusicApi {
    @Headers("X-RapidAPI-Key: 8434746cd9mshfb59f2b4b03ba22p1b8d3bjsne89b7eef971c",
        "X-RapidAPI-Host: shazam.p.rapidapi.com")
    @GET("search")
     fun getCurrentSongRecommendations(@Query("term") term: String?, @Query("locale") region: String?) : Call<Tracks?>?
}