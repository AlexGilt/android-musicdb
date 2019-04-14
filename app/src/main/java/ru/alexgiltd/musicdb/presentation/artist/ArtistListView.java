package ru.alexgiltd.musicdb.presentation.artist;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import ru.alexgiltd.musicdb.model.ArtistModel;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface ArtistListView extends MvpView {

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showError(String message);

    void onStartLoading();

    void onFinishLoading();

    void showArtists(List<ArtistModel> list);

    @StateStrategyType(SkipStrategy.class)
    void openArtistDetails(ArtistModel artistDetailsModel);
}
