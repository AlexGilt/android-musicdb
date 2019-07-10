package ru.alexgiltd.musicdb.di.fragment;

import dagger.BindsInstance;
import dagger.Subcomponent;
import ru.alexgiltd.musicdb.ui.artist.ArtistDetailsFragment;

@Subcomponent
public interface ArtistDetailsFragmentComponent {

    @Subcomponent.Factory
    interface Factory {
        ArtistDetailsFragmentComponent create(@BindsInstance @ArtistName String artistName);
    }

    void inject(ArtistDetailsFragment artistDetailsFragment);
}
