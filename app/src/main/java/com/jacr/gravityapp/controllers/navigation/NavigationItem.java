package com.jacr.gravityapp.controllers.navigation;

/**
 * Created by Jesus on 11/22/2015.
 */
public enum NavigationItem {

    CATALOG {
        @Override
        public int getId() {
            return 1;
        }
    },
    EXIT {
        @Override
        public int getId() {
            return 2;
        }
    };

    public abstract int getId();

}
