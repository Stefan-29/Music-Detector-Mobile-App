package com.stefan.musicdetectorapp.apiSearchEntities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ActionX(
    val name: String,
    val type: String,
    val uri: String
) : Parcelable