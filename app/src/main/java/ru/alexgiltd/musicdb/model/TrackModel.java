package ru.alexgiltd.musicdb.model;

import java.util.Map;
import java.util.Objects;

public class TrackModel {
    private String id;
    private String name;
    private Map<String, String> images;
    private SimpleArtistModel trackOwner;
    private long listeners;
    private long playcount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getImages() {
        return images;
    }

    public void setImages(Map<String, String> images) {
        this.images = images;
    }

    public SimpleArtistModel getTrackOwner() {
        return trackOwner;
    }

    public void setTrackOwner(SimpleArtistModel trackOwner) {
        this.trackOwner = trackOwner;
    }

    public long getListeners() {
        return listeners;
    }

    public void setListeners(long listeners) {
        this.listeners = listeners;
    }

    public long getPlaycount() {
        return playcount;
    }

    public void setPlaycount(long playcount) {
        this.playcount = playcount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrackModel that = (TrackModel) o;
        return listeners == that.listeners &&
                playcount == that.playcount &&
                Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(images, that.images) &&
                Objects.equals(trackOwner, that.trackOwner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, images, trackOwner, listeners, playcount);
    }

    @Override
    public String toString() {
        return "TrackModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", images=" + images +
                ", trackOwner=" + trackOwner +
                ", listeners=" + listeners +
                ", playcount=" + playcount +
                '}';
    }
}
