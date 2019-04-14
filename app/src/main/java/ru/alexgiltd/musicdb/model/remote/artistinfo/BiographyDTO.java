
package ru.alexgiltd.musicdb.model.remote.artistinfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BiographyDTO {

    @SerializedName("links")
    @Expose
    private LinksDTO linksDTO;
    @SerializedName("published")
    @Expose
    private String published;
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("content")
    @Expose
    private String content;

    public LinksDTO getLinksDTO() {
        return linksDTO;
    }

    public void setLinksDTO(LinksDTO linksDTO) {
        this.linksDTO = linksDTO;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
