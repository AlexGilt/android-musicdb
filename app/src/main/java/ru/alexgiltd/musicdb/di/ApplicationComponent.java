package ru.alexgiltd.musicdb.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;
import ru.alexgiltd.musicdb.App;
import ru.alexgiltd.musicdb.di.fragment.ArtistDetailsFragmentComponent;
import ru.alexgiltd.musicdb.di.fragment.ArtistListFragmentModule;
import ru.alexgiltd.musicdb.ui.artist.ArtistDetailsActivity;
import ru.alexgiltd.musicdb.ui.artist.ArtistListFragment;
import ru.alexgiltd.musicdb.ui.song.SongDetailFragment;
import ru.alexgiltd.musicdb.ui.song.SongListFragment;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        ApplicationModule.class,
        RoomModule.class,
        ArtistListFragmentModule.class
})
public interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        ApplicationComponent create(@BindsInstance Context applicationContext);
    }

    void inject(App application);

    void inject(ArtistListFragment fragment);
    void inject(SongListFragment fragment);
    void inject(SongDetailFragment fragment);

    void inject(ArtistDetailsActivity activity);

    ArtistDetailsFragmentComponent.Factory ArtistDetailsFragmentComponentFactory();
}
