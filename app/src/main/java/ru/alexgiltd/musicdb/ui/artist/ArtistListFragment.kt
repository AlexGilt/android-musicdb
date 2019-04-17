package ru.alexgiltd.musicdb.ui.artist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_artist_list.*
import ru.alexgiltd.musicdb.App
import ru.alexgiltd.musicdb.R
import ru.alexgiltd.musicdb.model.ArtistModel
import ru.alexgiltd.musicdb.presentation.artist.ArtistListPresenter
import ru.alexgiltd.musicdb.presentation.artist.ArtistListView
import ru.alexgiltd.musicdb.util.moxy.mvp.MvpAppCompatFragment
import javax.inject.Inject
import javax.inject.Provider


class ArtistListFragment : MvpAppCompatFragment(), ArtistListView {

    private val artistsAdapter = ArtistListAdapter()

    @Inject
    internal lateinit var presenterProvider: Provider<ArtistListPresenter>

    @InjectPresenter
    internal lateinit var presenter: ArtistListPresenter

    @ProvidePresenter
    fun providePresenter(): ArtistListPresenter? = presenterProvider.get()

    override fun onCreate(savedInstanceState: Bundle?) {
        App.getAppComponent()
                .inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_artist_list, container, false)

    override fun onViewCreated(v: View, savedInstanceState: Bundle?) {

        recycler_artists.layoutManager = LinearLayoutManager(requireActivity())
        artistsAdapter.onItemClickListener = presenter::onItemClicked
        recycler_artists.adapter = artistsAdapter
        recycler_artists.setHasFixedSize(true)

    }

    override fun showError(message: String) {
        Snackbar.make(requireView(), requireContext().getText(R.string.error_text), Snackbar.LENGTH_LONG)
                .show()
    }

    override fun onStartLoading() {
        progress_bar_artists.show()
    }

    override fun onFinishLoading() {
        progress_bar_artists.hide()
    }

    override fun showArtists(list: List<ArtistModel>) {
        artistsAdapter.submitList(list)
    }

    override fun openArtistDetails(artistDetailsModel: ArtistModel) {
        ArtistDetailsActivity.start(requireActivity(), artistDetailsModel.name)
    }

    companion object {

        const val TAG = "ArtistListFragment"

        @JvmStatic
        fun newInstance(): ArtistListFragment = ArtistListFragment()
    }

}
