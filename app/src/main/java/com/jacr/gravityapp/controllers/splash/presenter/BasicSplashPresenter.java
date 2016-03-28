package com.jacr.gravityapp.controllers.splash.presenter;

import android.os.Handler;

import com.jacr.gravityapp.controllers.splash.view.SplashView;


/**
 * Created by Jesus on 11/23/2015.
 */
public class BasicSplashPresenter implements SplashPresenter {

    //<editor-fold desc="Constants & Variables">

    private static final int SPLASH_TIME = 2000;
    private static final int ANIMATION_TIME = 1800;

    private SplashView view;

    //</editor-fold>

    public BasicSplashPresenter(SplashView view) {
        this.view = view;
    }

    @Override
    public void onLoadViews() {
        view.startFirstAnimation(ANIMATION_TIME/2);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                view.goToNavigationView();
            }

        }, SPLASH_TIME);
    }

    @Override
    public void onFinishFirstAnimation() {
        view.startSecondAnimation(ANIMATION_TIME/2);
    }

}
