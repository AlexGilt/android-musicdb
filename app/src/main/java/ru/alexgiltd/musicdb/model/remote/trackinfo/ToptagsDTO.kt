package ru.alexgiltd.musicdb.model.remote.trackinfo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ToptagsDTO(
        @Expose @SerializedName("tag") val tag: List<TagDTO>
)