package ru.alexgiltd.musicdb.model.mapper

import ru.alexgiltd.musicdb.model.SimpleArtistModel
import ru.alexgiltd.musicdb.model.TrackDetailsModel
import ru.alexgiltd.musicdb.model.remote.trackinfo.TrackInfoResponse

fun TrackInfoResponse.mapToTrackDetailsModel(): TrackDetailsModel = TrackDetailsModel(
        name = trackInfoDTO.name,
        mbid = trackInfoDTO.mbid,
        simpleArtistModel = SimpleArtistModel(
                trackInfoDTO.artist.mbid,
                trackInfoDTO.artist.name,
                trackInfoDTO.artist.url,
                null
        ),
        summary = trackInfoDTO.wiki.summary,
        content = trackInfoDTO.wiki.content)
