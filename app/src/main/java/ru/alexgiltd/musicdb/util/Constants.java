package ru.alexgiltd.musicdb.util;


import ru.alexgiltd.musicdb.BuildConfig;

public final class Constants {

    public static final String API_KEY = "api_key=" + BuildConfig.LASTFM_API_KEY;
    public static final String FORMAT_JSON = "format=json";

    public static final String END_POINT = "2.0/?" + API_KEY + "&" + FORMAT_JSON;

    public static final String GET_TOP_ARTISTS_METHOD = "method=chart.getTopArtists";
    public static final String GET_TOP_TRACKS_METHOD = "method=chart.getTopTracks";
    public static final String GET_ARTIST_INFO_METHOD = "method=artist.getInfo";
    public static final String GET_TRACK_INFO_METHOD = "method=track.getInfo";

    public static final String EXTRA_LARGE_IMAGE = "extralarge";
    public static final String LARGE_IMAGE = "large";
    public static final String MEGA_IMAGE = "mega";
    public static final String DEFAULT_IMAGE = "";
    public static final String MEDIUM_IMAGE = "medium";
    public static final String SMALL_IMAGE = "small";

    private Constants() {
    }
}
