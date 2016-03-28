package com.jacr.gravityapp.model.api.dtos.feed;

import com.jacr.gravityapp.model.api.dtos.feed.app.AppInfo;

import java.util.List;

/**
 * Created by Jesus on 11/21/2015.
 */
public class ITunesFeed {

    private Feed feed;

    public List<AppInfo> getInfo() {
        return feed.getInfo();
    }

}
