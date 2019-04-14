package ru.alexgiltd.musicdb.presentation.song

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface SongDetailsView : MvpView {

    fun showError()

    fun showSongName(songName: String)

    fun showSongImage(songImageUrl: String)
}