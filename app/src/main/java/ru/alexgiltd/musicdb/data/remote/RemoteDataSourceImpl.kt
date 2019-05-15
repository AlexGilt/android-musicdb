package ru.alexgiltd.musicdb.data.remote

import io.reactivex.Observable
import io.reactivex.Single
import ru.alexgiltd.musicdb.data.remote.api.LastFmService
import ru.alexgiltd.musicdb.model.ArtistModel
import ru.alexgiltd.musicdb.model.SimpleArtistModel
import ru.alexgiltd.musicdb.model.TrackDetailsModel
import ru.alexgiltd.musicdb.model.TrackModel
import ru.alexgiltd.musicdb.model.mapper.mapToArtistDetailModel
import ru.alexgiltd.musicdb.model.mapper.mapToSimplifiedArtistModelList
import ru.alexgiltd.musicdb.model.mapper.mapToTrackDetailsModel
import ru.alexgiltd.musicdb.model.mapper.mapToTrackModelList
import ru.alexgiltd.musicdb.model.remote.BaseResponse
import ru.alexgiltd.musicdb.model.remote.artistinfo.ArtistInfoResponse
import ru.alexgiltd.musicdb.model.remote.artists.ArtistsResponse
import ru.alexgiltd.musicdb.model.remote.trackinfo.TrackInfoResponse
import ru.alexgiltd.musicdb.model.remote.tracks.TracksResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSourceImpl @Inject constructor(
        private val lastFmService: LastFmService
) : RemoteDataSource {

    override fun getArtists(limit: Int): Observable<List<SimpleArtistModel>> {
        return lastFmService.getArtistList(limit)
                .doOnNext(this::throwExceptionIfApiError)
                .map(ArtistsResponse::mapToSimplifiedArtistModelList)
    }

    override fun getTracks(limit: Int): Observable<List<TrackModel>> {
        return lastFmService.getTrackList(limit)
                .doOnNext(this::throwExceptionIfApiError)
                .map(TracksResponse::mapToTrackModelList)
    }

    override fun getArtistDetailsByMbid(mbid: String): Single<ArtistModel> {
        return lastFmService.getArtistInfoByMbid(mbid)
                .doOnSuccess(this::throwExceptionIfApiError)
                .map(ArtistInfoResponse::mapToArtistDetailModel)
    }

    override fun getArtistDetailsByName(artistName: String): Single<ArtistModel> {
        return lastFmService.getArtistInfoByName(artistName)
                .doOnSuccess(this::throwExceptionIfApiError)
                .map(ArtistInfoResponse::mapToArtistDetailModel)
    }

    override fun getTrackDetailsByName(artistName: String, trackName: String): Single<TrackDetailsModel> {
        return lastFmService.getTrackInfoByName(artistName, trackName)
                .doOnSuccess(this::throwExceptionIfApiError)
                .map(TrackInfoResponse::mapToTrackDetailsModel)
    }

    private fun throwExceptionIfApiError(baseResponse: BaseResponse) {
        if (baseResponse.errorCode != 0) {
            throw RuntimeException("Error has occurred on server: " +
                    "Error code: ${baseResponse.errorCode}, message: ${baseResponse.message}")
        }
    }

}