package com.jacr.gravityapp.controllers.navigation.presenter;

import com.jacr.gravityapp.controllers.navigation.NavigationItem;
import com.jacr.gravityapp.controllers.navigation.view.MainNavigationView;

/**
 * Created by Jesus on 11/22/2015.
 */
public class BasicMainNavigationPresenter implements MainNavigationPresenter {

    private MainNavigationView view;

    public BasicMainNavigationPresenter(MainNavigationView view) {
        this.view = view;
    }

    // <editor-fold desc="Overrides - View Events">

    @Override
    public void onLoadPanel() {
        view.loadNavigationView(NavigationItem.CATALOG);
    }

    @Override
    public void onTapPanelItem(NavigationItem item) {
        if (item == NavigationItem.EXIT) {
            view.closeAllViews();
        } else {
            view.loadNavigationView(item);
        }
    }

    //</editor-fold>

}
