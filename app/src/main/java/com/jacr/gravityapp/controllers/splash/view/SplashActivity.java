package com.jacr.gravityapp.controllers.splash.view;

import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.jacr.gravityapp.R;
import com.jacr.gravityapp.controllers.navigation.view.MainDrawerActivity_;
import com.jacr.gravityapp.controllers.splash.presenter.BasicSplashPresenter;
import com.jacr.gravityapp.controllers.splash.presenter.SplashPresenter;
import com.nineoldandroids.animation.Animator;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.splash_activity)
public class SplashActivity extends AppCompatActivity implements SplashView {

    private SplashPresenter presenter;

    //<editor-fold desc="View Instances">

    @ViewById(R.id.splash_textview)
    TextView splashTextView;

    //</editor-fold>

    @AfterViews
    void init() {
        presenter = new BasicSplashPresenter(this);
        presenter.onLoadViews();
    }

    //<editor-fold desc="Overrides - Navigation & Animation"

    @Override
    @UiThread
    public void goToNavigationView() {
        MainDrawerActivity_.intent(this).start();
        finish();
    }


    @Override
    @UiThread
    public void startFirstAnimation(final int time) {
        YoYo.with(Techniques.ZoomInLeft).duration(time).withListener(new SplashAnimationAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                presenter.onFinishFirstAnimation();
            }
        }).playOn(splashTextView);
    }

    @Override
    public void startSecondAnimation(int time) {
        YoYo.with(Techniques.ZoomOutRight).duration(time).playOn(splashTextView);
    }

    //</editor-fold>

}
