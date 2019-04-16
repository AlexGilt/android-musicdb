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
import ru.alexgiltd.musicdb.data.local.LocalDataSource;
import ru.alexgiltd.musicdb.data.repository.Repository;
import ru.alexgiltd.musicdb.model.ArtistModel;
import ru.alexgiltd.musicdb.util.BasePresenter;

@InjectViewState
public class ArtistListPresenter extends BasePresenter<ArtistListView> {

    private static final String TAG = ArtistListPresenter.class.getSimpleName();

    private final Repository repository;
    private final LocalDataSource localDataSource;

    private final List<ArtistModel> artists = new ArrayList<>();

    @Inject
    public ArtistListPresenter(Repository repository, LocalDataSource localDataSource) {
        this.repository = repository;
        this.localDataSource = localDataSource;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        loadArtistList();
    }

    public void onItemClicked(ArtistModel artistDetailsModel) {
        getViewState().openArtistDetails(artistDetailsModel);
    }

    private void loadArtistList() {

        getViewState().onStartLoading();

        Disposable disposable = repository.getArtists(10)
                .flatMap(Observable::fromIterable)
                .flatMap(artist -> artist.getMbid().isEmpty()
                        ? repository.getArtistDetailsByName(artist.getName()).toObservable()
                        : repository.getArtistDetailsByMbid(artist.getMbid()).toObservable()
                )
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        artists -> {
                            getViewState().onFinishLoading();
                            this.artists.addAll(artists);
                            getViewState().showArtists(this.artists);
                        },
                        error -> {
                            getViewState().onFinishLoading();
                            getViewState().showError(error.getMessage());
                            Log.e(TAG, "loadArtistList(): ", error);
                        }
                );

        unsubscribeOnDestroy(disposable);

    }

}
