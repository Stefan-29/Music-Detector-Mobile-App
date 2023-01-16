package com.stefan.musicdetectorapp.apiSearchEntities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HitX(
    val snippet: String,
    val track: Track
) : Parcelable