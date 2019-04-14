package ru.alexgiltd.musicdb.model

data class ArtistModel(
        val name: String,
        val mbid: String = "",
        val url: String,
        val images: Map<String, String>?,
        val isStreamable: Boolean,
        val isOnTour: Boolean,
        val listeners: Long,
        val playcount: Long,
        val similarArtists: List<SimpleArtistModel>,
        val tags: List<TagModel>,
        val published: String,
        val summary: String,
        val content: String
)

data class TagModel(
        val name: String,
        val url: String
)