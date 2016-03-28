package com.jacr.gravityapp.controllers.catalog.presenter;

import com.jacr.gravityapp.controllers.catalog.view.CatalogItemDetailView;

/**
 * Created by Jesus on 11/24/2015.
 */
public class BasicDetailCatalogPresenter implements DetailCatalogPresenter {

    // <editor-fold desc="Constants & Variables">

    private static final int ANIMATION_TIME = 2000;

    private CatalogItemDetailView view;

    // </editor-fold>

    public BasicDetailCatalogPresenter(CatalogItemDetailView view) {
        this.view = view;
    }

    // <editor-fold desc="Overrides - View Events">

    @Override
    public void onToolbarClick() {
        view.closeView();
    }

    @Override
    public void onLoadViews() {
        view.printData();
        view.animateViewHeader(ANIMATION_TIME);
        view.animateViewBody(ANIMATION_TIME);
    }

    //</editor-fold>

}
