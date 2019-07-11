package ru.alexgiltd.musicdb.ui.song

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_song_details.*
import ru.alexgiltd.musicdb.App
import ru.alexgiltd.musicdb.R
import ru.alexgiltd.musicdb.presentation.song.SongDetailsPresenter
import ru.alexgiltd.musicdb.presentation.song.SongDetailsView
import ru.alexgiltd.musicdb.util.moxy.mvp.MvpAppCompatFragment
import javax.inject.Inject
import javax.inject.Provider


class SongDetailFragment : MvpAppCompatFragment(), SongDetailsView {

    @InjectPresenter
    lateinit var presenter: SongDetailsPresenter

    @Inject
    lateinit var presenterProvider: Provider<SongDetailsPresenter>

    @ProvidePresenter
    fun providePresenter(): SongDetailsPresenter? = presenterProvider.get()

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent
                .inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_song_details, container, false)

    override fun onViewCreated(v: View, savedInstanceState: Bundle?) {

    }

    override fun showSongName(songName: String) {
        collapsing_toolbar.title = songName
    }

    override fun showSongImage(songImageUrl: String) {
        Picasso.get()
                .load(songImageUrl)
                .fit()
                .centerCrop(Gravity.TOP)
                .into(image_song_details)
    }

    override fun showError() {
        Snackbar.make(requireView(), R.string.error_text, Snackbar.LENGTH_LONG)
                .show()
    }

    companion object {
        val TAG: String = SongDetailFragment::class.java.simpleName
        const val SONG_DETAILS_ARG_SONG_NAME = "song_details_arg_name"
        const val SONG_DETAILS_ARG_ARTIST_NAME = "song_details_arg_artist_name"

        @JvmStatic
        fun newInstance(songName: String, artistName: String): SongDetailFragment {
            val data = Bundle()
            data.putString(SONG_DETAILS_ARG_SONG_NAME, songName)
            data.putString(SONG_DETAILS_ARG_ARTIST_NAME, artistName)
            val fragment = SongDetailFragment()
            fragment.arguments = data
            return fragment
        }
    }
}
