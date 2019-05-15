package ru.alexgiltd.musicdb.presentation.artist

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.alexgiltd.musicdb.data.repository.Repository
import ru.alexgiltd.musicdb.model.ArtistModel
import ru.alexgiltd.musicdb.model.SimpleArtistModel
import ru.alexgiltd.musicdb.util.BasePresenter
import ru.alexgiltd.musicdb.util.Constants.EXTRA_LARGE_IMAGE
import javax.inject.Inject

@InjectViewState
class ArtistDetailsPresenter @Inject constructor(
        private val repository: Repository
) : BasePresenter<ArtistDetailsView>() {

    private var artistName: String? = null
    // TODO: make the field without "lateinit" modifier
    private lateinit var artist: ArtistModel

    override fun onFirstViewAttach() {
        loadArtistDetails()
    }

    fun setArtistDetailsModel(artistName: String) {
        this.artistName = artistName
    }

    fun onSimilarArtistItemClicked(simpleArtistModel: SimpleArtistModel) {
        viewState.openArtistDetails(simpleArtistModel.name)
    }

    fun onFavoriteBtnClicked() {
        // TODO: implement adding the artist to favorites
        viewState.showFavoriteArtistNotification(artist.name)
    }

    private fun showData() {
        viewState.showArtistName(artist.name)
        viewState.showArtistDescription(artist.content)
        viewState.showArtistImage(artist.images?.get(EXTRA_LARGE_IMAGE)!!)
        viewState.showSimilarArtists(artist.similarArtists)
    }

    private fun loadArtistDetails() {

        val disposable = repository.getArtistDetailsByName(artistName!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { viewState.onStartLoading() }
                .subscribe(
                        { artist ->
                            viewState.onFinishLoading()
                            this@ArtistDetailsPresenter.artist = artist
                            showData()
                        },
                        { error ->
                            viewState.onFinishLoading()
                            Log.e(TAG, "loadArtistDetails: ", error)
                            viewState.showError(error.message!!)
                        }
                )

        unsubscribeOnDestroy(disposable)

    }

    companion object {

        @JvmStatic
        private val TAG = ArtistDetailsPresenter::class.java.simpleName
    }
}