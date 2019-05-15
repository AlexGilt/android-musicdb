package ru.alexgiltd.musicdb

import android.app.Application

import com.squareup.picasso.Picasso

import ru.alexgiltd.musicdb.di.ApplicationComponent
import ru.alexgiltd.musicdb.di.DaggerApplicationComponent

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        daggerInit()

        picassoInit()

    }

    private fun daggerInit() {
        appComponent = DaggerApplicationComponent.builder()
                .applicationContext(applicationContext)
                .build()

        appComponent.inject(this)
    }

    private fun picassoInit() {
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
