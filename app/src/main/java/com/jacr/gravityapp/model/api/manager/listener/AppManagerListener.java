package com.jacr.gravityapp.model.api.manager.listener;

import com.jacr.gravityapp.model.api.dtos.feed.app.AppInfo;

import java.util.List;

/**
 * Created by Jesus on 11/22/2015.
 */
public interface AppManagerListener extends ManagerListener {

    void onLoadApps(List<AppInfo> apps);

}
