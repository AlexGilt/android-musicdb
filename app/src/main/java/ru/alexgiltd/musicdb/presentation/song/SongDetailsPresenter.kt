package ru.alexgiltd.musicdb.presentation.song

import com.arellomobile.mvp.InjectViewState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.alexgiltd.musicdb.data.repository.Repository
import ru.alexgiltd.musicdb.model.TrackDetailsModel
import ru.alexgiltd.musicdb.util.BasePresenter
import javax.inject.Inject

@InjectViewState
class SongDetailsPresenter @Inject constructor(private val repository: Repository)
    : BasePresenter<SongDetailsView>() {

    private lateinit var songName: String
    private lateinit var artistName: String

    override fun onFirstViewAttach() {

        loadTrackDetails()
    }

    private fun loadTrackDetails() {

        val disposable = repository.getTrackDetailsByName(artistName, songName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { trackDetails: TrackDetailsModel ->
                            viewState.showSongName(trackDetails.name)
                        },
                        { error: Throwable ->
                            viewState.showError()
                        }
                )

        unsubscribeOnDestroy(disposable)
    }
}