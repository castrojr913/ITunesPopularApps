package com.jacr.gravityapp.controllers.catalog.view;

import com.jacr.gravityapp.model.api.ModelError;
import com.jacr.gravityapp.model.api.dtos.feed.app.AppInfo;

import java.util.List;

/**
 * Created by Jesus on 11/22/2015.
 */
public interface CatalogView {

    void enableGridForItems(int columnNumber);

    void enableListForItems();

    void setAppsItems(List<AppInfo> apps);

    void showProgressBar();

    void hideProgressBar();

    void hideRefreshIndicator();

    void showItemsView();

    void showNoItemsView();

    void showError(ModelError error);

    void showItemDetailView(int position);

}
