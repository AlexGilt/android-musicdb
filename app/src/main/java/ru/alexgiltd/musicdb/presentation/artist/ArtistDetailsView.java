package ru.alexgiltd.musicdb.presentation.artist;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import ru.alexgiltd.musicdb.model.SimpleArtistModel;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface ArtistDetailsView extends MvpView {

    void showArtistName(String artistName);

    void showArtistImage(String artistImage);

    void showArtistDescription(String artistDesc);

    void showSimilarArtists(List<SimpleArtistModel> artistList);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showFavoriteArtistNotification(String artistName);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showError(String message);

    void onStartLoading();

    void onFinishLoading();

    @StateStrategyType(SkipStrategy.class)
    void openArtistDetails(String artistName);
}
