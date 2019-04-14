package ru.alexgiltd.musicdb.presentation.song

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.alexgiltd.musicdb.model.TrackModel

@StateStrategyType(AddToEndSingleStrategy::class)
interface SongListView : MvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showError(message: String)

    fun onStartLoading()

    fun onFinishLoading()

    fun showSongs(tracks: List<TrackModel>)

    fun openSongDetails(track: TrackModel)
}
