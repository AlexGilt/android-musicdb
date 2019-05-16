package ru.alexgiltd.musicdb.ui.artist


import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_artist_details.*
import ru.alexgiltd.musicdb.App
import ru.alexgiltd.musicdb.R
import ru.alexgiltd.musicdb.model.SimpleArtistModel
import ru.alexgiltd.musicdb.presentation.artist.ArtistDetailsPresenter
import ru.alexgiltd.musicdb.presentation.artist.ArtistDetailsView
import ru.alexgiltd.musicdb.util.moxy.mvp.MvpAppCompatFragment
import javax.inject.Inject
import javax.inject.Provider

class ArtistDetailsFragment : MvpAppCompatFragment(), ArtistDetailsView {

    private val similarArtistsAdapter = SimilarArtistListAdapter()

    @Inject
    internal lateinit var presenterProvider: Provider<ArtistDetailsPresenter>

    @InjectPresenter
    internal lateinit var presenter: ArtistDetailsPresenter

    @ProvidePresenter
    fun providePresenter(): ArtistDetailsPresenter {
        val presenter = presenterProvider.get()
        presenter.setArtistDetailsModel(arguments?.getString(ARTIST_DETAILS_ARG_STRING)!!)
        return presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent
                .inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, state: Bundle?): View? =
            inflater.inflate(R.layout.fragment_artist_details, container, false)

    override fun onViewCreated(v: View, savedInstanceState: Bundle?) {

        recycler_similar_artist_list.layoutManager = LinearLayoutManager(
                activity, LinearLayoutManager.HORIZONTAL, false
        )
        recycler_similar_artist_list.adapter = similarArtistsAdapter
        similarArtistsAdapter.onItemClickListener = presenter::onSimilarArtistItemClicked

        btn_add_to_favorites.setOnClickListener { view -> presenter.onFavoriteBtnClicked() }
    }

    override fun showArtistName(artistName: String) {
        collapsing_toolbar.title = artistName
    }

    override fun showArtistImage(artistImage: String) {
        Picasso.get()
                .load(artistImage)
                .fit()
                .centerCrop(Gravity.TOP)
                .into(image_artist_details, object : Callback.EmptyCallback() {
                    override fun onSuccess() {
                        appbar.setExpanded(true)
                    }
                })
    }

    override fun showArtistDescription(artistDesc: String) {
        text_artist_details_biography.text = artistDesc
    }

    override fun showSimilarArtists(artistList: List<SimpleArtistModel>) {
        similarArtistsAdapter.submitList(artistList)
    }

    override fun showFavoriteArtistNotification(artistName: String) {
        Snackbar.make(
                requireView(),
                requireContext().getString(R.string.favorite_button_text, artistName),
                Snackbar.LENGTH_LONG
        ).show()
    }

    override fun showError(message: String) {
        Snackbar.make(
                requireView(),
                collapsing_toolbar!!.context.getText(R.string.error_text),
                Snackbar.LENGTH_LONG
        ).show()
    }

    override fun onStartLoading() {
        content.visibility = View.GONE
        progressbar_artist_details.show()
    }

    override fun onFinishLoading() {
        content.visibility = View.VISIBLE
        progressbar_artist_details.hide()
    }

    override fun openArtistDetails(artistName: String) {
        ArtistDetailsActivity.start(requireActivity(), artistName)
    }

    companion object {

        const val TAG = "ArtistDetailsFragment"
        const val ARTIST_DETAILS_ARG_STRING = "ARTIST_DETAILS_ARG_STRING"

        @JvmStatic
        fun newInstance(artistName: String): ArtistDetailsFragment {
            val bundle = Bundle()
            bundle.putString(ARTIST_DETAILS_ARG_STRING, artistName)

            val fragment = ArtistDetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}
