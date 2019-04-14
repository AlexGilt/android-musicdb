package ru.alexgiltd.musicdb.data.local

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import ru.alexgiltd.musicdb.model.SimpleArtistModel
import ru.alexgiltd.musicdb.model.local.artist.ArtistLocal
import ru.alexgiltd.musicdb.model.local.artist.ArtistWithImage
import ru.alexgiltd.musicdb.model.local.artist.Image
import javax.inject.Inject
import javax.inject.Singleton

const val TAG = "LocalDataSourceImpl"

@Singleton
class LocalDataSourceImpl @Inject constructor(private val database: MusicDatabase) : LocalDataSource {

    override fun getAllArtists(): Observable<List<SimpleArtistModel>> {

        return database.artistDao()
                .getAllArtistsWithImages()
                .map { artists: List<ArtistWithImage> ->
                    artists.map {
                        SimpleArtistModel(
                            name = it.name,
                            mbid = it.mbid,
                            url = it.url,
                            images = it.images.associate { image: Image ->
                                image.size to image.url
                            }
                        )
                    }
                }

    }

    override fun addArtists(artists: List<SimpleArtistModel>) {

        val localArtists = artists.map {
            ArtistLocal(name = it.name, mbid = it.mbid, url = it.url)
        }

        val disposable = database.artistDao()
                .addArtists(localArtists)
                .subscribe { indices: List<Long> ->

                    // TODO: withdraw this hack with variable type of data class
                    val indexedArtists = localArtists.zip(indices) { artistLocal: ArtistLocal, i: Long ->
                        artistLocal.id = i
                    }

                    // TODO: withdraw this hack with variable type of data class
                    localArtists.zip(artists) { artistLocal, artistModel ->
                        artistLocal.images = artistModel.images?.map {
                            Image(artistId = artistLocal.id, size = it.key, url = it.value)
                        }
                    }

                    addImagesForArtists(localArtists)

                }

    }

    private fun addImagesForArtists(localArtists: List<ArtistLocal>) {

        localArtists.forEach {

            it.images?.let { images ->
                database.artistDao().addImages(images)
                        .subscribeOn(Schedulers.io())
//                        .doOnSuccess {
//                            Log.d(TAG, "Images added: $it")
//                        }
                        .subscribe()
            }

        }

/*        getAllArtists().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext {
                    Log.d(TAG, "addArtists(): ${it}")
                }
                .subscribe()*/
    }
}