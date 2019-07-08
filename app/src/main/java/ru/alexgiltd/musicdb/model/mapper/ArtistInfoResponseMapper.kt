package ru.alexgiltd.musicdb.model.mapper

import ru.alexgiltd.musicdb.model.ArtistModel
import ru.alexgiltd.musicdb.model.SimpleArtistModel
import ru.alexgiltd.musicdb.model.TagModel
import ru.alexgiltd.musicdb.model.remote.artistinfo.*
import ru.alexgiltd.musicdb.util.Constants

fun mapToArtistDetailModel(response: ArtistInfoResponse): ArtistModel {

    return ArtistModel(
            mbid = response.artistDetailsDTO?.mbid ?: "",
            name = response.artistDetailsDTO.name,
            url = response.artistDetailsDTO.url,
            listeners = response.artistDetailsDTO.statsDTO.listeners.toLong(),
            playcount = response.artistDetailsDTO.statsDTO.playcount.toLong(),
            isOnTour = response.artistDetailsDTO.ontour == "1",
            isStreamable = response.artistDetailsDTO.streamable == "1",
            content = response.artistDetailsDTO.biographyDTO.content,
            summary = response.artistDetailsDTO.biographyDTO.summary,
            published = response.artistDetailsDTO.biographyDTO.published,

            imageUrl = findArtistInfoImageDtoBySize(
                    response.artistDetailsDTO.imageDTO,
                    Constants.LARGE_IMAGE
            ) ?: throw UnsupportedOperationException(),

            similarArtists = response.artistDetailsDTO.similarArtistsDTO.artist.map { sArtistDto: SimpleArtistDTO ->
                SimpleArtistModel(
                        name = sArtistDto.name,
                        url = sArtistDto.url,
                        images = sArtistDto.imageDTO.associate { imageDto: ImageDTO ->
                            imageDto.size to imageDto.text
                        }
                )
            },

            tags = response.artistDetailsDTO.tagsDTO.tagDTO.map { tagDto: TagDTO ->
                TagModel(
                        name = tagDto.name,
                        url = tagDto.url
                )
            }
    )
}

fun findArtistInfoImageDtoBySize(images: List<ImageDTO>, imageSize: String): String? {
    for (imageDTO in images) {
        if (imageDTO.size == imageSize)
            return imageDTO.text
    }
    return null
}