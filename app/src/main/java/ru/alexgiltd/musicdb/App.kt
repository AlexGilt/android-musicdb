package ru.alexgiltd.musicdb

import android.app.Application

import com.squareup.picasso.Picasso

import ru.alexgiltd.musicdb.di.ApplicationComponent
import ru.alexgiltd.musicdb.di.DaggerApplicationComponent
import timber.log.Timber

class App : Application() {

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
        appComponent = DaggerApplicationComponent.builder()
                .applicationContext(applicationContext)
                .build()

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
