package ru.alexgiltd.musicdb.model.remote.tracks;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import ru.alexgiltd.musicdb.model.remote.BaseResponse;

public class TracksResponse extends BaseResponse {

    @SerializedName("tracks")
    @Expose
    private TracksDTO tracks;

    public TracksDTO getTracks() {
        return tracks;
    }

    public void setTracks(TracksDTO tracks) {
        this.tracks = tracks;
    }

}
