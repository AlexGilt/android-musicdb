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

        loadArtistList()
    }

    fun onItemClicked(artistDetailsModel: ArtistModel) {
        viewState.openArtistDetails(artistDetailsModel)
    }

    private fun loadArtistList() {

        val disposable = repository.getArtists(10)
                .flatMap { list ->
                    Observable.fromIterable(list)
                            .concatMap { simpleArtistModel ->
                                if (simpleArtistModel.mbid.isEmpty())
                                    repository.getArtistDetailsByName(simpleArtistModel.name)
                                            .toObservable()
                                            .subscribeOn(Schedulers.io())
                                else
                                    repository.getArtistDetailsByMbid(simpleArtistModel.mbid)
                                            .toObservable()
                                            .subscribeOn(Schedulers.io())
                            }
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { viewState.onStartLoading() }
                .subscribe(
                        { artistModel ->
                            artists.add(artistModel)
                            viewState.showArtists(ArrayList(artists))
                        },
                        { error ->
                            viewState.onFinishLoading()
                            viewState.showError(error.message!!)
                            Timber.e(error, "loadArtistList(): ")
                        },
                        {
                            viewState.onFinishLoading()
                            Timber.d("loadArtistList(): onComplete()")
                        }
                )

        unsubscribeOnDestroy(disposable)

    }

}
