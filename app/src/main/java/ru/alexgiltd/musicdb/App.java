package ru.alexgiltd.musicdb;

import android.app.Application;

import com.squareup.picasso.Picasso;

import ru.alexgiltd.musicdb.di.ApplicationComponent;
import ru.alexgiltd.musicdb.di.DaggerApplicationComponent;

public class App extends Application  {

//    @Inject
//    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    private static ApplicationComponent appComponent;

    public static ApplicationComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerApplicationComponent.builder()
                .applicationContext(getApplicationContext())
                .build();

        appComponent.inject(this);

        picassoInit();
    }

    private void picassoInit() {
        Picasso.setSingletonInstance(new Picasso.Builder(this)
                .indicatorsEnabled(true)
                .build()
        );
    }

//    @Override
//    public AndroidInjector<Fragment> supportFragmentInjector() {
//        return fragmentDispatchingAndroidInjector;
//    }
}
