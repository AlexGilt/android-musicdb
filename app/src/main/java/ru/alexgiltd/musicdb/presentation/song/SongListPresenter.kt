package ru.alexgiltd.musicdb.presentation.song

import com.arellomobile.mvp.InjectViewState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.alexgiltd.musicdb.data.repository.Repository
import ru.alexgiltd.musicdb.model.TrackModel
import ru.alexgiltd.musicdb.util.BasePresenter
import timber.log.Timber
import javax.inject.Inject

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

        val disposable = repository.getTracks(39)
                //                .flatMap(Observable::fromIterable)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { viewState.onStartLoading() }
                .subscribe(
                        { tracks ->
                            viewState.onFinishLoading()
                            viewState.showSongs(tracks)
                        },
                        { error ->
                            Timber.e(error, "loadTrackList(): ")
                            viewState.onFinishLoading()
                            viewState.showError(error.message ?: "")
                        })

        unsubscribeOnDestroy(disposable)
    }

}
