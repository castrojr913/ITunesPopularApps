package com.jacr.gravityapp.controllers.catalog.view;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.jacr.gravityapp.R;
import com.jacr.gravityapp.controllers.catalog.presenter.BasicDetailCatalogPresenter;
import com.jacr.gravityapp.controllers.catalog.presenter.DetailCatalogPresenter;
import com.jacr.gravityapp.model.api.dtos.feed.app.AppInfo;
import com.jacr.gravityapp.utilities.helpers.ViewHelper;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Jesus on 11/23/2015.
 */
@EActivity(R.layout.catalog_activity_item_detail)
public class CatalogItemDetailActivity extends AppCompatActivity implements CatalogItemDetailView {

    private DetailCatalogPresenter presenter;

    //<editor-fold desc="View Instances">

    @ViewById(R.id.catalog_detail_layout_header)
    LinearLayout headerLayout;

    @ViewById(R.id.catalog_detail_layout_body)
    RelativeLayout bodyLayout;

    @ViewById(R.id.catalog_detail_toolbar)
    Toolbar toolbar;

    @ViewById(R.id.catalog_detail_header_textview_title)
    TextView titleHeaderTextView;

    @ViewById(R.id.catalog_detail_header_textview_subtitle)
    TextView subTitleHeaderTextView;

    @ViewById(R.id.catalog_detail_header_imageview)
    ImageView headerImageView;

    @ViewById(R.id.catalog_detail_textview_description)
    TextView descriptionTextView;

    @ViewById(R.id.catalog_detail_textview_price)
    TextView priceTextView;

    @ViewById(R.id.catalog_detail_textview_category)
    TextView categoryTextView;

    @ViewById(R.id.catalog_detail_textview_release_date)
    TextView releaseDateTextView;

    @ViewById(R.id.catalog_detail_textview_rights)
    TextView rightsTextView;

    //</editor-fold>

    @Extra("app_info")
    AppInfo appInfo;

    @AfterViews
    void init() {
        // Setting up Toolbar
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        // --
        presenter = new BasicDetailCatalogPresenter(this);
        presenter.onLoadViews();
    }

    // <editor-fold desc=" Overrides - Toolbar">

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.catalog_activity_details, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                presenter.onToolbarClick();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    // </editor-fold>

    //<editor-fold desc="Overrides - View">

    @Override
    public void closeView() {
        finish();
    }

    @Override
    @UiThread
    public void animateViewHeader(long duration) {
        YoYo.with(Techniques.Tada).duration(duration).playOn(headerLayout);
    }

    @Override
    @UiThread
    public void animateViewBody(long duration) {
        YoYo.with(Techniques.Landing).duration(duration).playOn(bodyLayout);
    }

    @Override
    @UiThread
    public void printData() {
        ViewHelper.loadPicture(this, headerImageView, appInfo.getMediumThumbnailUrl(), 60);
        titleHeaderTextView.setText(appInfo.getName());
        subTitleHeaderTextView.setText(appInfo.getAuthorName());
        descriptionTextView.setText(appInfo.getSummary());
        priceTextView.setText(appInfo.getPrice());
        categoryTextView.setText(appInfo.getCategory());
        releaseDateTextView.setText(appInfo.getReleaseDate());
        rightsTextView.setText(appInfo.getRights());
    }

    //</editor-fold>

}
