package com.stefan.musicdetectorapp.apiSearchEntities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Action(
    val id: String,
    val name: String,
    val type: String,
    val uri: String
) : Parcelable