package com.jacr.gravityapp.controllers.navigation.presenter;

import com.jacr.gravityapp.controllers.navigation.NavigationItem;

/**
 * Created by Jesus on 11/22/2015.
 */
public interface MainNavigationPresenter {

    void onLoadPanel();

    void onTapPanelItem(NavigationItem item);

}
