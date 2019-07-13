package ru.alexgiltd.musicdb.model.mapper

import ru.alexgiltd.musicdb.model.ArtistModel
import ru.alexgiltd.musicdb.model.SimpleArtistModel
import ru.alexgiltd.musicdb.model.TagModel
import ru.alexgiltd.musicdb.model.remote.artistinfo.ArtistInfoResponse
import ru.alexgiltd.musicdb.model.remote.artistinfo.ImageDTO
import ru.alexgiltd.musicdb.model.remote.artistinfo.SimpleArtistDTO
import ru.alexgiltd.musicdb.model.remote.artistinfo.TagDTO
import ru.alexgiltd.musicdb.util.Constants.LARGE_IMAGE
import java.lang.IllegalStateException

fun mapToArtistDetailModel(r: ArtistInfoResponse): ArtistModel {
    return ArtistModel(
            mbid = r.artistDetailsDTO?.mbid ?: "",
            name = r.artistDetailsDTO.name,
            url = r.artistDetailsDTO.url,
            listeners = r.artistDetailsDTO.statsDTO.listeners.toLong(),
            playcount = r.artistDetailsDTO.statsDTO.playcount.toLong(),
            isOnTour = r.artistDetailsDTO.ontour == "1",
            isStreamable = r.artistDetailsDTO.streamable == "1",
            content = r.artistDetailsDTO.biographyDTO.content,
            summary = r.artistDetailsDTO.biographyDTO.summary,
            published = r.artistDetailsDTO.biographyDTO.published,

            imageUrl = findArtistInfoImageDtoBySize(r.artistDetailsDTO.imageDTO, LARGE_IMAGE)
                    ?: throw IllegalStateException(),

            similarArtists = r.artistDetailsDTO.similarArtistsDTO.artist.map(::mapSimpleArtistDtoToSimpleArtistModel),

            tags = r.artistDetailsDTO.tagsDTO.tagDTO.map(::mapTagDtoToTagModel)
    )
}

private fun findArtistInfoImageDtoBySize(images: List<ImageDTO>?, imageSize: String): String? {
    if (images != null) {
        for (image in images) {
            if (image.size == imageSize)
                return image.text
        }
    }
    return null
}

private fun mapSimpleArtistDtoToSimpleArtistModel(sArtistDto: SimpleArtistDTO): SimpleArtistModel {
    return SimpleArtistModel(
            name = sArtistDto.name,
            url = sArtistDto.url,
            images = sArtistDto.imageDTO.associate { imageDto: ImageDTO ->
                imageDto.size to imageDto.text
            }
    )
}

private fun mapTagDtoToTagModel(tagDto: TagDTO): TagModel {
    return TagModel(
            name = tagDto.name,
            url = tagDto.url
    )
}
