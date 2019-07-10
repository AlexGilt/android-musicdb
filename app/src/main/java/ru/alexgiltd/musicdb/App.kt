package ru.alexgiltd.musicdb

import android.app.Application

import com.squareup.picasso.Picasso
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector

import ru.alexgiltd.musicdb.di.ApplicationComponent
import ru.alexgiltd.musicdb.di.DaggerApplicationComponent
import timber.log.Timber
import javax.inject.Inject

class App : Application(), HasAndroidInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()

        initLogger()
        initDagger()
        initPicasso()
    }

    private fun initLogger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun initDagger() {
        appComponent = DaggerApplicationComponent.factory()
                .create(this)
        appComponent.inject(this)
    }

    private fun initPicasso() {
        Picasso.setSingletonInstance(Picasso.Builder(this)
                .indicatorsEnabled(true)
                .build()
        )
    }

    companion object {

        @JvmStatic
        lateinit var appComponent: ApplicationComponent
            private set
    }

}
