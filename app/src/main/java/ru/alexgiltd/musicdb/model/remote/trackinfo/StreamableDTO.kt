package ru.alexgiltd.musicdb.model.remote.trackinfo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class StreamableDTO(
        @Expose @SerializedName("#text") val text: String,
        @Expose @SerializedName("fulltrack") val fulltrack: String
)