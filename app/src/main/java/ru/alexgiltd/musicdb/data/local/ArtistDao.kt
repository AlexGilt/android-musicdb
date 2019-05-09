package ru.alexgiltd.musicdb.data.local

import androidx.room.*
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import ru.alexgiltd.musicdb.model.local.artist.ArtistInfoLocal
import ru.alexgiltd.musicdb.model.local.artist.ArtistLocal
import ru.alexgiltd.musicdb.model.local.artist.ArtistWithImagesLocal
import ru.alexgiltd.musicdb.model.local.artist.ImageLocal

@Dao
interface ArtistDao {

    @Query("SELECT * FROM artist LIMIT :limit")
    fun getArtistsWithImages(limit: Int): Observable<List<ArtistWithImagesLocal>>

    @Query("SELECT * FROM artist")
    fun getAllArtists(): Observable<List<ArtistLocal>>

    @Query("SELECT * FROM artist_image WHERE artist_id = :artistId")
    fun getImagesForArtist(artistId: Int): Single<List<ImageLocal>>

    @Query("SELECT * FROM artist WHERE name LIKE :name LIMIT 1")
    fun findArtistByName(name: String): Maybe<ArtistLocal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addArtists(artists: List<ArtistLocal>): Single<List<Long>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addImages(images: List<ImageLocal>): Single<List<Long>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addArtist(artist: ArtistLocal): Single<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addArtistInfo(artist: ArtistInfoLocal): Single<Long>

}