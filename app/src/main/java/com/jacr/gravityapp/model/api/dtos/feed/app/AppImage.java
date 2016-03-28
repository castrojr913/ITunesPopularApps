package com.jacr.gravityapp.model.api.dtos.feed.app;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Jesus on 11/21/2015.
 */
public class AppImage implements Serializable {

    @SerializedName("label")
    private String url;

    public String getUrl() {
        return url;
    }

}
