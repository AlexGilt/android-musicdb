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
                .filter(artistsResponse -> artistsResponse.getErrorCode() == 0)
                .map(ArtistsResponseMapperKt::mapToSimplifiedArtistModelList);
    }

    @Override
    public Observable<List<TrackModel>> getTrackList(int limit) {
        return lastFmService.getTrackList(limit)
                .filter(trackResponse -> trackResponse.getErrorCode() == 0)
                .map(TracksResponseMapperKt::mapToTrackModelList);
    }

    @Override
    public Single<ArtistModel> getArtistDetailsById(String mbid) {
        return lastFmService.getArtistInfoByMbid(mbid)
                .filter(artistInfoResponse -> artistInfoResponse.getErrorCode() == 0)
                .toSingle()
                .map(ArtistInfoResponseMapperKt::mapToArtistDetailModel);
    }

    @Override
    public Single<ArtistModel> getArtistDetailsByName(String artistName) {
        return lastFmService.getArtistInfoByName(artistName)
                .filter(artistInfoResponse -> artistInfoResponse.getErrorCode() == 0)
                .toSingle()
                .map(ArtistInfoResponseMapperKt::mapToArtistDetailModel);
    }

    @Override
    public Single<TrackDetailsModel> getTrackDetailsByName(String artistName, String trackName) {
        return lastFmService.getTrackInfoByName(artistName, trackName)
                .filter(trackInfoResponse -> trackInfoResponse.getMessage() == null)
                .toSingle()
                .map(TrackInfoResponseMapperKt::mapToTrackDetailsModel);
    }
}
