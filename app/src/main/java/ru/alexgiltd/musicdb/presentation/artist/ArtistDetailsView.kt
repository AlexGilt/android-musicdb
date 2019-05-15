package ru.alexgiltd.musicdb.presentation.artist

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

import ru.alexgiltd.musicdb.model.SimpleArtistModel

@StateStrategyType(AddToEndSingleStrategy::class)
interface ArtistDetailsView : MvpView {

    fun showArtistName(artistName: String)

    fun showArtistImage(artistImage: String)

    fun showArtistDescription(artistDesc: String)

    fun showSimilarArtists(artistList: List<SimpleArtistModel>)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showFavoriteArtistNotification(artistName: String)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showError(message: String)

    fun onStartLoading()

    fun onFinishLoading()

    @StateStrategyType(SkipStrategy::class)
    fun openArtistDetails(artistName: String)
}
