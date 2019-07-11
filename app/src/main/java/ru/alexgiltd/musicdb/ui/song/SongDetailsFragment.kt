package ru.alexgiltd.musicdb.ui.song

import android.content.Context
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
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Provider


class SongDetailsFragment : MvpAppCompatFragment(), SongDetailsView {
    @InjectPresenter
    lateinit var presenter: SongDetailsPresenter

    @Inject
    lateinit var presenterProvider: Provider<SongDetailsPresenter>

    @ProvidePresenter
    fun providePresenter(): SongDetailsPresenter? = presenterProvider.get()

    override fun onAttach(context: Context) {
        App.appComponent.songDetailsFragmentComponentFactory()
                .create(
                        arguments?.getString(SONG_DETAILS_ARG_ARTIST_NAME),
                        arguments?.getString(SONG_DETAILS_ARG_SONG_NAME)
                )
                .inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_song_details, container, false)

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

    override fun onStartLoading() {
        Timber.d("onStartLoading(): works")
    }

    override fun onFinishLoading() {
        Timber.d("onFinishLoading(): works")
    }

    companion object {
        val TAG: String = SongDetailsFragment::class.java.simpleName
        const val SONG_DETAILS_ARG_SONG_NAME = "song_details_arg_name"
        const val SONG_DETAILS_ARG_ARTIST_NAME = "song_details_arg_artist_name"

        @JvmStatic
        fun newInstance(artistName: String, songName: String): SongDetailsFragment {
            val data = Bundle()
            data.putString(SONG_DETAILS_ARG_ARTIST_NAME, artistName)
            data.putString(SONG_DETAILS_ARG_SONG_NAME, songName)
            val fragment = SongDetailsFragment()
            fragment.arguments = data
            return fragment
        }
    }
}
