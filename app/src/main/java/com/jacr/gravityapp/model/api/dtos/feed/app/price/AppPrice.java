package com.jacr.gravityapp.model.api.dtos.feed.app.price;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Jesus on 11/21/2015.
 */
public class AppPrice implements Serializable {

    @SerializedName("attributes")
    private AppPriceAttribute attribute;

    public String getAmount() {
        return attribute != null ? attribute.getAmount() : "";
    }

    public String getCurrency() {
        return attribute != null ? attribute.getCurrency() : "";
    }

}
