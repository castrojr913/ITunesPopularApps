package com.jacr.gravityapp.model.api.dtos.feed.app.release_date;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Jesus on 11/21/2015.
 */
public class AppReleaseDate implements Serializable {

    @SerializedName("attributes")
    private AppReleaseDateAttribute attribute;

    public String getDate() {
        return attribute != null ? attribute.getLabel() : "";
    }

}
