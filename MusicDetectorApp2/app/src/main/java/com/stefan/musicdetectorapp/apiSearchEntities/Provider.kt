package com.stefan.musicdetectorapp.apiSearchEntities

data class Provider(
    val actions: List<ActionX>,
    val caption: String,
    val images: Images,
    val type: String
)