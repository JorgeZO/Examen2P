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

    val fixedImage: String get() = IMAGE_FIXES[image] ?: image

    companion object {
        private val IMAGE_FIXES = mapOf(
            "https://upload.wikimedia.org/wikipedia/en/4/42/Beatles_-_Abbey_Road.jpg"
                    to "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a4/The_Beatles_Abbey_Road_album_cover.jpg/330px-The_Beatles_Abbey_Road_album_cover.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/9/91/ACDC_Back_in_Black.png"
                    to "https://upload.wikimedia.org/wikipedia/commons/thumb/9/92/ACDC_Back_in_Black.png/330px-ACDC_Back_in_Black.png",
            "https://upload.wikimedia.org/wikipedia/en/a/a8/Beyonce_-_Lemonade_%28Official_Album_Cover%29.png"
                    to "https://upload.wikimedia.org/wikipedia/en/5/53/Beyonce_-_Lemonade_%28Official_Album_Cover%29.png",
            "https://upload.wikimedia.org/wikipedia/en/a/a7/Random_Access_Memories.jpg"
                    to "https://upload.wikimedia.org/wikipedia/en/2/26/Daft_Punk_-_Random_Access_Memories.png"
        )
    }
}
