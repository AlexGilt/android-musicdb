package ru.alexgiltd.musicdb.data.repository

import ru.alexgiltd.musicdb.model.ArtistModel
import ru.alexgiltd.musicdb.model.SimpleArtistModel

import io.reactivex.Observable
import io.reactivex.Single
import ru.alexgiltd.musicdb.model.TrackDetailsModel
import ru.alexgiltd.musicdb.model.TrackModel

interface Repository {

    fun getArtists(limit: Int): Observable<List<SimpleArtistModel>>

    fun getTracks(limit: Int): Observable<List<TrackModel>>

    fun getArtistDetailsByMbid(mbid: String): Single<ArtistModel>

    fun getArtistDetailsByName(artistName: String): Single<ArtistModel>

    fun getTrackDetailsByName(artistName: String, trackName: String): Single<TrackDetailsModel>

}