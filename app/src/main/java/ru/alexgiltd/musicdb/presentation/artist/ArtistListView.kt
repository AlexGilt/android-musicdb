package ru.alexgiltd.musicdb.presentation.artist

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

import ru.alexgiltd.musicdb.model.ArtistModel

@StateStrategyType(AddToEndSingleStrategy::class)
interface ArtistListView : MvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showError(message: String)

    fun onStartLoading()

    fun onFinishLoading()

    fun showArtists(list: List<ArtistModel>)

    @StateStrategyType(SkipStrategy::class)
    fun openArtistDetails(artistDetailsModel: ArtistModel)
}
