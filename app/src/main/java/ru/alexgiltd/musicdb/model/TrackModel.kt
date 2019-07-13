package ru.alexgiltd.musicdb.model

data class TrackModel(
        val name: String,
        val id: String = "",
        val image: String?,
        val trackOwner: SimpleArtistModel,
        val listeners: Long = 0,
        val playcount: Long = 0
)
