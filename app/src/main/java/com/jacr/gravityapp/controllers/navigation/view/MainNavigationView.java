package com.jacr.gravityapp.controllers.navigation.view;

import com.jacr.gravityapp.controllers.navigation.NavigationItem;

/**
 * Created by Jesus on 11/22/2015.
 */
public interface MainNavigationView {

    void loadNavigationView(NavigationItem item);

    void closeAllViews();

}
