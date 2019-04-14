package ru.alexgiltd.musicdb.model.mapper

import ru.alexgiltd.musicdb.model.SimpleArtistModel
import ru.alexgiltd.musicdb.model.TrackModel
import ru.alexgiltd.musicdb.model.remote.tracks.ImageDTO
import ru.alexgiltd.musicdb.model.remote.tracks.TracksResponse

fun TracksResponse.mapToTrackModelList(): List<TrackModel> {

    val tracks = tracks.trackDTO.map {

        val trackModel = TrackModel()
        trackModel.id = it.mbid
        trackModel.name = it.name
        trackModel.images = it.imageDTO.associate { image: ImageDTO? ->
            image?.size to image?.text
        }

        trackModel.trackOwner = it.artistDTO.let { artistDTO ->
            SimpleArtistModel(
                    mbid = artistDTO.mbid ?: "",
                    name = artistDTO.name,
                    url = artistDTO.url
            )
        }

        return@map trackModel

    }

    return tracks
}