package ru.alexgiltd.musicdb.model.remote.artists;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArtistDTO {

    @SerializedName("name")
    @Expose
    private String name;
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
    private String streamable;
    @SerializedName("image")
    @Expose
    private List<ImageDTO> image;

    public ArtistDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getStreamable() {
        return streamable;
    }

    public void setStreamable(String streamable) {
        this.streamable = streamable;
    }

    public List<ImageDTO> getImage() {
        return image;
    }

    public void setImage(List<ImageDTO> image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "ArtistDetailsDTO{" +
                "name='" + name + '\'' +
                ", playcount='" + playcount + '\'' +
                ", listeners='" + listeners + '\'' +
                ", mbid='" + mbid + '\'' +
                ", url='" + url + '\'' +
                ", streamable='" + streamable + '\'' +
                ", image=" + image +
                '}';
    }
}
