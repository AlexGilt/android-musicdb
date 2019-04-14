
package ru.alexgiltd.musicdb.model.remote.artistinfo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArtistDetailsDTO {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("mbid")
    @Expose
    private String mbid;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("image")
    @Expose
    private List<ImageDTO> imageDTO;
    @SerializedName("streamable")
    @Expose
    private String streamable;
    @SerializedName("ontour")
    @Expose
    private String ontour;
    @SerializedName("stats")
    @Expose
    private StatsDTO statsDTO;
    @SerializedName("similar")
    @Expose
    private SimilarArtistsDTO similarArtistsDTO;
    @SerializedName("tags")
    @Expose
    private TagsDTO tagsDTO;
    @SerializedName("bio")
    @Expose
    private BiographyDTO biographyDTO;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<ImageDTO> getImageDTO() {
        return imageDTO;
    }

    public void setImageDTO(List<ImageDTO> imageDTO) {
        this.imageDTO = imageDTO;
    }

    public String getStreamable() {
        return streamable;
    }

    public void setStreamable(String streamable) {
        this.streamable = streamable;
    }

    public String getOntour() {
        return ontour;
    }

    public void setOntour(String ontour) {
        this.ontour = ontour;
    }

    public StatsDTO getStatsDTO() {
        return statsDTO;
    }

    public void setStatsDTO(StatsDTO statsDTO) {
        this.statsDTO = statsDTO;
    }

    public SimilarArtistsDTO getSimilarArtistsDTO() {
        return similarArtistsDTO;
    }

    public void setSimilarArtistsDTO(SimilarArtistsDTO similarArtistsDTO) {
        this.similarArtistsDTO = similarArtistsDTO;
    }

    public TagsDTO getTagsDTO() {
        return tagsDTO;
    }

    public void setTagsDTO(TagsDTO tagsDTO) {
        this.tagsDTO = tagsDTO;
    }

    public BiographyDTO getBiographyDTO() {
        return biographyDTO;
    }

    public void setBiographyDTO(BiographyDTO biographyDTO) {
        this.biographyDTO = biographyDTO;
    }

}
