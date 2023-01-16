package com.stefan.musicdetectorapp.apiSearchEntities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Artist(
    val adamid: String,
    val avatar: String,
    val name: String,
    val verified: Boolean,
    val weburl: String
) : Parcelable