package ru.alexgiltd.musicdb.data.local

import android.util.Log
import io.reactivex.Observable
import ru.alexgiltd.musicdb.model.SimpleArtistModel
import ru.alexgiltd.musicdb.model.local.artist.ArtistLocal
import ru.alexgiltd.musicdb.model.local.artist.ArtistWithImage
import ru.alexgiltd.musicdb.model.local.artist.Image
import javax.inject.Inject
import javax.inject.Singleton

const val TAG = "LocalDataSourceImpl"

@Singleton
class LocalDataSourceImpl @Inject constructor(private val database: MusicDatabase) : LocalDataSource {

    override fun getArtists(limit: Int): Observable<List<SimpleArtistModel>>
            = database.artistDao()
            .getArtistsWithImages(limit)
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

    override fun addArtists(artists: List<SimpleArtistModel>) {

        val localArtists = artists.map {
            val artist = ArtistLocal(name = it.name, mbid = it.mbid, url = it.url)
            artist.images = it.images?.map { image ->
                Image(artistId = 0, size = image.key, url = image.value)
            }

            return@map artist
        }

        val disposable = database.artistDao()
                .addArtists(localArtists)
                .toObservable()
                .flatMap { Observable.fromIterable(it) }
                .zipWith(localArtists) { receivedIndex: Long, artist: ArtistLocal ->
                    val copy = artist.copy(id = receivedIndex)
                    copy.images = artist.images
                    return@zipWith copy
                }
                .map { artistLocal ->
                    artistLocal.images?.map {
                        it.copy(artistId = artistLocal.id)
                    }
                }
                // adding images of artists to database
                .flatMap { database.artistDao().addImages(it).toObservable() }
                .subscribe(
                        { imageIndices ->
                            Log.d(TAG, "addArtists(): onNext()")
                        },
                        { error ->
                            Log.e(TAG, "addArtists(): ", error)
                        },
                        {
                            Log.d(TAG, "addArtists(): onComplete()")
                        }
                )
    }

}