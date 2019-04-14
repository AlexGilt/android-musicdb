package ru.alexgiltd.musicdb.model.remote;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public abstract class BaseResponse {

    @SerializedName("error")
    @Expose
    private int errorCode;

    @SerializedName("message")
    @Expose
    private String message;

    public BaseResponse() {
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "errorCode=" + errorCode +
                ", message='" + message + '\'' +
                '}';
    }
}
