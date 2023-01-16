package com.stefan.musicdetectorapp.apiSearchEntities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Hit(
    val artist: Artist
) : Parcelable