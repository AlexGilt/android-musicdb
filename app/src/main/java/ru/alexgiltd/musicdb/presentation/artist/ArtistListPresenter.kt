package ru.alexgiltd.musicdb.presentation.artist

import com.arellomobile.mvp.InjectViewState
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.alexgiltd.musicdb.data.repository.Repository
import ru.alexgiltd.musicdb.model.ArtistModel
import ru.alexgiltd.musicdb.util.BasePresenter
import timber.log.Timber
import java.util.*
import javax.inject.Inject

@InjectViewState
class ArtistListPresenter @Inject constructor(private val repository: Repository)
    : BasePresenter<ArtistListView>() {

    private val artists: MutableList<ArtistModel> = ArrayList()

    override fun onFirstViewAttach() {
        loadArtists()
    }

    fun onItemClicked(artistDetailsModel: ArtistModel) {
        viewState.openArtistDetails(artistDetailsModel)
    }

    private fun loadArtists() {
        val disposable = repository.getArtists(10)
                .flatMap { list ->
                    Observable.fromIterable(list)
                            .concatMap { simpleArtistModel ->
                                if (simpleArtistModel.mbid.isEmpty())
                                    repository.getArtistDetailsByName(simpleArtistModel.name)
                                            .subscribeOn(Schedulers.io())
                                else
                                    repository.getArtistDetailsByMbid(simpleArtistModel.mbid)
                                            .subscribeOn(Schedulers.io())
                            }
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { viewState.onStartLoading() }
                .subscribe(this::doOnNext, this::doOnError, this::doOnComplete)

        unsubscribeOnDestroy(disposable)
    }

    private fun doOnNext(artistModel: ArtistModel) {
        artists.add(artistModel)
        viewState.showArtists(ArrayList(artists))
    }

    private fun doOnError(throwable: Throwable) {
        viewState.onFinishLoading()
        viewState.showError(throwable.message!!)
        Timber.e(throwable, "loadArtists(): ")
    }

    private fun doOnComplete() {
        viewState.onFinishLoading()
    }
}
