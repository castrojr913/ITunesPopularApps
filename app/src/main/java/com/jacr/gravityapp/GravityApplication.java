package com.jacr.gravityapp;

import android.app.Application;
import android.util.DisplayMetrics;

import com.jacr.gravityapp.utilities.helpers.LogHelper;

/**
 * Created by Jesus on 11/21/2015.
 */
public class GravityApplication extends Application {

    /*
     * This class is executed once time. Therefore, we consider its instance as a Singleton.
     */

    //<editor-fold desc="Constants & Variables">

    private static final int TABLET_DENSITY_THRESHOLD = 600; // dp

    private static GravityApplication application;

    //</editor-fold>

    public static GravityApplication getInstance() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        LogHelper.getInstance().enableLogs();
    }

    //<editor-fold desc="About Device Properties"

    private int[] getScreenDimension() {
        DisplayMetrics metrics = application.getResources().getDisplayMetrics();
        int[] dps = {
                (int) (metrics.widthPixels * 160f / metrics.densityDpi),
                (int) (metrics.heightPixels * 160f / metrics.densityDpi)
        };
        return dps;
    }

    public boolean isTablet() {
        // If a device width >= 600dp may be considered as a Tablet
        return getScreenDimension()[0] >= TABLET_DENSITY_THRESHOLD;
    }

    //</editor-fold>

}
