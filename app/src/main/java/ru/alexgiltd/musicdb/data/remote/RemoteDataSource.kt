package ru.alexgiltd.musicdb.data.remote

import io.reactivex.Observable
import ru.alexgiltd.musicdb.model.ArtistModel
import ru.alexgiltd.musicdb.model.SimpleArtistModel
import ru.alexgiltd.musicdb.model.TrackDetailsModel
import ru.alexgiltd.musicdb.model.TrackModel

interface RemoteDataSource {

    fun getArtists(limit: Int): Observable<List<SimpleArtistModel>>

    fun getTracks(limit: Int): Observable<List<TrackModel>>

    fun getArtistDetailsByMbid(mbid: String): Observable<ArtistModel>

    fun getArtistDetailsByName(artistName: String): Observable<ArtistModel>

    fun getTrackDetailsByName(artistName: String, trackName: String): Observable<TrackDetailsModel>

}