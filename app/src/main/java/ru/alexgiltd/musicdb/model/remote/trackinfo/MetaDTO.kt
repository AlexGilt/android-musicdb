package ru.alexgiltd.musicdb.model.remote.trackinfo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MetaDTO(
        @Expose @SerializedName("position") val position: String
)