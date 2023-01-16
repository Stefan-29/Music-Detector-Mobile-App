package com.stefan.musicdetectorapp.apiSearchEntities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArtistX(
    val adamid: String,
    val id: String
) : Parcelable