package ru.alexgiltd.musicdb.ui.song


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_song_list.*
import ru.alexgiltd.musicdb.R
import ru.alexgiltd.musicdb.model.TrackModel
import ru.alexgiltd.musicdb.presentation.song.SongListPresenter
import ru.alexgiltd.musicdb.presentation.song.SongListView
import ru.alexgiltd.musicdb.util.moxy.mvp.MvpAppCompatFragment
import javax.inject.Inject
import javax.inject.Provider

class SongListFragment : MvpAppCompatFragment(), SongListView {

    private val songsAdapter = SongListAdapter()

    @Inject
    internal lateinit var presenterProvider: Provider<SongListPresenter>

    @InjectPresenter
    internal lateinit var presenter: SongListPresenter

    @ProvidePresenter
    fun providePresenter(): SongListPresenter? = presenterProvider.get()

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_song_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        toolbar.title = view.context.getString(R.string.songs_title_menu)

        initRecycler()
    }

    private fun initRecycler() {
        recycler_song_list.layoutManager = LinearLayoutManager(requireActivity())
        songsAdapter.onItemClickListener = presenter::onSongsItemClicked
        recycler_song_list.adapter = songsAdapter
        recycler_song_list.setHasFixedSize(true)
    }

    override fun showError(message: String) {
        Snackbar.make(
                requireView(),
                requireContext().getString(R.string.error_text),
                Snackbar.LENGTH_LONG
        ).show()
    }

    override fun onStartLoading() {
        progressbar_song_list.visibility = View.VISIBLE
    }

    override fun onFinishLoading() {
        progressbar_song_list.visibility = View.GONE
    }

    override fun showSongs(tracks: List<TrackModel>) {
        songsAdapter.submitList(tracks)
    }

    override fun openSongDetails(track: TrackModel) {
        SongDetailActivity.start(requireActivity(), track.name, track.trackOwner.name)
    }

    companion object {
        @JvmStatic
        fun newInstance(): SongListFragment = SongListFragment()
    }
}
