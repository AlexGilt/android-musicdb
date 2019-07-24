package ru.alexgiltd.musicdb.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;
import ru.alexgiltd.musicdb.App;
import ru.alexgiltd.musicdb.di.fragment.ArtistDetailsFragmentComponent;
import ru.alexgiltd.musicdb.di.fragment.FactoryInjectorModule;
import ru.alexgiltd.musicdb.di.fragment.SongDetailsFragmentComponent;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        ApplicationModule.class,
        FactoryInjectorModule.class
})
public interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        ApplicationComponent create(@BindsInstance Context applicationContext);
    }

    void inject(App application);

    ArtistDetailsFragmentComponent.Factory artistDetailsFragmentComponentFactory();
    SongDetailsFragmentComponent.Factory songDetailsFragmentComponentFactory();
}
