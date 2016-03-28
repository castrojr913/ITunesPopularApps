package com.jacr.gravityapp.controllers.catalog.presenter;

/**
 * Created by Jesus on 11/22/2015.
 */
public interface CatalogPresenter {

    void onLoadViews();

    void onItemClicked(int position);

    void onRefreshContent();

}
