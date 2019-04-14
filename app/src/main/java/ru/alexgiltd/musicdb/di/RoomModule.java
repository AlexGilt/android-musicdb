package ru.alexgiltd.musicdb.di;

import android.content.Context;

import javax.inject.Singleton;

import androidx.room.Room;
import dagger.Module;
import dagger.Provides;
import ru.alexgiltd.musicdb.data.local.MusicDatabase;

@Module
public abstract class RoomModule {

    @Provides
    @Singleton
    static MusicDatabase provideDatabase(Context context) {
        return Room.databaseBuilder(context, MusicDatabase.class, "musicDatabase.db")
                .build();
    }
}
