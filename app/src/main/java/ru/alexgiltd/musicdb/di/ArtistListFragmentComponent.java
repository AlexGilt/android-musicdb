package ru.alexgiltd.musicdb.di;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import ru.alexgiltd.musicdb.ui.artist.ArtistListFragment;

@Subcomponent
public interface ArtistListFragmentComponent extends AndroidInjector<ArtistListFragment> {

    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<ArtistListFragment> {}

}
