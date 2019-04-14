
package ru.alexgiltd.musicdb.model.remote.artistinfo;

import ru.alexgiltd.musicdb.model.remote.BaseResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArtistInfoResponse extends BaseResponse {

    @SerializedName("artist")
    @Expose
    private ArtistDetailsDTO artistDetailsDTO;

    public ArtistDetailsDTO getArtistDetailsDTO() {
        return artistDetailsDTO;
    }

    public void setArtistDetailsDTO(ArtistDetailsDTO artistDetailsDTO) {
        this.artistDetailsDTO = artistDetailsDTO;
    }

}
