package ru.alexgiltd.musicdb.di.fragment;

import dagger.BindsInstance;
import dagger.Subcomponent;
import ru.alexgiltd.musicdb.ui.song.SongDetailsFragment;

@Subcomponent
public interface SongDetailsFragmentComponent {

    @Subcomponent.Factory
    interface Factory {
        SongDetailsFragmentComponent create(
                @BindsInstance @ArtistName String artistName,
                @BindsInstance @SongName String songName
        );
    }

    void inject(SongDetailsFragment songDetailsFragment);
}
