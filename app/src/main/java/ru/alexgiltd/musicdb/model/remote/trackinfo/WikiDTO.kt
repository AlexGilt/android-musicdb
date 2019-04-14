package ru.alexgiltd.musicdb.model.remote.trackinfo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WikiDTO(
        @Expose @SerializedName("published") val published: String,
        @Expose @SerializedName("summary") val summary: String,
        @Expose  @SerializedName("content") val content: String
)