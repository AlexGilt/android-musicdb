package ru.alexgiltd.musicdb.model

data class SimpleArtistModel(
        val name: String,
        val mbid: String = "",
        val url: String,
        val images: Map<String, String>? = null
)
