package com.jacr.gravityapp.model.api.dtos.feed;

import com.jacr.gravityapp.model.api.dtos.feed.app.AppInfo;

import java.util.List;

/**
 * Created by Jesus on 11/21/2015.
 */
class Feed {

    private List<AppInfo> entry;

    public List<AppInfo> getInfo() {
        return entry;
    }

}
