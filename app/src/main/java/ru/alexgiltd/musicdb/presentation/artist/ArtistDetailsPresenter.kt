package ru.alexgiltd.musicdb.presentation.artist

import com.arellomobile.mvp.InjectViewState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.alexgiltd.musicdb.data.repository.Repository
import ru.alexgiltd.musicdb.di.fragment.ArtistName
import ru.alexgiltd.musicdb.model.ArtistModel
import ru.alexgiltd.musicdb.model.SimpleArtistModel
import ru.alexgiltd.musicdb.util.BasePresenter
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class ArtistDetailsPresenter @Inject constructor(
        private val repository: Repository,
        @ArtistName private val artistName: String
) : BasePresenter<ArtistDetailsView>() {

    private lateinit var artist: ArtistModel

    override fun onFirstViewAttach() {
        loadArtistDetails()
    }

    fun onSimilarArtistItemClicked(simpleArtistModel: SimpleArtistModel) {
        viewState.openArtistDetails(simpleArtistModel.name)
    }

    fun onFavoriteBtnClicked() {
        // TODO: implement adding the artist to favorites
        viewState.showFavoriteArtistNotification(artist.name)
    }

    private fun loadArtistDetails() {
        val disposable = repository.getArtistDetailsByName(artistName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { viewState.onStartLoading() }
                .subscribe(this::doOnSuccess, this::doOnError)

        unsubscribeOnDestroy(disposable)
    }

    private fun doOnSuccess(artistModel: ArtistModel) {
        viewState.onFinishLoading()
        artist = artistModel
        showData()
    }

    private fun doOnError(throwable: Throwable) {
        Timber.e(throwable, "loadArtistDetails: ")
        viewState.onFinishLoading()
        viewState.showError(throwable.message ?: "")
    }

    private fun showData() {
        viewState.showArtistName(artist.name)
        viewState.showArtistDescription(artist.content)
        viewState.showArtistImage(artist.imageUrl)
        viewState.showSimilarArtists(artist.similarArtists)
    }
}
