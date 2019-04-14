package ru.alexgiltd.musicdb.data.remote.api;


import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.alexgiltd.musicdb.model.remote.artistinfo.ArtistInfoResponse;
import ru.alexgiltd.musicdb.model.remote.artists.ArtistsResponse;
import ru.alexgiltd.musicdb.model.remote.trackinfo.TrackInfoResponse;
import ru.alexgiltd.musicdb.model.remote.tracks.TracksResponse;

import static ru.alexgiltd.musicdb.util.Constants.*;

public interface LastFmService {

    @GET(END_POINT + "&" + GET_TOP_ARTISTS_METHOD)
    Observable<ArtistsResponse> getArtistList(@Query("limit") int limit);

    @GET(END_POINT + "&" + GET_ARTIST_INFO_METHOD)
    Single<ArtistInfoResponse> getArtistInfoByMbid(@Query("mbid") String mbid);

    @GET(END_POINT + "&" + GET_ARTIST_INFO_METHOD)
    Single<ArtistInfoResponse> getArtistInfoByName(@Query("artist") String artistName);

    @GET(END_POINT + "&" + GET_TOP_TRACKS_METHOD)
    Observable<TracksResponse> getTrackList(@Query("limit") int limit);

    @GET(END_POINT + "&" + GET_TRACK_INFO_METHOD)
    Single<TrackInfoResponse> getTrackInfoByName(@Query("artist") String artistName,
                                                 @Query("track") String trackName);
}
