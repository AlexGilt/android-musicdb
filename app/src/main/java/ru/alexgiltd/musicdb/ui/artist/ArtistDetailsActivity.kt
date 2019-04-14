package ru.alexgiltd.musicdb.ui.artist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import ru.alexgiltd.musicdb.R


class ArtistDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artist_details)

        val extras = intent?.extras
        if (extras == null) {
            Log.e(TAG, "onCreate: extras is null")
            finish()
        }

        val fragment = ArtistDetailsFragment.newInstance(
                extras?.getString(ArtistDetailsFragment.ARTIST_DETAILS_ARG_STRING)
        )

        val fragmentManager = supportFragmentManager

        val createdFragment = fragmentManager.findFragmentByTag(ArtistDetailsFragment.TAG) ?: fragment

        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, createdFragment, ArtistDetailsFragment.TAG)
                .commit()
    }

    companion object {

        private val TAG = ArtistDetailsActivity::class.java.simpleName

        @JvmStatic
        fun start(activity: Activity, artistName: String) {
            val intent = Intent(activity, ArtistDetailsActivity::class.java)
            intent.putExtra(ArtistDetailsFragment.ARTIST_DETAILS_ARG_STRING, artistName)
            activity.startActivity(intent)
        }
    }
}
