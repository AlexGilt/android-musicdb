package ru.alexgiltd.musicdb.data.repository

import io.reactivex.Observable
import ru.alexgiltd.musicdb.data.local.LocalDataSource
import ru.alexgiltd.musicdb.data.remote.RemoteDataSource
import ru.alexgiltd.musicdb.model.ArtistModel
import ru.alexgiltd.musicdb.model.SimpleArtistModel
import ru.alexgiltd.musicdb.model.TrackDetailsModel
import ru.alexgiltd.musicdb.model.TrackModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(
        private val remoteDataSource: RemoteDataSource,
        private val localDataSource: LocalDataSource
) : Repository {

    override fun getArtists(limit: Int): Observable<List<SimpleArtistModel>> {
        val remoteObservable = remoteDataSource.getArtists(limit)
                .doOnNext(localDataSource::addArtists)

        val localObservable = localDataSource.getArtists(limit)

        return Observable.concat(localObservable, remoteObservable)
                .filter { it.isNotEmpty() }
                .take(1)
    }

    override fun getTracks(limit: Int): Observable<List<TrackModel>> {
        return remoteDataSource.getTracks(limit)
    }

    override fun getArtistDetailsByMbid(mbid: String): Observable<ArtistModel> {
        val remoteObservable = remoteDataSource.getArtistDetailsByMbid(mbid)
                .doOnNext(localDataSource::addArtistDetails)

        val localObservable = localDataSource.getArtistDetailsByMbid(mbid)

        return Observable.concat(localObservable, remoteObservable)
                .take(1)
    }

    override fun getArtistDetailsByName(artistName: String): Observable<ArtistModel> {
        val remoteObservable = remoteDataSource.getArtistDetailsByName(artistName)
                .doOnNext(localDataSource::addArtistDetails)

        val localObservable = localDataSource.getArtistDetailsByName(artistName)

        return Observable.concat(localObservable, remoteObservable)
                .take(1)
    }

    override fun getTrackDetailsByName(
            artistName: String,
            trackName: String
    ): Observable<TrackDetailsModel> {

        return remoteDataSource.getTrackDetailsByName(artistName, trackName)
    }

}
