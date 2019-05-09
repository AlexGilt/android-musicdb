package ru.alexgiltd.musicdb.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.alexgiltd.musicdb.model.local.artist.ArtistInfoLocal
import ru.alexgiltd.musicdb.model.local.artist.ArtistInfoToArtistJoin
import ru.alexgiltd.musicdb.model.local.artist.ArtistLocal
import ru.alexgiltd.musicdb.model.local.artist.ImageLocal

@Database(
    entities = [
        ArtistLocal::class,
        ArtistInfoLocal::class,
        ImageLocal::class,
        ArtistInfoToArtistJoin::class
    ],
    version = 1
)
abstract class MusicDatabase : RoomDatabase() {

    abstract fun artistDao(): ArtistDao

}
