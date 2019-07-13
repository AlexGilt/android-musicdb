package ru.alexgiltd.musicdb.model

data class TrackDetailsModel(
        val name: String,
        val mbid: String = "",
        val simpleArtistModel: SimpleArtistModel,
        val summary: String,
        val content: String
)