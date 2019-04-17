package ru.alexgiltd.musicdb.presentation.artist;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ru.alexgiltd.musicdb.data.repository.Repository;
import ru.alexgiltd.musicdb.model.ArtistModel;
import ru.alexgiltd.musicdb.util.BasePresenter;

@InjectViewState
public class ArtistListPresenter extends BasePresenter<ArtistListView> {

    private static final String TAG = ArtistListPresenter.class.getSimpleName();

    private final Repository repository;

    private final List<ArtistModel> artists = new ArrayList<>();

    @Inject
    public ArtistListPresenter(Repository repository) {
        this.repository = repository;
    }

    @Override
    protected void onFirstViewAttach() {

        loadArtistList();
    }

    public void onItemClicked(ArtistModel artistDetailsModel) {
        getViewState().openArtistDetails(artistDetailsModel);
    }

    private void loadArtistList() {

        Disposable disposable = repository.getArtists(10)
                .subscribeOn(Schedulers.io())
                .flatMap(simpleArtistModels -> Observable.fromIterable(simpleArtistModels)
                        .concatMap(artist -> artist.getMbid().isEmpty()
                                ? repository.getArtistDetailsByName(artist.getName()).toObservable().subscribeOn(Schedulers.io())
                                : repository.getArtistDetailsByMbid(artist.getMbid()).toObservable().subscribeOn(Schedulers.io())
                        )
                )
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        artistModel -> {
                            artists.add(artistModel);
                            getViewState().showArtists(new ArrayList<>(artists));
                        },
                        error -> {
                            getViewState().onFinishLoading();
                            getViewState().showError(error.getMessage());
                            Log.e(TAG, "loadArtistList(): ", error);
                        },
                        () -> {
                            getViewState().onFinishLoading();
                            Log.d(TAG, "addArtists(): onComplete()");
                        },
                        subscription -> {
                            getViewState().onStartLoading();
                        }
                );

        unsubscribeOnDestroy(disposable);

    }

}
