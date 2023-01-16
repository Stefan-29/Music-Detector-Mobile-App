package com.stefan.musicdetectorapp.apiSearchEntities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImagesX(
    val background: String,
    val coverart: String,
    val coverarthq: String,
    val joecolor: String
) : Parcelable