package ru.alexgiltd.musicdb.di.fragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import ru.alexgiltd.musicdb.ui.artist.ArtistListFragment;
import ru.alexgiltd.musicdb.ui.song.SongListFragment;

@Module
public abstract class FactoryInjectorModule {

    @ContributesAndroidInjector
    abstract SongListFragment contributeSongListFragmentInjector();

    @ContributesAndroidInjector
    abstract ArtistListFragment contributeArtistListFragmentInjector();
}
