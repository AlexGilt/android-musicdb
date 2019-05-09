package ru.alexgiltd.musicdb.data.local

import io.reactivex.Observable
import io.reactivex.Single
import ru.alexgiltd.musicdb.model.ArtistModel
import ru.alexgiltd.musicdb.model.SimpleArtistModel

interface LocalDataSource {

    fun getArtists(limit: Int): Observable<List<SimpleArtistModel>>

    fun addArtists(artists: List<SimpleArtistModel>)

    fun addArtistDetails(artistModel: ArtistModel)

    fun getArtistDetailsByName(artistName: String): Single<ArtistModel>

}