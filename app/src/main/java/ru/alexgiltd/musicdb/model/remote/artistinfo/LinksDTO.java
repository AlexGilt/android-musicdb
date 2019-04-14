
package ru.alexgiltd.musicdb.model.remote.artistinfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LinksDTO {

    @SerializedName("link")
    @Expose
    private LinkDTO linkDTO;

    public LinkDTO getLinkDTO() {
        return linkDTO;
    }

    public void setLinkDTO(LinkDTO linkDTO) {
        this.linkDTO = linkDTO;
    }

}
