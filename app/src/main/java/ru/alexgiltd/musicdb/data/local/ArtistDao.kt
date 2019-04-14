package ru.alexgiltd.musicdb.data.local

import androidx.room.*
import io.reactivex.Observable
import io.reactivex.Single
import ru.alexgiltd.musicdb.model.local.artist.ArtistLocal
import ru.alexgiltd.musicdb.model.local.artist.ArtistWithImage
import ru.alexgiltd.musicdb.model.local.artist.Image

@Dao
interface ArtistDao {

    @Transaction
    @Query("SELECT * FROM artist")
    fun getAllArtistsWithImages(): Observable<List<ArtistWithImage>>

    @Query("SELECT * FROM artist")
    fun getAllArtists(): List<ArtistLocal>

    @Query("SELECT * FROM artist_image WHERE artist_id = :artistId")
    fun getImagesForArtist(artistId: Int): List<Image>

    @Query("SELECT * FROM artist WHERE name LIKE :name LIMIT 1")
    fun findArtistByName(name: String): ArtistLocal

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addArtists(artists: List<ArtistLocal>): Single<List<Long>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addImages(images: List<Image>): Single<List<Long>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addArtist(artist: ArtistLocal): Long

    // TODO: consider making an transactional method of adding an artist and the artist's image
    /* @Dao
    public abstract class EmployeeCarDao {

        @Insert
        public abstract void insertEmployee(Employee employee);

        @Insert
        public abstract void insertCar(Car car);


        @Transaction
        public void insertCarAndEmployee(Car car, Employee employee) {
            insertCar(car);
            insertEmployee(employee);
        }

    }*/
}