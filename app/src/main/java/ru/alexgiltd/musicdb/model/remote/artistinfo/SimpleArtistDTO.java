
package ru.alexgiltd.musicdb.model.remote.artistinfo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SimpleArtistDTO {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("image")
    @Expose
    private List<ImageDTO> imageDTO = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<ImageDTO> getImageDTO() {
        return imageDTO;
    }

    public void setImageDTO(List<ImageDTO> imageDTO) {
        this.imageDTO = imageDTO;
    }

}
