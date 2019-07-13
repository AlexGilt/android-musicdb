package ru.alexgiltd.musicdb.model.mapper

import ru.alexgiltd.musicdb.model.SimpleArtistModel
import ru.alexgiltd.musicdb.model.remote.artists.ArtistDTO
import ru.alexgiltd.musicdb.model.remote.artists.ArtistsResponse

fun mapArtistsResponseToSimpleArtistModelList(r: ArtistsResponse): List<SimpleArtistModel> {
    return r.artists.artist.map(::mapArtistDtoToSimpleArtistModel)
}

private fun mapArtistDtoToSimpleArtistModel(artistDto: ArtistDTO): SimpleArtistModel {
    return SimpleArtistModel(
            mbid = artistDto.mbid,
            name = artistDto.name,
            url = artistDto.url,
            images = artistDto.image.associate { imageDTO ->
                Pair(imageDTO.size, imageDTO.text)
            }
    )
}