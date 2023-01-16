package com.stefan.musicdetectorapp.apiSearchEntities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Artists(
    val hits: List<Hit>
) : Parcelable