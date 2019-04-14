
package ru.alexgiltd.musicdb.model.remote.artistinfo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SimilarArtistsDTO {

    @SerializedName("artist")
    @Expose
    private List<SimpleArtistDTO> artist = null;

    public List<SimpleArtistDTO> getArtist() {
        return artist;
    }

    public void setArtist(List<SimpleArtistDTO> artist) {
        this.artist = artist;
    }

}
