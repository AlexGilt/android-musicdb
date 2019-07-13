package ru.alexgiltd.musicdb.model.mapper

import ru.alexgiltd.musicdb.model.SimpleArtistModel
import ru.alexgiltd.musicdb.model.TrackModel
import ru.alexgiltd.musicdb.model.remote.tracks.ArtistDTO
import ru.alexgiltd.musicdb.model.remote.tracks.ImageDTO
import ru.alexgiltd.musicdb.model.remote.tracks.TrackDTO
import ru.alexgiltd.musicdb.model.remote.tracks.TracksResponse
import ru.alexgiltd.musicdb.util.Constants

fun mapTracksResponseToTrackModelList(r: TracksResponse): List<TrackModel> {
    return r.tracks.trackDTO.map(::mapTrackDtoToTrackModel)
}

private fun mapTrackDtoToTrackModel(trackDto: TrackDTO): TrackModel {
    return TrackModel(
            name = trackDto.name,
            id = trackDto.mbid,
            image = findArtistInfoImageDtoBySize(trackDto.imageDTO, Constants.LARGE_IMAGE),
            trackOwner = mapArtistDtoToSimpleArtistModel(trackDto.artistDTO)
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

private fun mapArtistDtoToSimpleArtistModel(artistDto: ArtistDTO): SimpleArtistModel {
    return SimpleArtistModel(
            mbid = artistDto.mbid,
            name = artistDto.name,
            url = artistDto.url
    )
}