package ru.alexgiltd.musicdb.presentation.artist;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ru.alexgiltd.musicdb.data.repository.Repository;
import ru.alexgiltd.musicdb.model.ArtistModel;
import ru.alexgiltd.musicdb.model.SimpleArtistModel;
import ru.alexgiltd.musicdb.util.BasePresenter;

import static ru.alexgiltd.musicdb.util.Constants.EXTRA_LARGE_IMAGE;

@InjectViewState
public class ArtistDetailsPresenter extends BasePresenter<ArtistDetailsView> {

    private static final String TAG = ArtistDetailsPresenter.class.getSimpleName();

    private final Repository repository;
    private String artistName;
    private ArtistModel artist;

    @Inject
    public ArtistDetailsPresenter(Repository repository) {
        this.repository = repository;
    }

    @Override
    protected void onFirstViewAttach() {
//        showData();
        loadArtistDetails();
    }

    public void setArtistDetailsModel(String artistName) {
        this.artistName = artistName;
    }

    public void onSimilarArtistItemClicked(SimpleArtistModel simpleArtistModel) {
        getViewState().openArtistDetails(simpleArtistModel.getName());
    }

    public void onFavoriteBtnClicked() {
        // TODO: implement adding the artist to favorites
        getViewState().showFavoriteArtistNotification(artist.getName());
    }

    private void showData() {
        getViewState().showArtistName(artist.getName());
        getViewState().showArtistDescription(artist.getContent());
        getViewState().showArtistImage(artist.getImages().get(EXTRA_LARGE_IMAGE));
        getViewState().showSimilarArtists(artist.getSimilarArtists());
    }

    private void loadArtistDetails() {

        getViewState().onStartLoading();

        Disposable disposable = repository.getArtistDetailsByName(artistName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(artist -> {
                            getViewState().onFinishLoading();
                            ArtistDetailsPresenter.this.artist = artist;
                            showData();
                        },
                        error -> {
                            getViewState().onFinishLoading();
                            getViewState().showError(error.getMessage());
                            Log.e(TAG, "loadArtistDetails: ", error);
                        });

        unsubscribeOnDestroy(disposable);

    }
}