package com.jacr.gravityapp.model.api.dtos.feed.app.category;

import java.io.Serializable;

/**
 * Created by Jesus on 11/21/2015.
 */
class AppCategoryAttribute implements Serializable {

    private String id;
    private String term;
    private String scheme;
    private String label;

    public String getId() {
        return id;
    }

    public String getTerm() {
        return term;
    }

    public String getScheme() {
        return scheme;
    }

    public String getLabel() {
        return label;
    }

}
