package com.stefan.musicdetectorapp.apiSearchEntities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Hub(
    val actions: List<Action>,
    val displayname: String,
    val explicit: Boolean,
    val image: String,
    val options: List<Option>,
    val providers: List<Provider>,
    val type: String
) : Parcelable