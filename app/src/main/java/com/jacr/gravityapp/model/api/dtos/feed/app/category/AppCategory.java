package com.jacr.gravityapp.model.api.dtos.feed.app.category;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Jesus on 11/21/2015.
 */
public class AppCategory implements Serializable {

    @SerializedName("attributes")
    private AppCategoryAttribute attribute;

    public String getLabel() {
        return attribute != null ? attribute.getLabel() : "";
    }

}
