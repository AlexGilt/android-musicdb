
package ru.alexgiltd.musicdb.model.remote.artistinfo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TagsDTO {

    @SerializedName("tag")
    @Expose
    private List<TagDTO> tagDTO = null;

    public List<TagDTO> getTagDTO() {
        return tagDTO;
    }

    public void setTagDTO(List<TagDTO> tagDTO) {
        this.tagDTO = tagDTO;
    }

}
