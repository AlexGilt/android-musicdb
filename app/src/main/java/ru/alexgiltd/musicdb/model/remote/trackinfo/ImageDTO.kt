package ru.alexgiltd.musicdb.model.remote.trackinfo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ImageDTO(
        @Expose @SerializedName("#text") val text: String,
        @Expose @SerializedName("size") val size: String
)