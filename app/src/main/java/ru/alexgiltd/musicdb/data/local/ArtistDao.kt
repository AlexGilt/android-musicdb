package ru.alexgiltd.musicdb.data.local

import androidx.room.*
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import ru.alexgiltd.musicdb.model.local.artist.ArtistLocal
import ru.alexgiltd.musicdb.model.local.artist.ArtistWithImage
import ru.alexgiltd.musicdb.model.local.artist.Image

@Dao
interface ArtistDao {

    @Transaction
    @Query("SELECT * FROM artist LIMIT :limit")
    fun getArtistsWithImages(limit: Int): Observable<List<ArtistWithImage>>

    @Query("SELECT * FROM artist")
    fun getAllArtists(): Observable<List<ArtistLocal>>

    @Query("SELECT * FROM artist_image WHERE artist_id = :artistId")
    fun getImagesForArtist(artistId: Int): List<Image>

    @Query("SELECT * FROM artist WHERE name LIKE :name LIMIT 1")
    fun findArtistByName(name: String): Maybe<ArtistLocal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addArtists(artists: List<ArtistLocal>): Single<List<Long>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addImages(images: List<Image>): Single<List<Long>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addArtist(artist: ArtistLocal): Long

}