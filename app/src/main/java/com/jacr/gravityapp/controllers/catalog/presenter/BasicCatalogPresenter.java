package com.jacr.gravityapp.controllers.catalog.presenter;

import android.support.annotation.NonNull;

import com.jacr.gravityapp.GravityApplication;
import com.jacr.gravityapp.controllers.catalog.view.CatalogView;
import com.jacr.gravityapp.model.api.ModelError;
import com.jacr.gravityapp.model.api.dtos.feed.app.AppInfo;
import com.jacr.gravityapp.model.api.manager.apps.ITunesAppsManager;
import com.jacr.gravityapp.model.api.manager.listener.AppManagerListener;

import java.util.List;

/**
 * Created by Jesus on 11/22/2015.
 */
public class BasicCatalogPresenter implements CatalogPresenter {

    // <editor-fold desc="Constants & Variables">

    private static final int GRID_COLUMNS = 2;
    private static final boolean MODE_ONLINE = true;
    private static final boolean MODE_OFFLINE = false;

    private CatalogView view;

    // </editor-fold>

    public BasicCatalogPresenter(CatalogView view) {
        this.view = view;
    }

    //<editor-fold desc="Overrides - View Events">

    @Override
    public void onLoadViews() {
        view.showProgressBar();
        if (GravityApplication.getInstance().isTablet()) {
            view.enableGridForItems(GRID_COLUMNS);
        } else {
            view.enableListForItems();
        }
        loadApps(MODE_OFFLINE); // Get data on Cache if it exists
    }

    @Override
    public void onItemClicked(int position) {
        view.showItemDetailView(position);
    }

    @Override
    public void onRefreshContent() {
        loadApps(MODE_ONLINE); // Get updated data
    }

    //</editor-fold>

    private void loadApps(boolean shouldUpdate) {
        ITunesAppsManager.getInstance().getTopTwentyApps(shouldUpdate, new AppManagerListener() {

            @Override
            public void onLoadApps(@NonNull List<AppInfo> apps) {
                hideIndicators();
                if (apps.isEmpty()) {
                    view.showNoItemsView();
                } else {
                    view.showItemsView();
                    view.setAppsItems(apps);
                }
            }

            @Override
            public void onError(@NonNull ModelError error) {
                hideIndicators();
                view.showError(error);
            }

        });
    }

    //<editor-fold desc="Others">

    private void hideIndicators() {
        view.hideProgressBar();
        view.hideRefreshIndicator();
    }

    //</editor-fold>

}
