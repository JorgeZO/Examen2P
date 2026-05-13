package com.example.jzunigamusicapp.model

import com.google.gson.annotations.SerializedName

data class Album(
    @SerializedName("id") val id: String? = null,
    @SerializedName("_id") val mongoId: String? = null,
    val title: String = "",
    val artist: String = "",
    val description: String = "",
    val image: String = ""
) {
    val albumId: String get() = id ?: mongoId ?: ""
}
