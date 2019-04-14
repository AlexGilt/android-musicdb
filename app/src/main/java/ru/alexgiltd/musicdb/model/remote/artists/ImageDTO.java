
package ru.alexgiltd.musicdb.model.remote.artists;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImageDTO {

    @SerializedName("#text")
    @Expose
    private String text;
    @SerializedName("size")
    @Expose
    private String size;

    public ImageDTO() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "ImageDTO{" +
                "text='" + text + '\'' +
                ", size='" + size + '\'' +
                '}';
    }
}
