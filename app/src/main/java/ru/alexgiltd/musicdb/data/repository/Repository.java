package ru.alexgiltd.musicdb.data.repository;

import ru.alexgiltd.musicdb.model.ArtistModel;
import ru.alexgiltd.musicdb.model.SimpleArtistModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import ru.alexgiltd.musicdb.model.TrackDetailsModel;
import ru.alexgiltd.musicdb.model.TrackModel;

public interface Repository {
    Observable<List<SimpleArtistModel>> getArtistList(int limit);

    Observable<List<TrackModel>> getTrackList(int limit);

    Single<ArtistModel> getArtistDetailsById(String mbid);

    Single<ArtistModel> getArtistDetailsByName(String artistName);

    Single<TrackDetailsModel> getTrackDetailsByName(String artistName, String trackName);
}