package ru.alexgiltd.musicdb.ui.song

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.alexgiltd.musicdb.R
import ru.alexgiltd.musicdb.util.openFragmentWithoutBackStack
import timber.log.Timber

class SongDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_detail)

        val receivedData = intent?.extras
        if (receivedData != null) {

            val songDetailsFragment = SongDetailsFragment.newInstance(
                    receivedData.getString(SONG_DETAIL_ARG_ARTIST)!!,
                    receivedData.getString(SONG_DETAIL_ARG_SONG)!!
            )
            openFragmentWithoutBackStack(songDetailsFragment, SongDetailsFragment.TAG)
        } else {
            Timber.e("onCreate: extras is null")
            finish()
        }
    }

    companion object {
        const val SONG_DETAIL_ARG_SONG = "SONG_DETAIL_ARG_SONG"
        const val SONG_DETAIL_ARG_ARTIST = "SONG_DETAIL_ARG_ARTIST"

        @JvmStatic
        fun start(activity: Activity, songName: String, artistName: String) {
            val intent = Intent(activity, SongDetailsActivity::class.java)
            intent.putExtra(SONG_DETAIL_ARG_SONG, songName)
            intent.putExtra(SONG_DETAIL_ARG_ARTIST, artistName)
            activity.startActivity(intent)
        }
    }
}
