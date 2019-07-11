package ru.alexgiltd.musicdb.di.fragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import ru.alexgiltd.musicdb.ui.song.SongListFragment;

@Module
public abstract class SongListFragmentModule {

    @ContributesAndroidInjector
    abstract SongListFragment contributeSongListFragmentInjector();
}
