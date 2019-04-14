package ru.alexgiltd.musicdb.model.remote.trackinfo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AlbumDTO(
        @Expose @SerializedName("artist") val artist: String,
        @Expose @SerializedName("title") val title: String,
        @Expose @SerializedName("mbid") val mbid: String,
        @Expose @SerializedName("url") val url: String,
        @Expose @SerializedName("image") val image: List<ImageDTO>,
        @Expose @SerializedName("@attr") val meta: MetaDTO
)