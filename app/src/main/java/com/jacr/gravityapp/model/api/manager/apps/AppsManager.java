package com.jacr.gravityapp.model.api.manager.apps;

import com.jacr.gravityapp.model.api.manager.listener.AppManagerListener;

/**
 * Created by Jesus on 11/22/2015.
 */
public interface AppsManager {

    void getTopTwentyApps(boolean shouldUpdate, AppManagerListener listener);

}
