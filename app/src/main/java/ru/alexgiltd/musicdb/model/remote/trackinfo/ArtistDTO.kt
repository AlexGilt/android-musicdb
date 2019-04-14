package ru.alexgiltd.musicdb.model.remote.trackinfo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ArtistDTO(
        @Expose @SerializedName("name") val name: String,
        @Expose @SerializedName("mbid") val mbid: String,
        @Expose @SerializedName("url") val url: String
)