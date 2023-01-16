package com.stefan.musicdetectorapp.apiSearchEntities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Share(
    val avatar: String,
    val href: String,
    val html: String,
    val image: String,
    val snapchat: String,
    val subject: String,
    val text: String,
    val twitter: String
) : Parcelable