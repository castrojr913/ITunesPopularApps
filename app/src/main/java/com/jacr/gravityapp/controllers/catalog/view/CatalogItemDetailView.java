package com.jacr.gravityapp.controllers.catalog.view;

/**
 * Created by Jesus on 11/23/2015.
 */
public interface CatalogItemDetailView {

    void printData();

    void closeView();

    void animateViewHeader(long duration);

    void animateViewBody(long duration);

}
