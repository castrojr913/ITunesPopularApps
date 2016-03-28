package com.jacr.gravityapp.model.api.manager.apps;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.google.gson.reflect.TypeToken;
import com.jacr.gravityapp.GravityApplication;
import com.jacr.gravityapp.model.api.DataSource;
import com.jacr.gravityapp.model.api.manager.http.HttpClient;
import com.jacr.gravityapp.model.api.ModelError;
import com.jacr.gravityapp.model.api.dtos.feed.ITunesFeed;
import com.jacr.gravityapp.model.api.dtos.feed.app.AppInfo;
import com.jacr.gravityapp.model.api.manager.ApiManager;
import com.jacr.gravityapp.model.api.manager.listener.AppManagerListener;
import com.jacr.gravityapp.model.api.manager.listener.ManagerListener;
import com.jacr.gravityapp.utilities.helpers.LogHelper;
import com.jacr.gravityapp.utilities.helpers.StringHelper;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Jesus Castro on 11/22/2015.
 */
public class ITunesAppsManager extends ApiManager implements AppsManager {

    //<editor-fold desc="Constants & Variables"

    private static final String PREFERENCE_NAME = "ITunesAppsPreferences";
    private static final String PREFERENCE_NAME_KEY_APP_INFO = "appInfo";

    private static ITunesAppsManager singleton;

    //</editor-fold>

    //<editor-fold desc="Singleton">

    private ITunesAppsManager() {
        // Blank
    }

    public static AppsManager getInstance() {
        if (singleton == null) {
            singleton = new ITunesAppsManager();
        }
        return singleton;
    }

    //</editor-fold>

    //<editor-fold desc="Manager Overrides">

    @Override
    protected Class<?> getLogTag() {
        return ITunesAppsManager.class;
    }

    @Override
    protected void manageResponse(String url, byte[] response, ManagerListener listener) {
        try {
            if (url.equals(DataSource.API_BASE) && listener instanceof AppManagerListener) {
                ITunesFeed feed = gson.fromJson(new String(response), ITunesFeed.class);
                serializeAppsList(feed.getInfo());
                ((AppManagerListener) listener).onLoadApps(feed.getInfo());
            } else {
                listener.onError(ModelError.WEBSERVICE_FAILURE);
            }
        } catch (Exception e) {
            LogHelper.getInstance().exception(getLogTag(), e, e.toString());
            listener.onError(ModelError.WEBSERVICE_FAILURE);
        }
    }

    //</editor-fold>

    @Override
    public void getTopTwentyApps(boolean shouldUpdate, @NonNull AppManagerListener listener) {
        List<AppInfo> dtos = getCachedAppsList();
        if (dtos != null && !shouldUpdate) {
            listener.onLoadApps(dtos);
        } else {
            String url = DataSource.API_BASE;
            LogHelper.getInstance().debugRequest(getLogTag(), url, null);
            HttpClient.getInstance().get(url, createHttpCallback(url, listener));
        }
    }

    //<editor-fold desc="Data Cache">

    private List<AppInfo> getCachedAppsList() {
        String serializedModel = getPreferences().getString(PREFERENCE_NAME_KEY_APP_INFO, null);
        if (StringHelper.isEmpty(serializedModel)) {
            return null;
        } else {
            Type type = new TypeToken<List<AppInfo>>() {
            }.getType();
            return gson.fromJson(serializedModel, type);
        }
    }

    private void serializeAppsList(@NonNull List<AppInfo> appInfo) {
        String serializedModel = gson.toJson(appInfo);
        LogHelper.getInstance().debug(getLogTag(), serializedModel);
        SharedPreferences.Editor preferencesEditor = getPreferences().edit();
        preferencesEditor.putString(PREFERENCE_NAME_KEY_APP_INFO, serializedModel);
        preferencesEditor.apply();
    }

    public void clearCache() {
        SharedPreferences.Editor preferencesEditor = getPreferences().edit();
        preferencesEditor.clear();
        preferencesEditor.apply();
    }

    private SharedPreferences getPreferences() {
        return GravityApplication.getInstance().getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    //</editor-fold>

}
