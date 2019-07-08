package ru.alexgiltd.musicdb.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import ru.alexgiltd.musicdb.R
import ru.alexgiltd.musicdb.ui.artist.ArtistListFragment
import ru.alexgiltd.musicdb.ui.home.HomeFragment
import ru.alexgiltd.musicdb.ui.song.SongListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            openFragment(HomeFragment.newInstance(), HOME_FRAGMENT)
        }

        initBottomNavigation()
    }

    private fun initBottomNavigation() {
        main_bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home_menu -> {
                    openFragment(HomeFragment.newInstance(), HOME_FRAGMENT)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.artists_menu -> {
                    openFragment(ArtistListFragment.newInstance(), ARTIST_LIST_FRAGMENT)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.songs_menu -> {
                    openFragment(SongListFragment.newInstance(), SONG_LIST_FRAGMENT)
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

        fun AppCompatActivity.openFragment(fragment: Fragment, fragmentTag: String) {

            val fragmentManager = supportFragmentManager

            var createdFragment = fragmentManager.findFragmentByTag(fragmentTag)
            if (createdFragment == null) {
                createdFragment = fragment
            }
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, createdFragment, fragmentTag)
                    .addToBackStack(null)
                    .commitAllowingStateLoss()
        }
    }
}
