package com.stefan.musicdetectorapp.apiSearchEntities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Tracks(
    val hits: List<HitX>
) : Parcelable