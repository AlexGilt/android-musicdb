package ru.alexgiltd.musicdb.data.local

import io.reactivex.Observable
import ru.alexgiltd.musicdb.model.ArtistModel
import ru.alexgiltd.musicdb.model.SimpleArtistModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InMemoryCacheDataSource @Inject constructor() : LocalDataSource {
    private val simpleArtists: MutableList<SimpleArtistModel> = ArrayList()
    private val artists: MutableSet<ArtistModel> = HashSet()

    override fun addArtists(artists: List<SimpleArtistModel>) {
        simpleArtists.addAll(artists)
    }

    override fun getArtists(limit: Int): Observable<List<SimpleArtistModel>> {
        return Observable.defer {
            Observable.just(simpleArtists.take(limit))
        }
    }

    override fun addArtistDetails(artistModel: ArtistModel) {
        artists.add(artistModel)
    }

    override fun getArtistDetailsByMbid(mbid: String): Observable<ArtistModel> {
        return createObservableForArtistDetails(mbid)
    }

    override fun getArtistDetailsByName(artistName: String): Observable<ArtistModel> {
        return createObservableForArtistDetails(artistName)
    }

    private fun createObservableForArtistDetails(key: String): Observable<ArtistModel> {
        return Observable.defer {
            val cachedArtist: ArtistModel? = artists.find { artistModel ->
                artistModel.mbid == key || artistModel.name == key
            }
            if (cachedArtist != null)
                Observable.just(cachedArtist)
            else
                Observable.empty()
        }
    }
}