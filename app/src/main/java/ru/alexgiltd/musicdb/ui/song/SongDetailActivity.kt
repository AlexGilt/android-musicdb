package ru.alexgiltd.musicdb.ui.song

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.alexgiltd.musicdb.R

class SongDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_detail)

        val bundle = intent?.extras ?: finish()


    }

    companion object {

        const val SONG_DETAIL_ARG_SONG = "SONG_DETAIL_ARG_SONG"
        const val SONG_DETAIL_ARG_ARTISTS = "SONG_DETAIL_ARG_ARTISTS"

        @JvmStatic
        fun start(activity: Activity, songName: String, artistName: String) {
            val intent = Intent(activity, SongDetailActivity::class.java)
            intent.putExtra(SONG_DETAIL_ARG_SONG, songName)
            intent.putExtra(SONG_DETAIL_ARG_ARTISTS, artistName)
            activity.startActivity(intent)
        }
    }
}
