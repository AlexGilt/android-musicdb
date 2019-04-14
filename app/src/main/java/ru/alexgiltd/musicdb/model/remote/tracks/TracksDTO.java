package ru.alexgiltd.musicdb.model.remote.tracks;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TracksDTO {

    @SerializedName("track")
    @Expose
    private List<TrackDTO> trackDTO = null;
    @SerializedName("@attr")
    @Expose
    private MetaDTO metaDTO;

    public List<TrackDTO> getTrackDTO() {
        return trackDTO;
    }

    public void setTrackDTO(List<TrackDTO> trackDTO) {
        this.trackDTO = trackDTO;
    }

    public MetaDTO getMetaDTO() {
        return metaDTO;
    }

    public void setMetaDTO(MetaDTO metaDTO) {
        this.metaDTO = metaDTO;
    }
}
