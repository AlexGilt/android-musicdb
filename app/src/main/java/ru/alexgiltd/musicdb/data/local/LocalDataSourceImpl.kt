package ru.alexgiltd.musicdb.data.local

import android.util.Log
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ru.alexgiltd.musicdb.model.ArtistModel
import ru.alexgiltd.musicdb.model.SimpleArtistModel
import ru.alexgiltd.musicdb.model.local.artist.ArtistInfoLocal
import ru.alexgiltd.musicdb.model.local.artist.ArtistLocal
import ru.alexgiltd.musicdb.model.mapper.simpleArtistModelToArtistLocal
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

const val TAG = "LocalDataSourceImpl"

@Singleton
class LocalDataSourceImpl @Inject constructor(private val database: MusicDatabase) : LocalDataSource {

    override fun addArtistDetails(artistModel: ArtistModel) {

        database.artistDao()
                .findArtistByName(artistModel.name)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .map {
                    val artistInfo = ArtistInfoLocal(
                            artistId = it.id,
                            isStreamable = artistModel.isStreamable,
                            isOnTour = artistModel.isOnTour,
                            listeners = artistModel.listeners,
                            playcount = artistModel.playcount,
                            published = artistModel.published,
                            summary = artistModel.summary,
                            content = artistModel.content
                    )
                    artistInfo.similarArtists = artistModel.similarArtists.map(::simpleArtistModelToArtistLocal)

                    artistInfo
                }
                .flatMap { artistInfo ->
                    addArtistsObs(artistInfo.similarArtists!!)
                }
//                .flatMap {
//                    database.artistDao().addArtistInfo(it)
//                            .subscribeOn(Schedulers.io())
//                }
                .doOnError { throwable -> Timber.e(throwable, "addArtistDetails(): ") }
                .subscribe()


    }

    override fun getArtistDetailsByName(artistName: String): Single<ArtistModel> {
        TODO("not implemented")
    }

    override fun getArtists(limit: Int): Observable<List<SimpleArtistModel>> {
        return database.artistDao()
                .getArtistsWithImages(limit)
                .concatMap { list ->
                    Observable.fromIterable(list)
                            .map { artistWithImagesLocal ->
                                SimpleArtistModel(
                                    name = artistWithImagesLocal.name,
                                    mbid = artistWithImagesLocal.mbid,
                                    url = artistWithImagesLocal.url,
                                    images = artistWithImagesLocal.images.associate { imageLocal ->
                                        imageLocal.size to imageLocal.url
                                    }
                                )
                            }
                            .toList()
                            .toObservable()
                }
    }


    override fun addArtists(artists: List<SimpleArtistModel>) {

        val localArtists = artists.map(::simpleArtistModelToArtistLocal)

        database.artistDao()
                .addArtists(localArtists)
                .toObservable()
                .flatMap { Observable.fromIterable(it) }
                .zipWith(localArtists) { receivedIndex: Long, artist: ArtistLocal ->

                    val refreshedArtist = artist.copy(id = receivedIndex)
                    refreshedArtist.images = artist.images?.map {
                        it.copy(artistId = refreshedArtist.id)
                    }

                    refreshedArtist
                }
                // adding images of artists to database
                .flatMap { database.artistDao().addImages(it.images!!).toObservable() }
                .doOnError { throwable -> Timber.e(throwable, "addArtists(): ") }
                .subscribe()
    }

    private fun addArtistsObs(artists: List<ArtistLocal>): Observable<ArtistLocal> {

        return database.artistDao()
                .addArtists(artists)
                .toObservable()
                .flatMap { list ->
                    Observable.fromIterable(list)
                            .zipWith(artists) { receivedIndex, artist ->

                                val refreshedArtist = artist.copy(id = receivedIndex)
                                refreshedArtist.images = artist.images?.map {
                                    it.copy(artistId = refreshedArtist.id)
                                }

                                refreshedArtist
                            }
                }
    }
}