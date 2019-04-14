package ru.alexgiltd.musicdb.ui;

import android.os.Bundle;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import ru.alexgiltd.musicdb.R;
import ru.alexgiltd.musicdb.presentation.MainPresenter;
import ru.alexgiltd.musicdb.presentation.MainView;
import ru.alexgiltd.musicdb.ui.artist.ArtistListFragment;
import ru.alexgiltd.musicdb.ui.home.HomeFragment;
import ru.alexgiltd.musicdb.ui.song.SongListFragment;
import ru.alexgiltd.musicdb.util.moxy.mvp.MvpAppCompatActivity;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    public static final String HOME_FRAGMENT = "homeFragment";
    public static final String ARTIST_LIST_FRAGMENT = "artistListFragment";
    public static final String SONG_LIST_FRAGMENT = "songFragment";

    @InjectPresenter
    MainPresenter presenter;

    public static void openFragment(AppCompatActivity activity,
                                    Fragment fragment,
                                    String fragmentTag) {

        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        Fragment createdFragment = fragmentManager.findFragmentByTag(fragmentTag);
        if (createdFragment == null) {
            createdFragment = fragment;
        }
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, createdFragment, fragmentTag)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar_main);
        BottomNavigationView btmNavigation = findViewById(R.id.main_bottom_navigation);

        setSupportActionBar(toolbar);

        btmNavigation.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home_menu:
                    presenter.onTabItemClicked(HomeFragment.TAG);
                    return true;
                case R.id.artists_menu:
                    presenter.onTabItemClicked(ArtistListFragment.TAG);
                    return true;
                case R.id.songs_menu:
                    presenter.onTabItemClicked(SongListFragment.TAG);
                    return true;
            }
            return false;
        });
    }

    @Override
    public void displayTab(String tabTag) {

        Fragment fragment;

        switch (tabTag) {
            case HomeFragment.TAG:
                fragment = HomeFragment.newInstance();
                break;
            case ArtistListFragment.TAG:
                fragment = ArtistListFragment.newInstance();
                break;
            case SongListFragment.TAG:
                fragment = SongListFragment.newInstance();
                break;
            default:
                return;
        }

        openFragment(this, fragment, tabTag);

    }

    @Override
    public void showTabTitle(String tabTag) {

        ActionBar actionBar = getSupportActionBar();
        if (actionBar == null) return;

        switch (tabTag) {
            case HomeFragment.TAG:
                actionBar.setTitle(R.string.home_title_menu);
                break;
            case SongListFragment.TAG:
                actionBar.setTitle(R.string.songs_title_menu);
                break;
            case ArtistListFragment.TAG:
                actionBar.setTitle(R.string.artist_list_title);
                break;
        }

    }
}
