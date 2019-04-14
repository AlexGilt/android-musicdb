package ru.alexgiltd.musicdb.model.mapper

import ru.alexgiltd.musicdb.model.SimpleArtistModel
import ru.alexgiltd.musicdb.model.remote.artists.ArtistsResponse

fun ArtistsResponse.mapToSimplifiedArtistModelList(): List<SimpleArtistModel> {

    val artists = artists.artist.map {
        SimpleArtistModel(
                mbid = it.mbid,
                name = it.name,
                url = it.url,
                images = it.image.associate { imageDTO ->
                    Pair(imageDTO.size, imageDTO.text)
                }
        )
    }

    return artists
}