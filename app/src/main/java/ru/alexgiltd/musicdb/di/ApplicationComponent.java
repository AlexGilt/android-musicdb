package ru.alexgiltd.musicdb.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;
import ru.alexgiltd.musicdb.App;
import ru.alexgiltd.musicdb.di.fragment.ArtistDetailsFragmentComponent;
import ru.alexgiltd.musicdb.di.fragment.ArtistListFragmentModule;
import ru.alexgiltd.musicdb.di.fragment.SongListFragmentModule;
import ru.alexgiltd.musicdb.ui.artist.ArtistDetailsActivity;
import ru.alexgiltd.musicdb.ui.song.SongDetailFragment;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        ApplicationModule.class,
        RoomModule.class,
        ArtistListFragmentModule.class,
        SongListFragmentModule.class
})
public interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        ApplicationComponent create(@BindsInstance Context applicationContext);
    }

    void inject(App application);

    void inject(SongDetailFragment fragment);

    void inject(ArtistDetailsActivity activity);

    ArtistDetailsFragmentComponent.Factory ArtistDetailsFragmentComponentFactory();
}
