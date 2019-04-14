package ru.alexgiltd.musicdb.model.mapper

import ru.alexgiltd.musicdb.model.ArtistModel
import ru.alexgiltd.musicdb.model.SimpleArtistModel
import ru.alexgiltd.musicdb.model.TagModel
import ru.alexgiltd.musicdb.model.remote.artistinfo.ArtistInfoResponse
import ru.alexgiltd.musicdb.model.remote.artistinfo.ImageDTO
import ru.alexgiltd.musicdb.model.remote.artistinfo.SimpleArtistDTO
import ru.alexgiltd.musicdb.model.remote.artistinfo.TagDTO

fun ArtistInfoResponse.mapToArtistDetailModel(): ArtistModel {

    return ArtistModel(
            mbid = artistDetailsDTO?.mbid ?: "",
            name = artistDetailsDTO.name,
            url = artistDetailsDTO.url,
            listeners = artistDetailsDTO.statsDTO.listeners.toLong(),
            playcount = artistDetailsDTO.statsDTO.playcount.toLong(),
            isOnTour = artistDetailsDTO.ontour == "1",
            isStreamable = artistDetailsDTO.streamable == "1",
            content = artistDetailsDTO.biographyDTO.content,
            summary = artistDetailsDTO.biographyDTO.summary,
            published = artistDetailsDTO.biographyDTO.published,

            images = artistDetailsDTO.imageDTO?.associate { imageDto: ImageDTO ->
                imageDto.size to imageDto.text
            },

            similarArtists = artistDetailsDTO.similarArtistsDTO.artist.map { sArtistDto: SimpleArtistDTO ->
                SimpleArtistModel(
                        name = sArtistDto.name,
                        url = sArtistDto.url,
                        images = sArtistDto.imageDTO.associate { imageDto: ImageDTO ->
                            imageDto.size to imageDto.text
                        }
                )
            },

            tags = artistDetailsDTO.tagsDTO.tagDTO.map { tagDto: TagDTO ->
                TagModel(
                        name = tagDto.name,
                        url = tagDto.url
                )
            }
    )
}