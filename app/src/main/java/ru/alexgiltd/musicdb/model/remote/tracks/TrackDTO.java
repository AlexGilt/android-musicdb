package ru.alexgiltd.musicdb.model.remote.tracks;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TrackDTO {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("duration")
    @Expose
    private String duration;
    @SerializedName("playcount")
    @Expose
    private String playcount;
    @SerializedName("listeners")
    @Expose
    private String listeners;
    @SerializedName("mbid")
    @Expose
    private String mbid;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("streamable")
    @Expose
    private StreamableDTO streamableDTO;
    @SerializedName("artist")
    @Expose
    private ArtistDTO artistDTO;
    @SerializedName("image")
    @Expose
    private List<ImageDTO> imageDTO = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getPlaycount() {
        return playcount;
    }

    public void setPlaycount(String playcount) {
        this.playcount = playcount;
    }

    public String getListeners() {
        return listeners;
    }

    public void setListeners(String listeners) {
        this.listeners = listeners;
    }

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public StreamableDTO getStreamableDTO() {
        return streamableDTO;
    }

    public void setStreamableDTO(StreamableDTO streamableDTO) {
        this.streamableDTO = streamableDTO;
    }

    public ArtistDTO getArtistDTO() {
        return artistDTO;
    }

    public void setArtistDTO(ArtistDTO artistDTO) {
        this.artistDTO = artistDTO;
    }

    public List<ImageDTO> getImageDTO() {
        return imageDTO;
    }

    public void setImageDTO(List<ImageDTO> imageDTO) {
        this.imageDTO = imageDTO;
    }

}
