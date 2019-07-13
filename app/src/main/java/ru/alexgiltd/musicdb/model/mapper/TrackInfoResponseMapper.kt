package ru.alexgiltd.musicdb.model.mapper

import ru.alexgiltd.musicdb.model.SimpleArtistModel
import ru.alexgiltd.musicdb.model.TrackDetailsModel
import ru.alexgiltd.musicdb.model.remote.trackinfo.TrackInfoResponse

fun mapTrackInfoResponseToTrackDetailsModel(r: TrackInfoResponse): TrackDetailsModel {
    return TrackDetailsModel(
            name = r.trackInfoDTO.name,
            mbid = r.trackInfoDTO.mbid,
            simpleArtistModel = SimpleArtistModel(
                    name = r.trackInfoDTO.artist.name,
                    mbid = r.trackInfoDTO.artist.mbid,
                    url = r.trackInfoDTO.artist.url,
                    images = null
            ),
            summary = r.trackInfoDTO.wiki.summary,
            content = r.trackInfoDTO.wiki.content
    )
}
