package ru.alexgiltd.musicdb.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.alexgiltd.musicdb.model.local.artist.ArtistLocal
import ru.alexgiltd.musicdb.model.local.artist.Image

@Database(entities = [ArtistLocal::class, Image::class], version = 1)
abstract class MusicDatabase : RoomDatabase() {

    abstract fun artistDao(): ArtistDao

}
