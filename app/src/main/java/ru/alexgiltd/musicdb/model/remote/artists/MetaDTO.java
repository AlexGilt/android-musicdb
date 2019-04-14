
package ru.alexgiltd.musicdb.model.remote.artists;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MetaDTO {

    @SerializedName("page")
    @Expose
    private String page;
    @SerializedName("perPage")
    @Expose
    private String perPage;
    @SerializedName("totalPages")
    @Expose
    private String totalPages;
    @SerializedName("total")
    @Expose
    private String total;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPerPage() {
        return perPage;
    }

    public void setPerPage(String perPage) {
        this.perPage = perPage;
    }

    public String getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(String totalPages) {
        this.totalPages = totalPages;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "MetaDTO{" +
                "page='" + page + '\'' +
                ", perPage='" + perPage + '\'' +
                ", totalPages='" + totalPages + '\'' +
                ", total='" + total + '\'' +
                '}';
    }
}
