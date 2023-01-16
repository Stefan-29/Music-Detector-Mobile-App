package com.stefan.musicdetectorapp.apiSearchEntities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Provider(
    val actions: List<ActionX>,
    val caption: String,
    val images: Images,
    val type: String
) : Parcelable