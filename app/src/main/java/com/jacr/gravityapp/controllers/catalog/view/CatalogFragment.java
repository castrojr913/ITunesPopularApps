package com.jacr.gravityapp.controllers.catalog.view;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jacr.gravityapp.R;
import com.jacr.gravityapp.controllers.catalog.presenter.BasicCatalogPresenter;
import com.jacr.gravityapp.controllers.catalog.presenter.CatalogPresenter;
import com.jacr.gravityapp.model.api.ModelError;
import com.jacr.gravityapp.model.api.dtos.feed.app.AppInfo;
import com.jacr.gravityapp.utilities.views.ScrollableSwipeRefreshLayout;
import com.jacr.gravityapp.utilities.views.listener.RecyclerItemClickListener;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.ColorRes;
import org.androidannotations.annotations.res.StringRes;

import java.util.List;

import jp.wasabeef.recyclerview.animators.FadeInAnimator;
import jp.wasabeef.recyclerview.animators.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.adapters.ScaleInAnimationAdapter;

/**
 * Created by Jesus on 11/21/2015.
 */
@EFragment(R.layout.catalog_fragment)
public class CatalogFragment extends Fragment implements CatalogView, SwipeRefreshLayout.OnRefreshListener,
        RecyclerItemClickListener.OnItemClickListener {

    //<editor-fold desc="Variables">

    private CatalogPresenter presenter;
    private Activity parentActivity;
    private List<AppInfo> items;

    //</editor-fold>

    // <editor-fold desc="View Instances">

    @ViewById(R.id.catalog_layout_refresh)
    ScrollableSwipeRefreshLayout swipeToRefreshLayout;

    @ViewById(R.id.catalog_recyclerview_items)
    RecyclerView itemsView;

    @ViewById(R.id.catalog_progressbar)
    View progressBarView;

    @ViewById(R.id.catalog_view_no_items)
    View noItemsView;

    // </editor-fold>

    //<editor-fold desc="String Resources">

    @StringRes(R.string.fragment_catalog_progress_getting_apps)
    String progressBarText;

    @StringRes(R.string.error_connectivity)
    String errorConnectivity;

    @StringRes(R.string.error_timeout)
    String errorTimeout;

    @StringRes(R.string.error_webservice)
    String errorWebservice;

    //</editor-fold>

    //<editor-fold desc="Color Resources">

    @ColorRes(R.color.app_refresh_color_1)
    int firstRefreshColor;

    @ColorRes(R.color.app_refresh_color_2)
    int secondRefreshColor;

    //</editor-fold>

    @AfterViews
    void init() {
        parentActivity = getActivity();
        // Refresh Indicator
        swipeToRefreshLayout.setOnRefreshListener(this);
        swipeToRefreshLayout.setColorSchemeColors(firstRefreshColor, secondRefreshColor);
        swipeToRefreshLayout.setRecyclerView(itemsView);
        // Progress Bar
        TextView progressBarTextView = (TextView) progressBarView.findViewById(R.id.progressbar_text);
        progressBarTextView.setText(progressBarText);
        // --
        itemsView.addOnItemTouchListener(new RecyclerItemClickListener(parentActivity, this));
        // Setting up presenter
        presenter = new BasicCatalogPresenter(this);
        presenter.onLoadViews();
    }

    //<editor-fold desc="Override - Item Event">

    @Override
    public void onItemClick(View view, int position) {
        presenter.onItemClicked(position);
    }

    //</editor-fold>

    // <editor-fold desc="Overrides - Refresh Event">

    @Override
    public void onRefresh() {
        presenter.onRefreshContent();
    }

    // </editor-fold>

    // <editor-fold desc="Overrides - List & Grid Management">

    @Override
    public void enableGridForItems(int columnNumber) {
        itemsView.setLayoutManager(new GridLayoutManager(parentActivity, columnNumber));
    }

    @Override
    public void enableListForItems() {
        itemsView.setLayoutManager(new LinearLayoutManager(parentActivity));
    }

    @Override
    @UiThread
    public void setAppsItems(@NonNull final List<AppInfo> items) {
        this.items = items;
        CatalogItemAdapter itemAdapter = new CatalogItemAdapter(parentActivity, items);
        // Add animation
        itemsView.setItemAnimator(new FadeInAnimator());
        AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(itemAdapter);
        ScaleInAnimationAdapter scaleAdapter = new ScaleInAnimationAdapter(alphaAdapter);
        // --
        itemsView.setAdapter(scaleAdapter);
    }

    @Override
    @UiThread
    public void showItemsView() {
        itemsView.setVisibility(View.VISIBLE);
        noItemsView.setVisibility(View.GONE);
    }

    @Override
    @UiThread
    public void showNoItemsView() {
        itemsView.setVisibility(View.GONE);
        noItemsView.setVisibility(View.VISIBLE);
    }

    @Override
    @UiThread
    public void showProgressBar() {
        progressBarView.setVisibility(View.VISIBLE);
        itemsView.setVisibility(View.GONE);
    }

    @Override
    @UiThread
    public void hideProgressBar() {
        progressBarView.setVisibility(View.GONE);
        itemsView.setVisibility(View.VISIBLE);
    }

    @Override
    @UiThread
    public void hideRefreshIndicator() {
        swipeToRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showItemDetailView(int position) {
        CatalogItemDetailActivity_.intent(parentActivity).extra("app_info", items.get(position)).start();
    }

    //</editor-fold>

    //<editor-fold desc="Error Management">

    @Override
    @UiThread
    public void showError(@NonNull ModelError error) {
        String message = error == ModelError.CONNECTIVITY_FAILURE ? errorConnectivity
                : error == ModelError.TIMEOUT_FAILURE ? errorTimeout : errorWebservice;
        Toast.makeText(parentActivity, message, Toast.LENGTH_LONG).show();
    }

    // </editor-fold>

}
