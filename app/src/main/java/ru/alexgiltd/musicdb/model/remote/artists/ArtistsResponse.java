
package ru.alexgiltd.musicdb.model.remote.artists;

import ru.alexgiltd.musicdb.model.remote.BaseResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArtistsResponse extends BaseResponse {

    @SerializedName("artists")
    @Expose
    private ArtistsDTO artistsDTO;

    public ArtistsDTO getArtists() {
        return artistsDTO;
    }

    public void setArtists(ArtistsDTO artistsDTO) {
        this.artistsDTO = artistsDTO;
    }

}
