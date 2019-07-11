package ru.alexgiltd.musicdb.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import ru.alexgiltd.musicdb.R
import ru.alexgiltd.musicdb.ui.artist.ArtistListFragment
import ru.alexgiltd.musicdb.ui.home.HomeFragment
import ru.alexgiltd.musicdb.ui.song.SongListFragment
import ru.alexgiltd.musicdb.util.openFragmentWithBackStack
import ru.alexgiltd.musicdb.util.openFragmentWithoutBackStack

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            openFragmentWithoutBackStack(HomeFragment.newInstance(), HOME_FRAGMENT)
        }

        initBottomNavigation()
    }

    private fun initBottomNavigation() {
        main_bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home_menu -> {
                    openFragmentWithBackStack(HomeFragment.newInstance(), HOME_FRAGMENT)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.artists_menu -> {
                    openFragmentWithBackStack(ArtistListFragment.newInstance(), ARTIST_LIST_FRAGMENT)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.songs_menu -> {
                    openFragmentWithBackStack(SongListFragment.newInstance(), SONG_LIST_FRAGMENT)
                    return@setOnNavigationItemSelectedListener true
                }
                else -> false
            }
        }
    }

    companion object {
        const val HOME_FRAGMENT = "homeFragment"
        const val ARTIST_LIST_FRAGMENT = "artistListFragment"
        const val SONG_LIST_FRAGMENT = "songFragment"
    }
}
