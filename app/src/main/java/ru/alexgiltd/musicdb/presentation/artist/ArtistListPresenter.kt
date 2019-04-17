package ru.alexgiltd.musicdb.presentation.artist

import android.util.Log

import com.arellomobile.mvp.InjectViewState

import java.util.ArrayList

import javax.inject.Inject

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.alexgiltd.musicdb.data.repository.Repository
import ru.alexgiltd.musicdb.model.ArtistModel
import ru.alexgiltd.musicdb.model.SimpleArtistModel
import ru.alexgiltd.musicdb.util.BasePresenter

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
                .subscribeOn(Schedulers.io())
                .flatMap {
                    Observable.fromIterable(it)
                            .concatMap { artist: SimpleArtistModel ->
                                if (artist.mbid.isEmpty())
                                    repository.getArtistDetailsByName(artist.name)
                                            .toObservable()
                                            .subscribeOn(Schedulers.io())
                                else
                                    repository.getArtistDetailsByMbid(artist.mbid)
                                            .toObservable()
                                            .subscribeOn(Schedulers.io())
                            }
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { artistModel ->
                            artists.add(artistModel)
                            viewState.showArtists(ArrayList(artists))
                        },
                        { error ->
                            viewState.onFinishLoading()
                            viewState.showError(error.message!!)
                            Log.e(TAG, "loadArtistList(): ", error)
                        },
                        {
                            viewState.onFinishLoading()
                            Log.d(TAG, "loadArtistList(): onComplete()")
                        },
                        { subscription -> viewState.onStartLoading() }
                )

        unsubscribeOnDestroy(disposable)

    }

    companion object {
        private val TAG = ArtistListPresenter::class.java.simpleName
    }
}
