package ru.alexgiltd.musicdb.ui.artist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.alexgiltd.musicdb.R
import ru.alexgiltd.musicdb.util.openFragmentWithoutBackStack
import timber.log.Timber


class ArtistDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artist_details)

        val receivedData = intent?.extras
        if (receivedData != null) {

            val fragment = ArtistDetailsFragment.newInstance(
                    receivedData.getString(ArtistDetailsFragment.ARTIST_DETAILS_ARG_STRING)!!
            )
            openFragmentWithoutBackStack(fragment, ArtistDetailsFragment.TAG)

        } else {
            Timber.e("onCreate: extras is null")
            finish()
        }
    }

    companion object {
        @JvmStatic
        fun start(activity: Activity, artistName: String) {
            val intent = Intent(activity, ArtistDetailsActivity::class.java)
            intent.putExtra(ArtistDetailsFragment.ARTIST_DETAILS_ARG_STRING, artistName)
            activity.startActivity(intent)
        }
    }
}