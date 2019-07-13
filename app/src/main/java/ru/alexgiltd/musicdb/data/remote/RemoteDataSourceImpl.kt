package ru.alexgiltd.musicdb.data.remote

import io.reactivex.Observable
import io.reactivex.Single
import ru.alexgiltd.musicdb.data.remote.api.LastFmService
import ru.alexgiltd.musicdb.model.ArtistModel
import ru.alexgiltd.musicdb.model.SimpleArtistModel
import ru.alexgiltd.musicdb.model.TrackDetailsModel
import ru.alexgiltd.musicdb.model.TrackModel
import ru.alexgiltd.musicdb.model.mapper.mapArtistsResponseToSimpleArtistModelList
import ru.alexgiltd.musicdb.model.mapper.mapToArtistDetailModel
import ru.alexgiltd.musicdb.model.mapper.mapTrackInfoResponseToTrackDetailsModel
import ru.alexgiltd.musicdb.model.mapper.mapTracksResponseToTrackModelList
import ru.alexgiltd.musicdb.model.remote.BaseResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSourceImpl @Inject constructor(
        private val lastFmService: LastFmService
) : RemoteDataSource {

    override fun getArtists(limit: Int): Observable<List<SimpleArtistModel>> {
        return lastFmService.getArtistList(limit)
                .doOnNext(this::throwExceptionIfApiErrorOccurred)
                .map(::mapArtistsResponseToSimpleArtistModelList)
    }

    override fun getTracks(limit: Int): Observable<List<TrackModel>> {
        return lastFmService.getTrackList(limit)
                .doOnNext(this::throwExceptionIfApiErrorOccurred)
                .map(::mapTracksResponseToTrackModelList)
    }

    override fun getArtistDetailsByMbid(mbid: String): Single<ArtistModel> {
        return lastFmService.getArtistInfoByMbid(mbid)
                .doOnSuccess(this::throwExceptionIfApiErrorOccurred)
                .map(::mapToArtistDetailModel)
    }

    override fun getArtistDetailsByName(artistName: String): Single<ArtistModel> {
        return lastFmService.getArtistInfoByName(artistName)
                .doOnSuccess(this::throwExceptionIfApiErrorOccurred)
                .map(::mapToArtistDetailModel)
    }

    override fun getTrackDetailsByName(
            artistName: String,
            trackName: String
    ): Single<TrackDetailsModel> {
        return lastFmService.getTrackInfoByName(artistName, trackName)
                .doOnSuccess(this::throwExceptionIfApiErrorOccurred)
                .map(::mapTrackInfoResponseToTrackDetailsModel)
    }

    private fun throwExceptionIfApiErrorOccurred(baseResponse: BaseResponse) {
        if (baseResponse.errorCode != 0) {
            throw LastFmService.ApiErrorException(baseResponse.errorCode, baseResponse.message)
        }
    }

}