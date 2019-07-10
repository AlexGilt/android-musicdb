package ru.alexgiltd.musicdb.di;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import ru.alexgiltd.musicdb.ui.artist.ArtistListFragment;

@Module(subcomponents = ArtistListFragmentComponent.class)
abstract class ArtistListFragmentModule {

    @Binds
    @IntoMap
    @ClassKey(ArtistListFragment.class)
    abstract AndroidInjector.Factory<?>
    bindArtistListFragmentInjectorFactory(ArtistListFragmentComponent.Factory factory);
}
