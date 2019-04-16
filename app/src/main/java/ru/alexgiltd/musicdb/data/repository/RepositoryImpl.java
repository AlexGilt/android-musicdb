package ru.alexgiltd.musicdb.data.repository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.Single;
import ru.alexgiltd.musicdb.data.remote.api.LastFmService;
import ru.alexgiltd.musicdb.model.ArtistModel;
import ru.alexgiltd.musicdb.model.SimpleArtistModel;
import ru.alexgiltd.musicdb.model.TrackDetailsModel;
import ru.alexgiltd.musicdb.model.TrackModel;
import ru.alexgiltd.musicdb.model.mapper.ArtistInfoResponseMapperKt;
import ru.alexgiltd.musicdb.model.mapper.ArtistsResponseMapperKt;
import ru.alexgiltd.musicdb.model.mapper.TrackInfoResponseMapperKt;
import ru.alexgiltd.musicdb.model.mapper.TracksResponseMapperKt;
import ru.alexgiltd.musicdb.model.remote.BaseResponse;

@Singleton
public class RepositoryImpl implements Repository {

    private final LastFmService lastFmService;

    @Inject
    public RepositoryImpl(LastFmService lastFmService) {
        this.lastFmService = lastFmService;
    }

    @Override
    public Observable<List<SimpleArtistModel>> getArtistList(int limit) {
        return lastFmService.getArtistList(limit)
                .doOnNext(this::throwExceptionIfApiError)
                .map(ArtistsResponseMapperKt::mapToSimplifiedArtistModelList);
    }

    @Override
    public Observable<List<TrackModel>> getTrackList(int limit) {
        return lastFmService.getTrackList(limit)
                .doOnNext(this::throwExceptionIfApiError)
                .map(TracksResponseMapperKt::mapToTrackModelList);
    }

    @Override
    public Single<ArtistModel> getArtistDetailsById(String mbid) {
        return lastFmService.getArtistInfoByMbid(mbid)
                .doOnSuccess(this::throwExceptionIfApiError)
                .map(ArtistInfoResponseMapperKt::mapToArtistDetailModel);
    }

    @Override
    public Single<ArtistModel> getArtistDetailsByName(String artistName) {
        return lastFmService.getArtistInfoByName(artistName)
                .doOnSuccess(this::throwExceptionIfApiError)
                .map(ArtistInfoResponseMapperKt::mapToArtistDetailModel);
    }

    @Override
    public Single<TrackDetailsModel> getTrackDetailsByName(String artistName, String trackName) {
        return lastFmService.getTrackInfoByName(artistName, trackName)
                .doOnSuccess(this::throwExceptionIfApiError)
                .map(TrackInfoResponseMapperKt::mapToTrackDetailsModel);
    }

    private void throwExceptionIfApiError(BaseResponse baseResponse) {
        if (baseResponse.getErrorCode() != 0) {
            throw new RuntimeException(
                    String.format(
                            "Error has occurred on server: Error code: %d, message: %s",
                            baseResponse.getErrorCode(),
                            baseResponse.getMessage()
                    )
            );
        }
    }
}
