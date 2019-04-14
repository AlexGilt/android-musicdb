
package ru.alexgiltd.musicdb.model.remote.artists;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArtistsDTO {

    @SerializedName("artist")
    @Expose
    private List<ArtistDTO> artist = null;
    @SerializedName("@attr")
    @Expose
    private MetaDTO meta;

    public List<ArtistDTO> getArtist() {
        return artist;
    }

    public void setArtist(List<ArtistDTO> artist) {
        this.artist = artist;
    }

    public MetaDTO getMetaDTO() {
        return meta;
    }

    public void setMetaDTO(MetaDTO meta) {
        this.meta = meta;
    }

}
