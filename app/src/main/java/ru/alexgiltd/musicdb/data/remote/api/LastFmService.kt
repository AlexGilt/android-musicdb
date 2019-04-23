package ru.alexgiltd.musicdb.data.remote.api


import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.alexgiltd.musicdb.BuildConfig
import ru.alexgiltd.musicdb.model.remote.artistinfo.ArtistInfoResponse
import ru.alexgiltd.musicdb.model.remote.artists.ArtistsResponse
import ru.alexgiltd.musicdb.model.remote.trackinfo.TrackInfoResponse
import ru.alexgiltd.musicdb.model.remote.tracks.TracksResponse

const val API_KEY = "api_key=" + BuildConfig.LASTFM_API_KEY
const val FORMAT_JSON = "format=json"
const val END_POINT = "2.0/?$API_KEY&$FORMAT_JSON"
const val GET_TOP_ARTISTS_METHOD = "method=chart.getTopArtists"
const val GET_TOP_TRACKS_METHOD = "method=chart.getTopTracks"
const val GET_ARTIST_INFO_METHOD = "method=artist.getInfo"
const val GET_TRACK_INFO_METHOD = "method=track.getInfo"

interface LastFmService {

    @GET("$END_POINT&$GET_TOP_ARTISTS_METHOD")
    fun getArtistList(@Query("limit") limit: Int): Observable<ArtistsResponse>

    @GET("$END_POINT&$GET_ARTIST_INFO_METHOD")
    fun getArtistInfoByMbid(@Query("mbid") mbid: String): Single<ArtistInfoResponse>

    @GET("$END_POINT&$GET_ARTIST_INFO_METHOD")
    fun getArtistInfoByName(@Query("artist") artistName: String): Single<ArtistInfoResponse>

    @GET("$END_POINT&$GET_TOP_TRACKS_METHOD")
    fun getTrackList(@Query("limit") limit: Int): Observable<TracksResponse>

    @GET("$END_POINT&$GET_TRACK_INFO_METHOD")
    fun getTrackInfoByName(@Query("artist") artistName: String,
                           @Query("track") trackName: String): Single<TrackInfoResponse>
}
