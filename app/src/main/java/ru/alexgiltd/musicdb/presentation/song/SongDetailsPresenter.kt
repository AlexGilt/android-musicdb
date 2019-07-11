package ru.alexgiltd.musicdb.presentation.song

import com.arellomobile.mvp.InjectViewState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.alexgiltd.musicdb.data.repository.Repository
import ru.alexgiltd.musicdb.di.fragment.ArtistName
import ru.alexgiltd.musicdb.di.fragment.SongName
import ru.alexgiltd.musicdb.model.TrackDetailsModel
import ru.alexgiltd.musicdb.util.BasePresenter
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class SongDetailsPresenter @Inject constructor(
        private val repository: Repository,
        @ArtistName private val artistName: String,
        @SongName private val songName: String
) : BasePresenter<SongDetailsView>() {

    override fun onFirstViewAttach() {
        loadTrackDetails()
    }

    private fun loadTrackDetails() {
        val disposable = repository.getTrackDetailsByName(artistName, songName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { viewState.onStartLoading() }
                .subscribe(this::doOnSuccess, this::doOnError)

        unsubscribeOnDestroy(disposable)
    }

    private fun doOnSuccess(trackDetailsModel: TrackDetailsModel) {
        viewState.onFinishLoading()
        viewState.showSongName(trackDetailsModel.name)
    }

    private fun doOnError(throwable: Throwable) {
        Timber.e(throwable)
        viewState.onFinishLoading()
        viewState.showError()
    }
}