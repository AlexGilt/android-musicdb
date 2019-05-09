package ru.alexgiltd.musicdb.model.mapper

import ru.alexgiltd.musicdb.model.ArtistModel
import ru.alexgiltd.musicdb.model.SimpleArtistModel
import ru.alexgiltd.musicdb.model.local.artist.ArtistLocal
import ru.alexgiltd.musicdb.model.local.artist.ImageLocal

fun artistModelToArtistLocal(artistModel: ArtistModel): ArtistLocal {

    val artistLocal = ArtistLocal(
        name = artistModel.name,
        mbid = if (artistModel.mbid.isNotEmpty()) artistModel.mbid else null,
        url = artistModel.url
    )
    artistLocal.images = artistModel.images?.map {
        ImageLocal(size = it.key, url = it.value)
    }

    return artistLocal
}

fun simpleArtistModelToArtistLocal(simpleArtistModel: SimpleArtistModel): ArtistLocal {

    val artistLocal = ArtistLocal(
        name = simpleArtistModel.name,
        mbid = if (simpleArtistModel.mbid.isNotEmpty()) simpleArtistModel.mbid else null,
        url = simpleArtistModel.url
    )
    artistLocal.images = simpleArtistModel.images?.map {
        ImageLocal(size = it.key, url = it.value)
    }

    return artistLocal
}