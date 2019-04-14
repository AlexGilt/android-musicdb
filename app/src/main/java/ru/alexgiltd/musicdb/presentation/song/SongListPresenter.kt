package ru.alexgiltd.musicdb.presentation.song

import android.util.Log

import com.arellomobile.mvp.InjectViewState

import javax.inject.Inject

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.alexgiltd.musicdb.data.repository.Repository
import ru.alexgiltd.musicdb.model.TrackModel
import ru.alexgiltd.musicdb.util.BasePresenter

@InjectViewState
class SongListPresenter @Inject constructor(private val repository: Repository)
    : BasePresenter<SongListView>() {

    override fun onFirstViewAttach() {
        loadTrackList()
    }

    fun onSongsItemClicked(track: TrackModel) {
        viewState.openSongDetails(track)
    }

    private fun loadTrackList() {

        viewState.onStartLoading()

        // TODO: change the inner logic of lastFm api hence the rest service count from 0,
        // so 40 is 39 exactly
        val disposable = repository.getTrackList(39)
                //                .flatMap(Observable::fromIterable)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { tracks ->
                            viewState.onFinishLoading()
                            viewState.showSongs(tracks)
                        },
                        { error ->
                            Log.e(TAG, "loadTrackList(): ", error)
                            viewState.onFinishLoading()
                            viewState.showError(error.message ?: "")
                        })

        unsubscribeOnDestroy(disposable)
    }

    companion object {
        const val TAG = "SongListPresenter"
    }
}
